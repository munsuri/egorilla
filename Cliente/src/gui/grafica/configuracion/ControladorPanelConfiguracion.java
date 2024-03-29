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

package gui.grafica.configuracion;

import gestorDeConfiguracion.ControlConfiguracionCliente;
import gestorDeConfiguracion.ControlConfiguracionClienteException;
import gestorDeConfiguracion.PropiedadCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase controladora (siguiendo el patron MVC) que se encarga de recibir los eventos
 * de la Vista (GUIPanelConfiguracion) y tratarlos adecuadamente, realizando operaciones 
 * sobre el Modelo (objeto ControlConfiguracionCliente) si es necesario.
 * 
 * @author F. Javier Sanchez Pardo
 */

public class ControladorPanelConfiguracion implements ActionListener{
    
    //ATRIBUTOS
    
    // Se guarda una referencia al Modelo (PATRÓN MVC) que en este caso es un objeto
    // ControlConfiguracionCliente (para poder realizar operaciones sobre el Modelo).
    private ControlConfiguracionCliente _objetoModelo;
    
    // Se guarda una referencia a la Vista (PATRÓN MVC) que en este caso es un objeto
    // GUIPanelConfiguracion (porque puede ser necesario modificarModelo/manipular los elementos
    // de la vista desde el controlador). Así todo queda atado.
    private GUIDialogoConfiguracion _objetoVista;
    
    /**
     * Constructor que recibe una instancia del Modelo (objeto ControlConfiguracionCliente)
     * y otra de la Vista (objeto GUIPanelConfiguracion)
     * @param oCtrlConfigCli referencia al Modelo (patron MVC)
     * @param oGUIPanelConfig referencia a la Vista (patron MVC) 
     */
    public ControladorPanelConfiguracion(ControlConfiguracionCliente oCtrlConfigCli,
                                        GUIDialogoConfiguracion oGUIDialogoConfig){
        _objetoModelo = oCtrlConfigCli;
        _objetoVista = oGUIDialogoConfig;
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        //Boton de Aceptar
        if (source == _objetoVista.obtenerBotonAceptar()){
            try {
                if (validar()){
                    modificarModelo();
                    _objetoVista.setVisible (false);
                }
            } catch (ControlConfiguracionClienteException ex) {
                Logger.getLogger(ControladorPanelConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == _objetoVista.obtenerBotonCancelar()){
            //Boton de Cancelar: se sale sin grabar
            _objetoVista.setVisible (false);
        }else if (source == _objetoVista.obtenerBotonRestaurar()){
            //Boton de Restaurar valores por defecto: se carga el panel con las propiedades POR DEFECTO del Modelo
            _objetoVista.inicializarCampos(_objetoModelo.obtenerConfiguracionPorDefecto());
        }else if (source == _objetoVista.obtenerBotonDirLlegada()){
            //Boton de eleccion del directorio de Llegada de archivos
            String sDirectorio = _objetoVista.obtenerDirectorio();
            if (sDirectorio != null)
                _objetoVista.establecerDirLlegada(sDirectorio);
        }else if (source == _objetoVista.obtenerBotonDirCompartidos()){
            //Boton de eleccion del directorio de comparticion de archivos
            String sDirectorio = _objetoVista.obtenerDirectorio();
            if (sDirectorio != null)
                _objetoVista.establecerDirCompartidos(sDirectorio);
        }
    }
    
    /**
     * Si el contenido de los controles de la pestaña (la Vista) es diferente de los valores 
     * de las propiedades en el objeto ControlConfiguracionCliente (Modelo) entonces
     * actualizo los valores de las propiedades en el Modelo.
     * 
     */
    private void modificarModelo () throws ControlConfiguracionClienteException{
        Properties propiedades = new Properties ();
        String sCadenaAux = null;
        
        sCadenaAux = _objetoVista.obtenerNumDescargasSim();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.NUM_DESCARGAS_SIM.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.NUM_DESCARGAS_SIM.obtenerLiteral(), sCadenaAux);
        
        sCadenaAux = _objetoVista.obtenerLimVelocidadSubida();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.LIM_VELOCIDAD_SUBIDA.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.LIM_VELOCIDAD_SUBIDA.obtenerLiteral(), sCadenaAux);
        
        sCadenaAux = _objetoVista.obtenerLimVelocidadBajada();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.LIM_VELOCIDAD_BAJADA.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.LIM_VELOCIDAD_BAJADA.obtenerLiteral(), sCadenaAux);
                
        sCadenaAux = _objetoVista.obtenerPuerto();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.PUERTO.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.PUERTO.obtenerLiteral(), sCadenaAux);

        sCadenaAux = _objetoVista.obtenerDirLlegada();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.DIR_LLEGADA.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.DIR_LLEGADA.obtenerLiteral(), sCadenaAux);
        
        sCadenaAux = _objetoVista.obtenerDirCompartidos();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.DIR_COMPARTIDOS.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.DIR_COMPARTIDOS.obtenerLiteral(), sCadenaAux);
        
        sCadenaAux = _objetoVista.obtenerIPServidor();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.IP_SERVIDOR.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.IP_SERVIDOR.obtenerLiteral(), sCadenaAux);
        
        sCadenaAux = _objetoVista.obtenerPuertoServidor();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.PUERTO_SERVIDOR.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.PUERTO_SERVIDOR.obtenerLiteral(), sCadenaAux);

        sCadenaAux = _objetoVista.obtenerNombreServidor();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.NOMBRE_SERVIDOR.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.NOMBRE_SERVIDOR.obtenerLiteral(), sCadenaAux);

        sCadenaAux = _objetoVista.obtenerDescripServidor();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.DESCRIP_SERVIDOR.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.DESCRIP_SERVIDOR.obtenerLiteral(), sCadenaAux);

        sCadenaAux = _objetoVista.obtenerNombreUsuario();
        if ( sCadenaAux.compareTo(_objetoModelo.obtenerPropiedad(PropiedadCliente.NOMBRE_USUARIO.obtenerLiteral())) != 0)
            propiedades.setProperty(PropiedadCliente.NOMBRE_USUARIO.obtenerLiteral(), sCadenaAux);
        
        //Si ha habido cambios grabo.
        if (propiedades.size() != 0)
            _objetoModelo.establecerConfiguracion(propiedades);                
    }

    /**
     * Valida el contenido de los controles de entrada.
     */
    private boolean validar(){
        String sMensajeError = "";
        int numErrores = 0;

        //Validar que se rellenan todos los campos
        if ( (_objetoVista.obtenerNumDescargasSim().compareTo("") == 0) ||
            (_objetoVista.obtenerLimVelocidadSubida().compareTo("") == 0) ||
            (_objetoVista.obtenerLimVelocidadBajada().compareTo("") == 0) ||
            (_objetoVista.obtenerPuerto().compareTo("") == 0) ||
            (_objetoVista.obtenerDirLlegada().compareTo("") == 0) ||
            (_objetoVista.obtenerDirCompartidos().compareTo("") == 0) ||
            (_objetoVista.obtenerIPServidor().compareTo("") == 0) ||
            (_objetoVista.obtenerPuertoServidor().compareTo("") == 0) ||
            (_objetoVista.obtenerNombreServidor().compareTo("") == 0) ||
            (_objetoVista.obtenerDescripServidor().compareTo("") == 0) ||
            (_objetoVista.obtenerNombreUsuario().compareTo("") == 0) ){
                sMensajeError = "Todos los campos son obligatorios.\n";
                numErrores++;
        }

        //Validar campos numericos.
        try {
            Integer.parseInt(_objetoVista.obtenerNumDescargasSim());
        }catch(NumberFormatException e){
            sMensajeError = sMensajeError + "El Numero de Descargas debe ser un numero.\n";
            numErrores++;
        }
        try {
            Integer.parseInt(_objetoVista.obtenerLimVelocidadSubida());
        }catch(NumberFormatException e){
            sMensajeError = sMensajeError + "El Limite de Velocidad de subida debe ser un numero.\n";
            numErrores++;
        }
        try {
            Integer.parseInt(_objetoVista.obtenerLimVelocidadBajada());
        }catch(NumberFormatException e){
            sMensajeError = sMensajeError + "El Limite de Velocidad de bajada debe ser un numero.\n";
            numErrores++;
        }
        try {
            Integer.parseInt(_objetoVista.obtenerPuerto());
        }catch(NumberFormatException e){
            sMensajeError = sMensajeError + "El Puerto debe ser un numero.\n";
            numErrores++;
        }
        try {
            Integer.parseInt(_objetoVista.obtenerPuertoServidor());
        }catch(NumberFormatException e){
            sMensajeError = sMensajeError + "El Puerto del Servidor debe ser un numero.\n";
            numErrores++;
        }
        
        if (numErrores > 0){
                JOptionPane.showMessageDialog(_objetoVista, sMensajeError,
                    "Error configuracion", JOptionPane.ERROR_MESSAGE);
                return false;
        }
        
        return true;
    }
    
}
