package gestorDeFicheros;

import java.util.*;
import mensajes.serverclient.*;
import datos.*;

class ManejarListaArchivos {
  
  protected Archivo buscarArchivoEnLista( ListaArchivos listaArchivos, String hash){
    boolean encontrado = false;
    Archivo encontradoArchivo = null;
    
    for( int i = 0;  i < listaArchivos.size() && encontrado == false;  i++ ){
      encontradoArchivo = listaArchivos.get( i );
      //System.out.println("Comparo hashA:"+encontradoArchivo.getHash()+" con hashB:"+hash);
      if( comparaHash( encontradoArchivo.getHash(), hash ) == true )
        encontrado = true;
    }
    if( encontrado == false ){
      //Sino lo ha encontrado devuelvo un nulo
      encontradoArchivo = null;
    }//Sino no tengo que cambiar nada
 
    return encontradoArchivo;
  }

  protected boolean comparaHash( String hashA, String hashB){
    if( hashA.compareTo( hashB ) == 0 )
      return true;
    else
      return false;
  }

  protected void includirArchivoEnLista( Archivo archivo, ListaArchivos lista ){
    lista.add( archivo );
  }

  protected void eliminarArchivoDeLista( Archivo archivo, ListaArchivos lista ){
    //System.out.println( lista.remove( archivo ) );
    Archivo archAux = null;
    boolean encontrado = false;

    for( int i = 0;  i < lista.size() && encontrado == false;  i++ ){
      archAux = lista.get( i );
      //System.out.println("Comparo <"+fragAux.getOffset()+"> con <"+fragmento.getOffset()+">");
      if( archAux.equals( archivo ) == true ){
        //System.out.println("Eliminoooooo");
        //_indicesFaltan.remove( fragAux ); no elimina asi, no se xq
        lista.remove( i );
        encontrado = true;
      }
    }
  }

  protected void moverArchivoEntreListas( Archivo archivo, ListaArchivos listaA, ListaArchivos listaB ){
    //Un archivo de la lista A se elimina, pasando a la listaB
    listaA.remove( archivo );
    listaB.add( archivo );
  }
 

  public ListaArchivos unirListas( ListaArchivos listaA, ListaArchivos listaB ){
    ListaArchivos listaNueva = new ListaArchivos();

    for( int i = 0;  i < listaA.size();  i++ ){
      listaNueva.add( listaA.get( i ) );
    }

    for( int i = 0;  i < listaB.size();  i++ ){
      listaNueva.add( listaB.get( i ) );
    }

    return listaNueva;
  }


  public void recorrerListaArchivos( ListaArchivos lista ){
    if( lista == null ){
      System.out.println("Lista nula. 0 ficheros.");
    }else{
    for( int i = 0;  i < lista.size();  i++) {
      //System.out.println( lista.elementAt(i).getNombre() );

      //System.out.print( lista.elementAt(i).getNombre() );
      //System.out.println( " - "+lista.elementAt(i).getHash() );
      
      System.out.println( lista.elementAt(i).toString() );
    }
    System.out.println("\n<" + lista.size() + "> ficheros.");
    }
  }
}
