/* 
	This file is part of eGorilla.

    eGorilla is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    eGorilla is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with eGorilla.  If not, see <http://www.gnu.org/licenses/>.
*/

package peerToPeer.egorilla;

import gestorDeErrores.ControlDeErrores;
import gestorDeErrores.ErrorEGorilla;
import gestorDeRed.GestorDeRed;
import gestorDeRed.NetError;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajes.Mensaje;

/**
 * gestiona la salida de mensajes
 * TODOS los mensajes salen desde esta clase.
 * si se implementa algún control de flujo de subida DEBE ser aqui
 * @author Luis Ayuso
 */
class GestorMensajes extends Thread{
    private Queue<Mensaje> _cola;
    private GestorDeRed<Mensaje> _red;

    private boolean _flush;
    
    public GestorMensajes(GestorDeRed<Mensaje> red) {
        _cola = new LinkedList<Mensaje>();
        _red = red;
        _flush = false;
        this.setName("Cola de Mensajes");
    }

    
    /**
     * encola un mensaje para enviar.
     *
     * @param msj
     */
    synchronized  void addMensajeParaEnviar(Mensaje msj){

        _cola.add(msj);
        this.notify();
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                wait();
                while (!_cola.isEmpty()) {
                    Mensaje msj = _cola.poll();
                    try {
                        _red.envia(msj, msj.ipDestino(), msj.puertoDestino());
                    } catch (NetError ex) {
                        ControlDeErrores.getInstancia().registrarError(ErrorEGorilla.ERROR_RED,
                                "error al enviar mensaje a " + msj.ipDestino() + ": " +
                                ex.getError());
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("*** Soy el Gestor de Mensajes: me muero");
            if (_flush) { // si hay flush vaciamos la cola
                while (!_cola.isEmpty()) {
                    Mensaje msj = _cola.poll();
                    try {
                        _red.envia(msj, msj.ipDestino(), msj.puertoDestino());
                    } catch (NetError e) {
                        ControlDeErrores.getInstancia().registrarError(ErrorEGorilla.ERROR_RED,
                                "error al enviar mensaje a " + msj.ipDestino() + ": " +
                                e.getError());
                    }
                }
            }
        }

    }

    /**
     * fuerza el envio de todos los mensajes y acaba con las
     * transferencias
     */
    void  flushYSalir() {
        try {
            _flush = true;
            this.parar();
            join();
        } catch (InterruptedException ex) {
        }
    }

    synchronized void parar(){
        _cola.clear();
        this.interrupt();
    }
}
