/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidoregorilla.datos;

import servidoregorilla.paquete.Archivo;
import java.io.Serializable;
import java.util.Vector;
import servidoregorilla.paquete.DatosCliente;
import servidoregorilla.paquete.TipoArchivo;
import servidoregorilla.protocolo.ConexionCliente;

/*****************************************************************************/
/**
 * Clase de Lista de archivos que proporciona todos los métodos necesarios para
 * el tratamiento de éste tipo de objetos.
 * La estructura empleada es una tabla hash ya que proporciona una eficiencia
 * mayor que otras estructuras para éste propósito.
 * 
 * @author pitidecaner
 * @author Salcedonia
 */
public class ListaArchivos extends Vector<Archivo> implements Serializable {

    // ATRIBUTOS
    public Vector<Cliente_Archivo> _relacion;

/*****************************************************************************/
    /**
     * Constructor de la clase ListaArchivos.
     */
    public ListaArchivos() {
        super();
        _relacion = new Vector<Cliente_Archivo>();
    }
	
	

/*****************************************************************************/
    /**
     * Añade un archivo a la lista de archivos.
     * 
     * @param a Archivo a añadir a la lista de archivos.
     */
    public synchronized void anadirArchivo(Archivo a) {
        add(a);
    }

/*****************************************************************************/
    /**
     * Elimina un archivo de la lista de archivos.
     * 
     * @param a Archivo a eliminar de la lista de archivos.
     */
    public synchronized void eliminarArchivo(Archivo a) {

        // TODO: esto no puede funcionar asi
        remove(a);
    }

/*****************************************************************************/
    /**
     * Actualiza la BBDD del servidor con los ficheros que tiene un cliente recién
     * conectado, se comprueba si cada uno de los archivos pertenecían antes al sistema.
     * en tal caso sólo se añade la referencia a este cliente y el posible nombre
     * distinto del archivo.
     *
     * @param conexion la conexión con el cliente.
     * @param listaArchivos la lista de archivos de este cliente.
     */
    public void actualizarDesdeListaCliente(ConexionCliente conexion, 
        ListaArchivos listaArchivos) {

      //No me gusta esto aqui, pero no se ha tenido en cuenta el caso

      //Si es el primer cliente, es decir, servidor sin ficheros...
      if( getNumeroArchivos() == 0 ){
        for( int i = 0;  i < listaArchivos.getNumeroArchivos();  i++ ){
          //Meto todos los ficheros compartidos del cliente
          add( listaArchivos.get(i) );
        }
      }else{ //Sino...
      boolean encontrado = false;

      for( int i = 0;  i < listaArchivos.getNumeroArchivos();  i++ ){
        //flag encontrado ya que si le encuentro el primero en la lista
        //no pierdo tiempo buscando en el resto xq no va a estar repe
        for( int j = 0;  j < getNumeroArchivos() && encontrado == false;  
            j++ ){
          encontrado = listaArchivos.get(i).comparaArchivo( get(j) );
        }
        if( encontrado == false ) {
          System.out.println("No encontrado en el servidor");
          add( listaArchivos.get(i) );
          _relacion.add( new Cliente_Archivo(listaArchivos.get(i).getHash()
                ,listaArchivos.get(i).getNombre(), conexion) );
        }else{
          System.out.println("Encontrado en el servidor");
          //Hago las relaciones para el encontrado
          //Lo pongo a false para seguir buscando
          encontrado = false;
        }
      }
           // buscalo en la BBDD
            //for (Archivo archivoAux : this) 
              //  encontrado = archivoAux.comparaArchivo(archivo);

            // si no existe darlo de alta
            //
           // else {
                
                // tomar nota de la relación, de existir.
                //for (Cliente_Archivo rel : _relacion) {
                  //  if (rel._hash.contentEquals(archivo.getHash())) {
                        
                      //  rel._propietarios.add(conexion);
                    //    rel._nombresArchivo.add(archivo.getNombre());
                   // }
               // }
            //}
       // }
    }
    }

  public int getNumeroArchivos(){
    return size();
  }

  public int getNumeroClientes(){
    return 0;
  }

/*****************************************************************************/
    /**
     * Buscar por nombre, la versión actual busca aquellos archivos que se llaman
     * exactamente como el nombre indicado.
     * 
     * @param nombre Nombre del archivo buscado.
     * 
     * @return Un array con los datos de los clientes que tienen ese archivo.
     */
    public Archivo[] buscar(String nombre){
        
        Vector<Archivo> lista = new Vector<Archivo>();
        
        for (Archivo a : this) {
            if (a._nombre.contains(nombre))
                lista.add(a);
        }
        System.out.println( "Archivos encontrados: <"+ lista.size() +">" );
        Archivo[] ars= new Archivo[lista.size()];
        return lista.toArray(ars);
    }
    
/*****************************************************************************/
    /**
     * Buscar por tipo de archivo.
     * 
     * @param tipo Tipo del archivo buscado.
     * 
     * @return Un array con los datos de los clientes que tienen ese archivo.
     */
    public Archivo[] buscar(TipoArchivo tipo){
        Vector<Archivo> lista = new Vector<Archivo>();
        
        for (Archivo a : this) {
            if (a._tipo == tipo)
                lista.add(a);
        }
        
        Archivo[] ars= new Archivo[lista.size()];
        return lista.toArray(ars);
    }
    
/*****************************************************************************/
    /**
     * Búsqueda por hash.
     *  
     * @param hash Hash del archivo a buscar.
     * 
     * @return Un array con los datos de los clientes que tienen ese archivo.
     */
    public DatosCliente[] getPropietarios (String hash){
        Vector<DatosCliente> lista = new Vector<DatosCliente>();
        
        for (Cliente_Archivo r : _relacion) {
            if (r._hash.contentEquals(hash)){
                for (ConexionCliente cliente : r._propietarios) {
                    lista.add(cliente.getDatosCliente());
                }
                break;
            }
        }
        
        DatosCliente[] clientes= new DatosCliente[lista.size()];
        return lista.toArray(clientes);
    }
}
