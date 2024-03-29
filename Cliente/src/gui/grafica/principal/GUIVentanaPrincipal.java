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

package gui.grafica.principal;

import datos.Archivo;
import gestorDeEstadisticas.GestorEstadisticas;
import gestorDeConfiguracion.ControlConfiguracionCliente;
import gestorDeConfiguracion.ControlConfiguracionClienteException;
import gestorDeConfiguracion.PropiedadCliente;
import gestorDeErrores.ControlDeErrores;
import gestorDeFicheros.GestorCompartidos;
import gui.grafica.buscador.ControladorPanelBuscador;
import gui.grafica.buscador.GUIPanelBuscador;
import gui.grafica.compartidos.ControladorPanelCompartidos;
import gui.grafica.compartidos.GUIPanelCompartidos;
import gui.grafica.configuracion.GUIDialogoConfiguracion;
import gui.grafica.estadisticas.GUIPanelEstadisticas;
import gui.grafica.servidores.ControladorPanelServidores;
import gui.grafica.servidores.GUIPanelServidores;
import gui.grafica.splash_screen.ThreadSplash;
import gui.grafica.trafico.*;
import java.awt.event.ActionEvent;
import util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import peerToPeer.EstadoP2P;
import peerToPeer.egorilla.GestorEgorilla;
import peerToPeer.ObservadorP2P;

/**
 * Clase que gestiona la ventana principal de la aplicación.
 * 
 * @author Javier Salcedo, Víctor Adaíl
 */
public class GUIVentanaPrincipal extends JFrame implements ObservadorP2P {

    /**
     * Identificador de la clase. 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Ruta donde se encuentran los recursos necesarios para la ventana principal
     * de la interfaz grafica.
     */
    private static final String RUTA_RECURSOS = "/recursos/interfaz/principal/";    
    /**
     * Boton que muestra el panel buscador.
     */
    private JButton _btnBuscar;
    /**
     * Boton que muestra el panel de compartidos.
     */
    private JButton _btnCompartidos;
    /**
     * Boton que manda la peticion de conexion/desconexion a su controlador
     * para que este se lo comunique al servidor.
     */
    private JButton _btnConectar;
    /**
     * Boton que muestra el panel de configuracion de la aplicacion.
     */
    private JButton _btnConfiguracion;
    /**
     * Boton que muestra el panel de las estadisticas.
     */
    private JButton _btnEstadisticas;
    /**
     * Boton que muestra el panel de servidores.
     */
    private JButton _btnServidores;
    /**
     * Boton que muestra el panel de trafico.
     */
    private JButton _btnTrafico;
    /**
     * Boton que muestra la ventan de ayuda de la aplicacion.
     */
    private JButton _btnAyuda;
    /**
     * Etiqueta que muestra el estado de la conexion (conectado/desconectado).
     */
    private JLabel _lblConexion;
    /**
     * Etiqueta que muestra el estado de la aplicacion.
     */
    private JLabel _lblEstado;
    /**
     * Panel que contiene la etiqueta de estado.
     */
    private JPanel _panelEstado;
    /**
     * Panel que contiene la etiqueta de conexion.
     */
    private JPanel _panelConexion;
    /**
     * Botonera superior que contiene los botones con las acciones principales
     * de la aplicacion.
     */
    private JToolBar _botonera;
    /**
     * Panel principal que muestra cada una de los paneles ante la pulsacion 
     * de su respectivo boton en la botonera.
     */
    private JPanel _panelPrincipal;
    /**
     * Controlador de la ventana principal.
     */
    private ControladorVentanaPrincipal _controlador;
    /**
     * Icono de la ventana principal.
     */
    private ImageIcon _iconoVentana = new ImageIcon( getClass().getResource(RUTA_RECURSOS + "iconos/icono.png") );
    /**
     * Icono del boton de conexion cuando la aplicacion se encuentra desconectada
     * a un servidor.
     */
    private ImageIcon _imgConectar = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnConectar.png"));
    /**
     * Icono del boton de conexion cuando la aplicacion se encuentra negociando con
     * un servidor.
     */
    private ImageIcon _imgNegociando = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnNegociando.png"));
    /**
     * Icono del boton de conexion cuando la aplicacion se encuentra conectada a
     * un servidor.
     */
    private ImageIcon _imgDesconectar = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnDesconectar.png"));
    /**
     * Icono del boton de servidores.
     */
    private ImageIcon _imgServidores = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnServidores.png"));
    /**
     * Icono del boton de busquedas.
     */
    private ImageIcon _imgBuscar = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnBuscar.png"));
    /**
     * Icono del boton de trafico.
     */
    private ImageIcon _imgTrafico = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnTrafico.png"));
    /**
     * Icono del boton de compartidos.
     */
    private ImageIcon _imgCompartidos = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnCompartidos.png"));
    /**
     * Icono del boton de estadisticas.
     */
    private ImageIcon _imgEstadisticas = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnEstadisticas.png"));
    /**
     * Icono del boton de configuracion.
     */
    private ImageIcon _imgConfiguracion = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnConfiguracion.png"));
    /**
     * Icono del boton de ayuda.
     */
    private ImageIcon _imgAyuda = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "botones/btnAyuda.png"));
    /**
     * Estado del sistema P2P.
     */
    private EstadoP2P _estado;
    /**
     * Hilo de Splash dinamico.
     */
    private ThreadSplash hiloSplash;
    /**
     * tray del sistema.
     */
    private SystemTray tray;
    /**
     * Imagen tray conectado.
     */
    private Image imageConectado = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "iconos/iconoCon.png")).getImage();
    /**
     * Imagen tray desconectado.
     */
    private Image imageDesconectado = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "iconos/iconoDes.png")).getImage();
    /**
     * Imagen tray negociando.
     */
    private Image imageNegociando = new ImageIcon(getClass().getResource(RUTA_RECURSOS + "iconos/iconoNeg.png")).getImage();
    /**
     * Tray icon de la aplicacion.
     */
    private TrayIcon trayIcon;

    /**
     * Constructor de la clase VentanaPrincipal.
     * 
     * @param controlador Controlador de la ventana principal. 
     */
    public GUIVentanaPrincipal(ControladorVentanaPrincipal controlador) {

        try {
            _estado = EstadoP2P.DESCONECTADO;
            _controlador = controlador;
            _controlador.getGestorEGorilla().agregarObservador(this);
            _controlador.getGestorEGorilla().getAlmacenDescargas().agregarObservador(GestorEstadisticas.getInstacia());
            
            hiloSplash = new ThreadSplash();
            hiloSplash.run();

            activarTrayIcon();

            iniciarComponentes();
            
        } catch (ControlConfiguracionClienteException ex) {
        
            // Llamada al gestor de errores
        }    
    }

    /**
     * Activa el trayIcon de la aplicacion.
     *
     */
    private void activarTrayIcon() {
        if (SystemTray.isSupported()) {
            trayIcon = new TrayIcon(imageDesconectado, "eGorilla - Desconectado -");
            tray = SystemTray.getSystemTray();

            setPropiedadesTrayIcon(imageDesconectado, "eGorilla - Desconectado -");

            trayIcon.displayMessage("Bienvenido", "Bienvenido a eGorilla", TrayIcon.MessageType.INFO);

        }
    }

    /**
     * Establece las propiedades del trayIcon de la aplicacion.
     * 
     * @param imagen imagen del trayIcon.
     * @param texto Titulo del trayIcon.
     */
    private void setPropiedadesTrayIcon(Image imagen, String texto) {

        try {
            tray.remove(trayIcon);

        } catch (Exception e) {}

        PopupMenu menu = new PopupMenu();

        MenuItem conectarItem = new MenuItem("Conectar/Desconectar");
        conectarItem.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            pulsacionBotonConectar(e);
          }
        });
        menu.add(conectarItem);

        MenuItem ayudaItem = new MenuItem("Ayuda");
        ayudaItem.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            pulsacionBotonAyuda(null);
          }
        });
        menu.add(ayudaItem);

        MenuItem salirItem = new MenuItem("Salir");
        salirItem.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ((GestorEgorilla)_controlador.getGestorEGorilla()).acabarTodo();

            System.exit(0);
          }
        });
        menu.add(salirItem);

        trayIcon = new TrayIcon(imagen, texto, menu);

        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(isVisible()){
                setVisible(false);
            }
            else{
                setVisible(true);
            }
        }
        });

        try {
        tray.add(trayIcon);

        ControlDeErrores.getInstancia().setTray(trayIcon);

        } catch (AWTException e) {}
    }
    /**
     * Crea y configura todos los paneles que se visualizan el panel principal.
     * 
     * @throws gestorDeConfiguracion.ControlConfiguracionClienteException
     * @see gestorDeConfiguracion.ControlConfiguracionClienteException
     */
    private void configurarPanelPrincipal() throws ControlConfiguracionClienteException {

        _panelPrincipal.setPreferredSize(new Dimension(800, 600));
        _panelPrincipal.setLayout(new CardLayout());
        
        // PANEL DE SERVIDORES
        _panelPrincipal.add("Servidores", new GUIPanelServidores(new ControladorPanelServidores(_controlador.getGestorEGorilla())));
        
        // PANEL BUSCADOR
        _panelPrincipal.add("Buscador", new GUIPanelBuscador(new ControladorPanelBuscador(_controlador.getGestorEGorilla())));

        // PANEL DE CONFIGURACION
        //La configuracion va ahora mediante un Cuadro de dialogo.
        
        // PANEL DE TRAFICO
        _panelPrincipal.add("Descargas", new GUIPanelTrafico(new ControladorPanelTrafico(_controlador.getGestorEGorilla())));
        
        // PANEL DE COMPARTIDOS
        _panelPrincipal.add("Compartidos", new GUIPanelCompartidos(new ControladorPanelCompartidos(_controlador.getGestorEGorilla())));
        
        // PANEL DE ESTADISTICAS
        _panelPrincipal.add("Estadisticas", new GUIPanelEstadisticas());

        getContentPane().add(_panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Inicia todos los componentes de la ventana.
     * 
     * @throws gestorDeConfiguracion.ControlConfiguracionClienteException
     * @see gestorDeConfiguracion.ControlConfiguracionClienteException
     */
    private void iniciarComponentes() throws ControlConfiguracionClienteException {

        _panelPrincipal = new JPanel();
        _panelEstado = new JPanel();
        _panelConexion = new JPanel();
        _btnConectar = new JButton();
        _btnServidores = new JButton();
        _btnBuscar = new JButton();
        _btnTrafico = new JButton();
        _btnCompartidos = new JButton();
        _btnEstadisticas = new JButton();
        _btnConfiguracion = new JButton();
        _btnAyuda = new JButton();
        _lblEstado = new JLabel();
        _lblConexion = new JLabel();
        _botonera = new JToolBar();

        setTitle("eGorilla");
        setIconImage(_iconoVentana.getImage());
        setLocationByPlatform(true);
        setMinimumSize(new Dimension(560, 500));

        configurarPanelPrincipal();

        _panelEstado.setName("panelEstado");
        _panelEstado.setLayout(new BorderLayout());

        _lblEstado.setText("Bienvenido a eGorilla");
        _panelEstado.add(_lblEstado, BorderLayout.CENTER);

        _panelConexion.setMinimumSize(new Dimension(40, 20));
        _panelConexion.setPreferredSize(new Dimension(100, 20));

        _lblConexion.setText("Desconectado");
        _panelConexion.add(_lblConexion, BorderLayout.CENTER);

        _panelEstado.add(_panelConexion, BorderLayout.EAST);
        getContentPane().add(_panelEstado, BorderLayout.PAGE_END);

        // BOTONERA
        _botonera.setBorder(BorderFactory.createTitledBorder("Menu"));
        _botonera.setForeground(new Color(235, 233, 237));
        _botonera.setRollover(true);
        _botonera.setAutoscrolls(true);
        _botonera.setDoubleBuffered(true);
        _botonera.setOpaque(false);

        // BOTON CONECTAR
        _btnConectar.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnConectar.setText(dameTxtconexion());
        _btnConectar.setIcon(_imgConectar);
        _btnConectar.setBorderPainted(false);
        _btnConectar.setAlignmentX(0.5F);
        _btnConectar.setDoubleBuffered(true);
        _btnConectar.setFocusCycleRoot(true);
        _btnConectar.setFocusPainted(false);
        _btnConectar.setFocusable(false);
        _btnConectar.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnConectar.setIconTextGap(-5);
        _btnConectar.setMargin(new Insets(0, 10, 0, 10));
        _btnConectar.setPreferredSize(new Dimension(70, 70));
        _btnConectar.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnConectar.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Añadimos el evento de pulsación del ratón
        _btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 pulsacionBotonConectar(evt);
            }
        });
        _botonera.add(_btnConectar);

        // BOTON SERVIDORES
        _btnServidores.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnServidores.setText("Servidores");
        _btnServidores.setIcon(_imgServidores);
        _btnServidores.setBorderPainted(false);
        _btnServidores.setAlignmentX(0.5F);
        _btnServidores.setDoubleBuffered(true);
        _btnServidores.setFocusCycleRoot(true);
        _btnServidores.setFocusPainted(true);
        _btnServidores.setFocusable(true);
        _btnServidores.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnServidores.setIconTextGap(-5);
        _btnServidores.setMargin(new Insets(0, 10, 0, 10));
        _btnServidores.setPreferredSize(new Dimension(70, 70));
        _btnServidores.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnServidores.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Añadimos el evento de pulsación del ratón
        _btnServidores.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                pulsacionBotonServidores(evt);
            }
        });
        _botonera.add(_btnServidores);

        // BOTON BUSCAR
        _btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnBuscar.setText("Buscar");
        _btnBuscar.setIcon(_imgBuscar);
        _btnBuscar.setBorderPainted(false);
        _btnBuscar.setAlignmentX(0.5F);
        _btnBuscar.setDoubleBuffered(true);
        _btnBuscar.setFocusCycleRoot(true);
        _btnBuscar.setFocusPainted(true);
        _btnBuscar.setFocusable(true);
        _btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnBuscar.setIconTextGap(-5);
        _btnBuscar.setMargin(new Insets(0, 10, 0, 10));
        _btnBuscar.setPreferredSize(new Dimension(70, 70));
        _btnBuscar.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnBuscar.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Añadimos el evento de pulsación del ratón
        _btnBuscar.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                pulsacionBotonBuscar(evt);
            }
        });
        _botonera.add(_btnBuscar);

        // BOTON TRAFICO
        _btnTrafico.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnTrafico.setText("Tráfico");
        _btnTrafico.setIcon(_imgTrafico);
        _btnTrafico.setBorderPainted(false);
        _btnTrafico.setAlignmentX(0.5F);
        _btnTrafico.setDoubleBuffered(true);
        _btnTrafico.setFocusCycleRoot(true);
        _btnTrafico.setFocusPainted(true);
        _btnTrafico.setFocusable(true);
        _btnTrafico.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnTrafico.setIconTextGap(-7);
        _btnTrafico.setMargin(new Insets(0, 10, 0, 10));
        _btnTrafico.setPreferredSize(new Dimension(70, 70));
        _btnTrafico.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnTrafico.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Añadimos el evento de pulsación del ratón
        _btnTrafico.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pulsacionBotonTrafico(evt);
            }
        });
        _botonera.add(_btnTrafico);

        // BOTON COMPARTIDOS
        _btnCompartidos.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnCompartidos.setText("Compartidos");
        _btnCompartidos.setIcon(_imgCompartidos);
        _btnCompartidos.setBorderPainted(false); // No pinta los bordes de los botones

        _btnCompartidos.setAlignmentX(0.5F);
        _btnCompartidos.setDoubleBuffered(true);
        _btnCompartidos.setFocusCycleRoot(true);
        _btnCompartidos.setFocusPainted(true);
        _btnCompartidos.setFocusable(true);
        _btnCompartidos.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnCompartidos.setIconTextGap(-5);
        _btnCompartidos.setMargin(new Insets(0, 10, 0, 10));
        _btnCompartidos.setPreferredSize(new Dimension(70, 70));
        _btnCompartidos.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnCompartidos.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Añadimos el evento de pulsación del ratón
        _btnCompartidos.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                pulsacionBotonCompartidos(evt);
            }
        });
        _botonera.add(_btnCompartidos);

        // BOTON ESTADISTICAS
        _btnEstadisticas.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnEstadisticas.setName("btnEstadisticas");
        _btnEstadisticas.setText("Estadísticas");
        _btnEstadisticas.setIcon(_imgEstadisticas);
        _btnEstadisticas.setBorderPainted(false);
        _btnEstadisticas.setAlignmentX(0.5F);
        _btnEstadisticas.setDoubleBuffered(true);
        _btnEstadisticas.setFocusCycleRoot(true);
        _btnEstadisticas.setFocusPainted(true);
        _btnEstadisticas.setFocusable(true);
        _btnEstadisticas.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnEstadisticas.setIconTextGap(-5);
        _btnEstadisticas.setMargin(new Insets(0, 10, 0, 10));
        _btnEstadisticas.setPreferredSize(new Dimension(70, 70));
        _btnEstadisticas.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnEstadisticas.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Añadimos el evento de pulsación del ratón
        _btnEstadisticas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                pulsacionBotonEstadisticas(evt);
            }
        });
        _botonera.add(_btnEstadisticas);

        // BOTON CONFIGURACION
        _btnConfiguracion.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnConfiguracion.setText("Configuración");
        _btnConfiguracion.setIcon(_imgConfiguracion);
        _btnConfiguracion.setBorderPainted(false);
        _btnConfiguracion.setAlignmentX(0.5F);
        _btnConfiguracion.setDoubleBuffered(true);
        _btnConfiguracion.setFocusCycleRoot(true);
        _btnConfiguracion.setFocusPainted(true);
        _btnConfiguracion.setFocusable(true);
        _btnConfiguracion.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnConfiguracion.setIconTextGap(-5);
        _btnConfiguracion.setMargin(new Insets(0, 10, 0, 10));
        _btnConfiguracion.setPreferredSize(new Dimension(70, 70));
        _btnConfiguracion.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnConfiguracion.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Añadimos el evento de pulsación del ratón
        _btnConfiguracion.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    pulsacionBotonConfiguracion(evt);
                } catch (ControlConfiguracionClienteException ex) {
                    Logger.getLogger(GUIVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        _botonera.add(_btnConfiguracion);

        // BOTON AYUDA
        _btnAyuda.setFont(new Font("Tahoma", Font.BOLD, 11));
        _btnAyuda.setText("Ayuda");
        _btnAyuda.setIcon(_imgAyuda);
        _btnAyuda.setBorderPainted(false);
        _btnAyuda.setAlignmentX(0.5F);
        _btnAyuda.setDoubleBuffered(true);
        _btnAyuda.setFocusCycleRoot(true);
        _btnAyuda.setFocusPainted(true);
        _btnAyuda.setFocusable(true);
        _btnAyuda.setHorizontalTextPosition(SwingConstants.CENTER);
        _btnAyuda.setIconTextGap(-5);
        _btnAyuda.setMargin(new Insets(0, 10, 0, 10));
        _btnAyuda.setPreferredSize(new Dimension(70, 70));
        _btnAyuda.setVerticalAlignment(SwingConstants.BOTTOM);
        _btnAyuda.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Añadimos el evento de pulsación del ratón
        _btnAyuda.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                pulsacionBotonAyuda(evt);
            }
        });
        _botonera.add(_btnAyuda);

        getContentPane().add(_botonera, BorderLayout.PAGE_START);

        pack();
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener( new GestorEventoVentana( this, _controlador ) );
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Muestra el panel de servidores en el panel principal de la ventana.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnServidores.
     */
    private void pulsacionBotonServidores(MouseEvent evt) {

        ((CardLayout) _panelPrincipal.getLayout()).show(_panelPrincipal, "Servidores");
    }

    /**
     * Muestra el panel de búsquedas en el panel principal de la ventana.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnBuscar.
     */
    private void pulsacionBotonBuscar(MouseEvent evt) {

        ((CardLayout) _panelPrincipal.getLayout()).show(_panelPrincipal, "Buscador");
    }

    /**
     * Muestra el panel de tráfico en el panel principal de la ventana.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnTrafico.
     */
    private void pulsacionBotonTrafico(MouseEvent evt) {

        ((CardLayout) _panelPrincipal.getLayout()).show(_panelPrincipal, "Descargas");
    }

    /**
     * Muestra el panel de servidores en el panel principal de la ventana.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnServidores.
     */
    private void pulsacionBotonCompartidos(MouseEvent evt) {

        ((CardLayout) _panelPrincipal.getLayout()).show(_panelPrincipal, "Compartidos");
    }

    /**
     * Muestra el panel de estadísticas en el panel principal de la ventana.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnEstadísticas.
     */
    private void pulsacionBotonEstadisticas(MouseEvent evt) {

        ((CardLayout) _panelPrincipal.getLayout()).show(_panelPrincipal, "Estadisticas");
    }

    /**
     * Muestra el panel de configuración en el panel principal de la ventana.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnConfiguracion.
     */
    private void pulsacionBotonConfiguracion(MouseEvent evt) throws ControlConfiguracionClienteException {
    
//        ((CardLayout) _panelPrincipal.getLayout()).show(_panelPrincipal, "Configuracion");
        JDialog dlgConfiguracion = new GUIDialogoConfiguracion (this, "Configuracion de eGorilla", true, ControlConfiguracionCliente.obtenerInstancia());
        dlgConfiguracion.pack();
        dlgConfiguracion.setLocationRelativeTo(this);
        dlgConfiguracion.setVisible(true);        
    }

    /**
     * Muestra el manual de ayuda de la aplicación.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnAyuda.
     */
    private void pulsacionBotonAyuda(MouseEvent evt) {        
        Util.lanzarVisualizadorDefecto("ayuda/index.html");
    }

    /**
     * Cambia el estado del botón conectar. Lo pone al estado Conectar si estaba en Desconectar
     * y viceversa, además de actualizar las etiquetas informativas correspondientes.
     * 
     * @param evt Evento de pulsación del ratón sobre el botón _btnConectar.
     */
    private void pulsacionBotonConectar(ActionEvent evt) {

        try {
            // Si el botón conectar tiene el texto Conectar
            if (EstadoP2P.DESCONECTADO.equals(_estado)) {
                
                // Avisamos al Control de la ventana principal para que realice la acción de conectar con el servidor
                int puerto = Integer.parseInt(ControlConfiguracionCliente.obtenerInstancia().obtenerPropiedad(PropiedadCliente.PUERTO_SERVIDOR.obtenerLiteral()));
                String direccionIP = ControlConfiguracionCliente.obtenerInstancia().obtenerPropiedad(PropiedadCliente.IP_SERVIDOR.obtenerLiteral());
                _controlador.peticionConexionAServidor(direccionIP, puerto);

            } else {   
                // Avisamos al Control de la ventana principal para que realice la acción de desconectar con el servidor
                _controlador.peticionDeDesconexionDeServidor();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión",
                    "Error al conectarse al servidor",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //------------------------------------------\\
    //      INTERFACE OBSERVADOREGORILLA        \\
    //------------------------------------------\\
    
    @Override
    public void cambioEstado(EstadoP2P estado, String direccionIP, int puerto) {
        // La imagen y el texto del botón ahora son Desconectar
        _estado = estado;
        _btnConectar.setText(dameTxtconexion());
        _btnConectar.setIcon(dameIconConexion());
        _btnConectar.setEnabled(!_estado.equals(EstadoP2P.NEGOCIANDO));
        // Cambiamos las etiquetas de estado
        _lblConexion.setText(dameTxtLabelConexion());
        setPropiedadesTrayIcon(dameImagenConexion(), "eGorilla " + dameTxtLabelConexion());
        trayIcon.displayMessage(dameTxtLabelConexion(), "eGorilla " + dameTxtLabelConexion().toLowerCase(), TrayIcon.MessageType.INFO);

    }

//    @Override
//    public void desconexionCompletada(GestorEgorilla gestorEGorilla) {
//        // La imagen y el texto del botón ahora son Desconectar
//        _btnConectar.setText("Conectar");
//        _btnConectar.setIcon(_imgConectar);
//
//        // Cambiamos las etiquetas de estado
//        _lblConexion.setText("Desconectado");
//    }

    @Override
    public void resultadosBusqueda(GestorEgorilla gestorEGorilla, String nombre, Archivo[] lista) {
    }

    @Override
    public void finDescarga(GestorEgorilla gestorEGorilla, Archivo arch) {
       trayIcon.displayMessage("Descarga Completada", "Archivo: "+arch.getNombre(), TrayIcon.MessageType.INFO);
    }

//    @Override
//    public void perdidaConexion(GestorEgorilla gestorEGorilla) {
//    }
    
    
    public class GestorEventoVentana extends WindowAdapter{
      GUIVentanaPrincipal _guiVentanaPrincipal;
      ControladorVentanaPrincipal _controlador;
      GestorEgorilla _gestorEGorilla;

      public GestorEventoVentana( GUIVentanaPrincipal guiVentanaPrincipal, ControladorVentanaPrincipal controlador ){
         _guiVentanaPrincipal = guiVentanaPrincipal;
         _controlador = controlador;
         _gestorEGorilla = (GestorEgorilla)_controlador.getGestorEGorilla();
      }
        
      @Override
      public void windowClosing(WindowEvent e){
          //Hacer un sleep
          _gestorEGorilla.acabarTodo();
          
          System.exit(0);
      }
    }

    @Override
    public void pausaDescarga(GestorEgorilla GestorEGorilla, Archivo arch) {
        //TODO: descarga pausada
    }

    @Override
    public void eliminarDescarga(GestorEgorilla GestorEGorilla, Archivo arch) {
        //TODO DESCARGA ELIMINADA
    }
    
    private String dameTxtconexion() {
        switch (_estado) {
            case CONECTADO : return "Desconectar";
            case DESCONECTADO : return "Conectar";
            case NEGOCIANDO : return "Conectando...";
        }
       return "Conectar";
    }
    private String dameTxtLabelConexion() {
        switch (_estado) {
            case CONECTADO : return "Conectado";
            case DESCONECTADO : return "Desconectado";
            case NEGOCIANDO : return "Conectando...";
        }
       return "Desconectado";
    }
    private ImageIcon dameIconConexion() {
        switch (_estado) {
            case CONECTADO : return _imgDesconectar;
            case DESCONECTADO : return _imgConectar;
            case NEGOCIANDO : return _imgNegociando;
        }
       return _imgConectar;
    }
    private Image dameImagenConexion() {
        switch (_estado) {
            case CONECTADO : return imageConectado;
            case DESCONECTADO : return imageDesconectado;
            case NEGOCIANDO : return imageNegociando;
        }
       return imageDesconectado;
    }
}
