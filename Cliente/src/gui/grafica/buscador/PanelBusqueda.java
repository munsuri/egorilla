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

package gui.grafica.buscador;

import datos.Archivo;
import gestorDeFicheros.GestorCompartidos;
import gui.grafica.servidores.ObservadorPanelServidores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import mensajes.serverclient.ListaArchivos;
import peerToPeer.descargas.Descarga;
import peerToPeer.descargas.ObservadorAlmacenDescargas;

/**
 * Panel que contiene el resultado de una busqueda.
 * 
 * @author Javier Salcedo
 */
public class PanelBusqueda extends JPanel implements ObservadorAlmacenDescargas {

    /**
     * Lista de archivos que ha respondido el servidor en respuesta a la búsqueda
     */
    private Archivo[] _busqueda;
    /**
     * Lista de paneles de archivos (BusquedaIndividual).
     */
    private ArrayList<BusquedaIndividual> _listaArchivos;
    /**
     * Panel principal del contenedor
     */
    private JPanel _panelPrincipal;
    /**
     * Controlador del panel buscador.
     */
    private ControladorPanelBuscador _controlador;
    /**
     * Vector de observadores de esta clase.
     */
    private Vector<ObservadorPanelBusqueda> _observadores;
    /**
     * Color de selección.
     */
    private Color _colorSeleccion = new Color(102, 204, 255);
    /**
     * Color de fondo del panel.
     */
    private Color _colorFondo = Color.WHITE;
    /**
     * Color archivo descargado incompletado.
     */
    private Color _colorDescargadoIncompleto = Color.RED;
    /**
     * Color archivo descargado completo.
     */
    private Color _colorDescargadoCompleto = Color.GREEN;
    /**
     * Color del borde del panel.
     */
    private Color _colorBorde = Color.BLACK;
    
    /**
     * Constructor de la clase PanelBusqueda.
     * 
     * @param controlador Controlador del panel buscador.
     * @param lista Lista de archivos devuelta por el servidor.
     */
    public PanelBusqueda(ControladorPanelBuscador controlador, Archivo[] lista) {

        _controlador = controlador;
        _busqueda = lista;
        _listaArchivos = new ArrayList<BusquedaIndividual>();
        _observadores = new Vector<ObservadorPanelBusqueda>();
        _panelPrincipal = new JPanel();
        _panelPrincipal.setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(_panelPrincipal, BorderLayout.NORTH);
        iniciarComponentes();
    }

    /**
     * Inicia los componentes del panel.
     */
    public void iniciarComponentes() {

        _panelPrincipal.setLayout(new GridLayout(0, 1, 0, 0));
        _panelPrincipal.add(new Cabecera());

        // Representa los resultados de la búsqueda
        for (int i = 0; i < _busqueda.length; i++) {

            BusquedaIndividual b = new BusquedaIndividual(_busqueda[i]);

            // Guardamos la fila en el ArrayList
            _listaArchivos.add(b);
            // Creamos una nueva fila
            _panelPrincipal.add(b);
        }
    }

    /**
     * Pinta el archivo del color correspondiente.
     * 
     * @param b Archivo asociado a la busqueda.
     * @para color Color a colorear del archivo.
     */
    private void colorearArchivo(BusquedaIndividual b, Color color) {

        b._panelPrincipal.setBackground(_colorFondo);
        b._panelPrincipal.setBackground(_colorFondo);
        b._panelPrincipal.setBorder(BorderFactory.createLineBorder(_colorFondo));
        b.setBorder(BorderFactory.createLineBorder(_colorFondo));
        b._lblNombre.setForeground(color);
        b._lblTamanio.setForeground(color);
        b._lblTipoArchivo.setForeground(color);
        b._lblHash.setForeground(color);

        b._panelPrincipal.repaint();
    }

    /**
     * Cabecera de la tabla donde van a representarse las búsquedas.
     */
    private class Cabecera extends JPanel {

        /**
         * Etiqueta del nombre del archivo.
         */
        private JLabel _lblNombre;
        /**
         * Etiqueta del tamanio del archivo.
         */
        private JLabel _lblTamanio;
        /**
         * Etiqueta del tipo de archivo del archivo.
         */
        private JLabel _lblTipoArchivo;
        /**
         * Etiqueta del hash del archivo.
         */
        private JLabel _lblHash;
        /**
         * Panel principal que contiene a todos los elementos anteriores.
         */
        private JPanel _panelPrincipal;
        /**
         * Color de fuente de la cabecera.
         */
        private Color _colorFuente = Color.WHITE;
        /**
         * Color de fondo de la cabecera.
         */
        private Color _colorFondo = Color.BLUE;
        
        /**
         * Constructor de la clase Cabecera.
         */
        private Cabecera() {

            iniciarComponentes();
        }

        /**
         * Inicia los componentes de la cabecera.
         */
        private void iniciarComponentes() {

            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                    new Color(102, 204, 255),
                    new Color(51, 153, 255),
                    new Color(0, 0, 102),
                    new Color(0, 0, 153)));

            _panelPrincipal = new JPanel();
            _lblNombre = new JLabel("Nombre");
            _lblTamanio = new JLabel("Tamaño");
            _lblTipoArchivo = new JLabel("Tipo");
            _lblHash = new JLabel("Hash");
            _panelPrincipal.setLayout(new GridLayout(0, 4, 25, 25));
            _panelPrincipal.setBackground(_colorFondo);

            _lblNombre.setForeground(_colorFuente);
            _lblTamanio.setForeground(_colorFuente);
            _lblTipoArchivo.setForeground(_colorFuente);
            _lblHash.setForeground(_colorFuente);

            _panelPrincipal.add(_lblNombre);
            _panelPrincipal.add(_lblTamanio);
            _panelPrincipal.add(_lblTipoArchivo);
            _panelPrincipal.add(_lblHash);
            setLayout(new BorderLayout());
            add(_panelPrincipal, BorderLayout.NORTH);
        }
    }

    /**
     * Representa un archivo de la lista devuelta por el servidor.
     */
    private class BusquedaIndividual extends JPanel {

        /**
         * Archivo que representa la búsqueda individual. Lo necesitamos
         * para pasarselo al controlador.
         */
        private Archivo _archivo;
        /**
         * Etiqueta que muestra el nombre del archivo.
         */
        private JLabel _lblNombre;
        /**
         * Etiqueta que muestra el valor del tamanio del archivo.
         */
        private JLabel _lblTamanio;
        /**
         * Etiqueta que muestra el tipo de archivo del archivo.
         */
        private JLabel _lblTipoArchivo;
        /**
         * Etiqueta que muestra el hash del archivo.
         */
        private JLabel _lblHash;
        /**
         * Opción descargar del _popup.
         */
        private JMenuItem _menuDescargar;
        /**
         * Para procesar pulsaciones sobre las opciones del _popup.
         */
        private OyenteBoton _oyenteBoton;
        /*
         * Para procesar los eventos del raton sobre el panel. 
         */
        private EventosRaton _eventosRaton;
        /**
         * Panel principal contenedor.
         */
        private JPanel _panelPrincipal;

        /**
         * Constructor de la clase BusquedaIndividual.
         * 
         * @param nombre Nombre del archivo.
         * @param tamanio Tamanio del archivo en bytes.
         * @param tipo Tipo del archivo.
         * @param hash Hash del archivo.
         */
        private BusquedaIndividual(Archivo archivo) {

            _archivo = archivo;

            _eventosRaton = new EventosRaton();
            _oyenteBoton = new OyenteBoton();

            _panelPrincipal = new JPanel();
            _panelPrincipal.setBorder(BorderFactory.createLineBorder(_colorFondo));
            _panelPrincipal.setBackground(_colorFondo);
            setBackground(_colorFondo);

            _panelPrincipal.addMouseListener(_eventosRaton);
            _lblNombre = new JLabel(_archivo.getNombre());
            _lblNombre.addMouseListener(_eventosRaton);
            _lblTamanio = new JLabel(Long.toString(_archivo.getSize()) + " bytes");
            _lblTamanio.addMouseListener(_eventosRaton);
            _lblTipoArchivo = new JLabel(_archivo.getTipo().name());
            _lblTipoArchivo.addMouseListener(_eventosRaton);
            _lblHash = new JLabel(_archivo.getHash());
            _lblHash.addMouseListener(_eventosRaton);

            iniciarComponentes();

            createPopupMenu();
        }

        private void comprobarSiEstaCompleto() {

            // Compruebo si el archivo esta en mis compartidos y si estan completos para pintarlo en verde
            ListaArchivos descargas = GestorCompartidos.getInstancia().getGestorDisco().getListaArchivosCompletos();
            for (int i = 0; i < descargas.size(); i++) {

                // Si esta descargandose lo pinto en verde
                if (descargas.get(i).getHash().matches(_archivo.getHash())) {

                    colorearArchivo(this, _colorDescargadoCompleto);
                }
            }
        }

        /**
         * Comprueba si el archivo esta descargandose actualmente para
         * mostrarlo de color rojo.
         */
        private void comprobarSiEstaDescargandose() {

            // Compruebo si esta descargandose para pintarlo en rojo
            ArrayList<Descarga> descargas = _controlador.getGestorEGorilla().getAlmacenDescargas().getListaDescargas();
            for (Descarga descarga : descargas) {

                // Si esta descargandose lo pinto en rojo
                if (descarga.getArchivo().getHash().matches(_archivo.getHash())) {

                    colorearArchivo(this, _colorDescargadoIncompleto);
                }
            }
        }

        /**
         * Inicia los componentes de una busqueda individual
         */
        private void iniciarComponentes() {

            _panelPrincipal.setLayout(new GridLayout(0, 4, 25, 5));
            _panelPrincipal.add(_lblNombre);
            _panelPrincipal.add(_lblTamanio);
            _panelPrincipal.add(_lblTipoArchivo);
            _panelPrincipal.add(_lblHash);
            comprobarSiEstaDescargandose();
            comprobarSiEstaCompleto();  
            setLayout(new BorderLayout());
            add(_panelPrincipal, BorderLayout.CENTER);
            _panelPrincipal.repaint();
        }

        /**
         * Crea el menu que aparecera al hacer click con el boton derecho del raton
         * asignando los componentes que apareceran.
         */
        private void createPopupMenu() {

            JPopupMenu popup = new JPopupMenu();

            _menuDescargar = new JMenuItem("Descargar");
            _menuDescargar.addActionListener(_oyenteBoton);
            popup.add(_menuDescargar);

            MouseListener popupListener = new PopupListener(popup);
            _lblNombre.addMouseListener(popupListener);
            _lblTamanio.addMouseListener(popupListener);
            _lblHash.addMouseListener(popupListener);
            _lblTipoArchivo.addMouseListener(popupListener);

            this.addMouseListener(popupListener);
        }

        /**
         * Clase que implementa los métodos necesarios para procesar 
         * eventos producidos sobre los diferentes botones del panel.
         */
        private class OyenteBoton implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getActionCommand().equals("Descargar")) {

                    // Aviso al controlador con el archivo asociado
                    // a la BusquedaIndividual a la que se ha pulsado
                    _controlador.peticionDescargarFichero(_archivo);
                }
            }
        }

        /**
         * Clase que implementa los métodos necesarios para procesar 
         * eventos del raton producidos sobre el panel.
         */
        private class EventosRaton extends MouseAdapter {

            @Override
            public void mouseClicked(MouseEvent evt) {

                if (evt.getClickCount() == 2) {

                    // Aviso al controlador con el archivo asociado
                    // a la BusquedaIndividual a la que se ha pulsado
                    _controlador.peticionDescargarFichero(_archivo);
                } else if (evt.getClickCount() == 1) {

                    repintar();
                    // Solo se queda marcado el que ha sido seleccionado
                    _panelPrincipal.setBackground(_colorSeleccion);
                    _panelPrincipal.setBorder(BorderFactory.createLineBorder(_colorSeleccion));
                    setBorder(BorderFactory.createLineBorder(_colorBorde));
                    _panelPrincipal.repaint();

                    // Avisamos de la seleccion de una fila
                    avisarArchivoSeleccionado(_archivo);
                }
            }
        }

        /**
         * Establece el color blanco de fondo para todos los componentes
         * del panel.
         */
        private void repintar() {

            for (BusquedaIndividual b : _listaArchivos) {

                b._panelPrincipal.setBackground(_colorFondo);
                b.setBorder(BorderFactory.createLineBorder(_colorFondo));
                b._panelPrincipal.setBorder(BorderFactory.createLineBorder(_colorFondo));
                b._panelPrincipal.repaint();
            }
        }

        /**
         * Clase que interpreta los eventos de ratón para mostrar un popup
         * asociado.
         */
        class PopupListener extends MouseAdapter {

            /**
             * Popup del panel.
             */
            private JPopupMenu _popup;

            /**
             * Constructor de la clase gestorEGorilla.
             * 
             * @param popupMenu Popup asociado.
             */
            public PopupListener(JPopupMenu popupMenu) {
                _popup = popupMenu;
            }

            /**
             * Muestra el menu contextual.
             * 
             * @param e Evento del raton.
             */
            private void mostrarMenuRaton(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    _popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mostrarMenuRaton(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mostrarMenuRaton(e);
            }
        }
    }

    /**
     * Aniade un observador a la lista de observadores.
     * 
     * @param observador Nuevo observador a aniadir a la lista.
     */
    public void addObservador(ObservadorPanelBusqueda observador) {

        if (!_observadores.contains(observador)) {
            _observadores.add(observador);
        }

    }

    /**
     * Elimina un observador de la lista de observadores.
     * 
     * @param observador Observador a eliminar de la lista.
     */
    public void eliminaObservador(ObservadorPanelServidores observador) {

        _observadores.remove(observador);
    }

    /**
     * Avisa a todos los observadores registrados en la lista de la 
     * seleccion de un servidor en la lista de servidores.
     * 
     * @param archivo Archivo seleccionado.
     */
    private void avisarArchivoSeleccionado(Archivo archivo) {

        for (ObservadorPanelBusqueda observador : _observadores) {
            observador.archivoSeleccionado(archivo);
        }

    }

    //--------------------------------------\\
    //      INTERFACE ALMACEN DESCARGAS     \\
    //--------------------------------------\\
    @Override
    public void nuevaDescarga(String nombre, String hash, int tamanio) {

        for (BusquedaIndividual b : _listaArchivos) {

            // Ponemos en rojo esa fila cuando ha llegado esa descarga
            if (b._lblHash.getText().matches(hash)) {

                colorearArchivo(b, _colorDescargadoIncompleto);
            }
        }
    }

    @Override
    public void fragmentoDescargado(String hash) {
    }

    @Override
    public void eliminarDescarga(String _hash) {
        // TODO: mirar si conviene hacer algo
    }

    @Override
    public void descargaCompleta(String hash) {
        //TODO DESCARGADO
    }

    @Override
    public void descargaPausada(String hash) {
        //TODO PAUSA DESCARGA
    }
}

