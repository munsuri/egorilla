/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peerToPeer.descargas;

import datos.Archivo;
import datos.Fragmento;
import gestorDeFicheros.GestorCompartidos;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import mensajes.p2p.Tengo;
import mensajes.serverclient.DatosCliente;

/**
 *
 * @author Jose Miguel Guerrero
 */
public class Descarga {

    public static final int PIDEASERVIDOR = 0;
    public static final int PIDEALOSPROPIETARIOS = 20;
    public static final int DESCARGA = 70;

    private int _estado;
    private Archivo _archivo;
    private Vector<Fragmento> _listaFragmentosPendientes;
    private ArrayList<DatosCliente> _propietarios;
    private ArrayList<Par> _listaQuienTieneQue;
    //estado 0 es que debe hacer HolaQuiero, cada 20 veces que pase por el Descargador se hara
    private int _posicion=0;
    
    public Descarga(Archivo archivo){
        _archivo=archivo;
        _estado = PIDEASERVIDOR;
        _listaFragmentosPendientes = new Vector<Fragmento>();
        _propietarios = new ArrayList<DatosCliente>();
        _listaQuienTieneQue = new ArrayList<Par>();
    }

    public Archivo getArchivo(){
        return _archivo;
    }

    public Vector<Fragmento> getListaFragmentosPendientes(){
        return _listaFragmentosPendientes;
    }

    public void actualiza(){
        _listaFragmentosPendientes=GestorCompartidos.getInstancia().queFragmentosTienesPendientes(_archivo.getHash());
    }

    public void actualizaPropietarios(DatosCliente[] datos){
        for(int i=0;i<datos.length;i++){
             _propietarios.add(datos[i]);
        }
        _estado = PIDEALOSPROPIETARIOS;
    }

    public void actualizaQuienTieneQue(Tengo mensaje){
        Cliente cliente=new Cliente(mensaje.ipDestino(),mensaje.puertoDestino());
        Par par=new Par(cliente,mensaje.getFragmentos());
        _listaQuienTieneQue.add(par);
        _estado = DESCARGA;
    }

    public Cliente dameClienteQueTiene(Fragmento frag){
        Cliente cliente=null;
        Random aleatorio=new Random();
        //nos posicionamos de forma aleatoria en un cliente
        _posicion=((int)(aleatorio.nextDouble() * _listaQuienTieneQue.size()));
        //registramos el cliente por el que empezamos a mirar
        int inicial=_posicion++;
        boolean encontrado=false;
        //mientras no demos la vuelta a todos los clientes y no tengamos cliente
        while(!encontrado && inicial!=_posicion){
            Par par=dameSiguienteCliente();
            //TODO Comprobar si se obtiene el indice o no
            int fragmento=par.getfragmentos().indexOf(frag);
            //si obtenemos un fragmento es que hemos encontrado un cliente que lo tiene
            if(fragmento!=-1){
                cliente=par.getCliente();
                encontrado=true;
            }
        }
        return cliente;
    }

    private Par dameSiguienteCliente(){
        int tamLista = _listaQuienTieneQue.size();
        if (_posicion < tamLista-1){//No hemos superado al último
            _posicion ++;//Actualizamos posición para obtener el siguiente
        } else {//Acabamos de superar al último
            _posicion = 0;//El siguiente por tanto será el primero
        }
        return _listaQuienTieneQue.get(_posicion);
    }

    public int getEstado(){
        return _estado;
    }
    
    public boolean  fragmentoDescargado(Fragmento frag){
        return _listaFragmentosPendientes.remove(frag);
    }

    public void decrementaEstado(){
        _estado --;
    }

    //---------- CLASES AUXILIARES

    public class Cliente{
        private String _ip;
        private int _puerto;

        public Cliente(String ip,int puerto){
            _ip=ip;
            _puerto=puerto;
        }

        public String getIP(){
            return _ip;
        }

        public int getPuerto(){
            return _puerto;
        }
    }

    private class Par{
        private Cliente _cliente;
        private Vector<Fragmento> _listaFragmentosTiene;

        public Par(Cliente cliente,Vector<Fragmento> listaFragmentosTiene){
            _cliente=cliente;
            _listaFragmentosTiene=listaFragmentosTiene;
        }

        public Cliente getCliente(){
            return _cliente;
        }

        public Vector<Fragmento> getfragmentos(){
            return _listaFragmentosTiene;
        }
    }

    public ArrayList<DatosCliente> getListaPropietarios(){
        return  _propietarios;
    }
}