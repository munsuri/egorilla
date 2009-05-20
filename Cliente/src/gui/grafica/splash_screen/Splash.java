package gui.grafica.splash_screen;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 *
 * @author Victor
 */
public class Splash extends javax.swing.JFrame {

    public static final String RUTA_RECURSOS = "/recursos/interfaz/splash_screen/";    // ATRIBUTOS
    
    /** Creates new form Splash */
    public Splash() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setName("jPanel10"); // NOI18N
        jPanel10.setLayout(new java.awt.CardLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash1.png"))); 
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel1);

        jPanel10.add(jPanel1, "card1");

        jPanel2.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash2.png")))); 
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel2);

        jPanel10.add(jPanel2, "card2");

        jPanel3.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel3.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash3.png")))); 
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel3);

        jPanel10.add(jPanel3, "card3");

        jPanel4.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash4.png")))); 
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel4);

        jPanel10.add(jPanel4, "card4");

        jPanel5.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel5.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash5.png")))); 
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel5);

        jPanel10.add(jPanel5, "card5");

        jPanel6.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel6.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash6.png")))); 
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel6);

        jPanel10.add(jPanel6, "card6");

        jPanel7.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel7.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash7.png")))); 
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(jLabel7);

        jPanel10.add(jPanel7, "card7");

        jPanel8.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel8.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash8.png")))); 
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel8.add(jLabel8);

        jPanel10.add(jPanel8, "card8");

        jPanel9.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel9.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel9.setName("jPanel9"); // NOI18N
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash9.png")))); 
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel9.add(jLabel9);

        jPanel10.add(jPanel9, "card9");

        jPanel11.setName("jPanel11"); // NOI18N
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash10.png")))); 
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jLabel10);

        jPanel10.add(jPanel11, "card10");

        jPanel12.setName("jPanel12"); // NOI18N
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash11.png")))); 
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel12.add(jLabel11);

        jPanel10.add(jPanel12, "card11");

        jPanel13.setName("jPanel13"); // NOI18N
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash12.png")))); 
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel13.add(jLabel12);

        jPanel10.add(jPanel13, "card12");

        jPanel14.setName("jPanel14"); // NOI18N
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon((new ImageIcon(getClass().getResource(RUTA_RECURSOS + "splash13.png")))); 
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel14.add(jLabel13);

        jPanel10.add(jPanel14, "card12");

        getContentPane().add(jPanel10);

        pack();
    }// </editor-fold>

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Splash().setVisible(true);
            }
        });
    }

    public void avanza(){

        splash++;
        CardLayout cl = (CardLayout)(jPanel10.getLayout());
        
        cl.show(jPanel10, "card"+splash);

    }

    public class FondoPanel extends javax.swing.JPanel {
     private Image imgFondo;

     public FondoPanel() {
        preInit();
        initComponents();
     }

        private FondoPanel(String string) {
            imgFondo = (new ImageIcon(getClass().getResource("./resources/splash1.png"))).getImage(); 
        }

     private void initComponents() {
     }

     private void preInit(){

    	 imgFondo = (new ImageIcon(getClass().getResource("./resources/splash1.png"))).getImage(); 

     }

     protected void paintComponent(Graphics g) {

        g.drawImage(imgFondo,0,0,null);

     }

     void pintarImagen(String imagen){

         
         imgFondo = (new ImageIcon(getClass().getResource("./resources/splash2.png"))).getImage(); 
         /*repaint();
         paintComponent(this.getGraphics());*/
     }

    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration
    private int splash = 1;
}