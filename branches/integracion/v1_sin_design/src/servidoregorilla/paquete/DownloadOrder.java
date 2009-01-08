/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidoregorilla.paquete;

import java.io.Serializable;

/*****************************************************************************/
/**
 * Clase que implementa los métodos necesarios para la gestión de las consultas
 * de descarga que los clientes realizan al servidor.
 * 
 * @author Pitidecaner
 * @author Salcedonia
 */
public class DownloadOrder implements Peticion, Serializable{
    
    // ATRIBUTOS
    private String _hash;

/*****************************************************************************/
    /**
     * Constructor de la clase DownloadOrder.
     * 
     * @param h Hash asociado.
     */
    public DownloadOrder(String h){
        _hash = h;
    }
    
/*****************************************************************************/
    /**
     * Devuelve la versión asociada a la consulta de descarga.
     * 
     * @return 3
     */
    public int getVersion() {
        return 3;
    }

/*****************************************************************************/
    /**
     * Devuelve el hash asociado al archivo de descarga asociado.
     * 
     * @return El hash asociado al archivo de descarga asociado.
     */
    public String getHash() {
        return _hash;
    }

/*****************************************************************************/
    /**
     * Establece el valor de hash a valor hash.
     * 
     * @param hash Nuevo valor a establecer.
     */
    public void setHash(String hash) {
        _hash = hash;
    }
}