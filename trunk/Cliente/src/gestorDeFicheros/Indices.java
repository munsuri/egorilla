package gestorDeFicheros;

import java.util.*;
import java.io.*;

import datos.*;

public class Indices implements Serializable{
  private Vector<Fragmento> _indices;

  public Indices( Vector<Fragmento> indices ){
    _indices = indices;
  }

  public Vector<Fragmento> getIndices(){
    return _indices;
  }

  public Fragmento getIndice( int index ){
    return _indices.get( index );
  }

  public boolean contains( Fragmento fragmento ){
    return _indices.contains( fragmento );
  }
}