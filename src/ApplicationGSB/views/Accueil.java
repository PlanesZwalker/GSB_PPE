package ApplicationGSB.views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ApplicationGSB.controlleurs.IndexGSB;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTable;

import com.alee.managers.notification.WebNotificationPopup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import com.alee.managers.popup.WebPopup;
import com.alee.managers.popup.PopupStyle;

import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import javax.swing.JTabbedPane;

public class Accueil extends JFrame {

    private JPanel contentPane;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JPasswordField passwordField;
    private JTable table;

    /**
     * Create the frame.
     */
    public Accueil() {

        /*
	 * 		FENETRE
         */
        setIconImage(Toolkit.getDefaultToolkit().getImage(Accueil.class.getResource("/com/sun/java/swing/plaf/motif/icons/TreeClosed.gif")));
        setTitle("GSB - LOGICIEL COMPTE RENDU VISITEURS MEDICAUX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1223, 823);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnProgramme = new JMenu("Programme");
        mnProgramme.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mnProgramme.setIcon(new ImageIcon(Accueil.class.getResource("/com/alee/laf/filechooser/icons/computer.png")));
        menuBar.add(mnProgramme);

        JMenuItem mntmQuitter = new JMenuItem("Quitter");
        mntmQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        mntmQuitter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mntmQuitter.setSelectedIcon(new ImageIcon(Accueil.class.getResource("/com/alee/extended/tab/icons/close.png")));
        mntmQuitter.setIcon(new ImageIcon(Accueil.class.getResource("/com/alee/extended/tab/icons/close-rollover.png")));
        mnProgramme.add(mntmQuitter);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextArea txtrBienvenueSurLespace = new JTextArea();
        txtrBienvenueSurLespace.setEditable(false);
        txtrBienvenueSurLespace.setFont(new Font("Segoe WP Black", Font.BOLD, 18));
        txtrBienvenueSurLespace.setText("BIENVENUE SUR L'ESPACE -COMPTE RENDU- DU LOGICIEL GSB ");
        txtrBienvenueSurLespace.setBounds(173, 11, 621, 29);
        contentPane.add(txtrBienvenueSurLespace);

        /*
		 * 	NOM
		 *
         */
        JTextArea txtrNom = new JTextArea();
        txtrNom.setEditable(false);
        txtrNom.setText("NOM");
        txtrNom.setBounds(894, 109, 60, 23);
        contentPane.add(txtrNom);

        txtNom = new JTextField();
        txtNom.setBounds(894, 132, 141, 20);
        contentPane.add(txtNom);
        txtNom.setColumns(10);

        /*
		 * 	PRENOM
         */
        JTextArea txtrPrenom = new JTextArea();
        txtrPrenom.setEditable(false);
        txtrPrenom.setText("PRENOM");
        txtrPrenom.setBounds(894, 174, 84, 28);
        contentPane.add(txtrPrenom);

        txtPrenom = new JTextField();
        txtPrenom.setBounds(894, 203, 141, 20);
        contentPane.add(txtPrenom);
        txtPrenom.setColumns(10);

        /*
		 * 	MOT DE PASSE
         */
        JTextArea txtrMotDePasse = new JTextArea();
        txtrMotDePasse.setEditable(false);
        txtrMotDePasse.setText("MOT DE PASSE");
        txtrMotDePasse.setBounds(894, 229, 108, 23);
        contentPane.add(txtrMotDePasse);

        passwordField = new JPasswordField();
        passwordField.setBounds(894, 255, 141, 23);
        contentPane.add(passwordField);

        /*
		 * 	BOUTON DE CONNEXION
		 *
         */
        JButton btnSeConnecter = new JButton("Se Connecter");
        btnSeConnecter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                IndexGSB Visiteurs = new IndexGSB();
                JOptionPane.showMessageDialog(null, Visiteurs);
            }
        });
        btnSeConnecter.setBounds(894, 307, 141, 23);
        contentPane.add(btnSeConnecter);

        /*
		 * 		POP UP / NOTIFICATION
         */
        WebNotificationPopup webNotificationPopup = new WebNotificationPopup();
        webNotificationPopup.setBounds(20, 116, 271, 110);
        contentPane.add(webNotificationPopup);
        webNotificationPopup.setContent("BONJOUR \r\nBIENVENUE");
        webNotificationPopup.setToolTipText("Cliquez pour Fermer le message");

        /*
		 * 		POPUP
         */
        WebPopup webPopup = new WebPopup();
        webPopup.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(153, 180, 209), new Color(51, 153, 255), new Color(153, 180, 209), new Color(51, 153, 255)));
        webPopup.setPaintFocus(true);
        webPopup.setPopupStyle(PopupStyle.bevel);
        webPopup.setCloseOnFocusLoss(true);
        webPopup.setAnimated(true);
        webPopup.setLanguageContainerKey("");
        webPopup.setBounds(782, 70, 365, 318);
        contentPane.add(webPopup);

        /*
		 * 		WEB TABBED PANE
         */
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(new Color(211, 211, 211));
        tabbedPane.setBounds(134, 471, 906, 235);
        contentPane.add(tabbedPane);

        /*
        *   MENU A ONGLETS
         */
        WebTabbedPane webTabbedPane = new WebTabbedPane();
        webTabbedPane.setTopBg(SystemColor.info);
        webTabbedPane.setSelectedBottomBg(SystemColor.activeCaption);
        webTabbedPane.setBackground(SystemColor.activeCaption);
        tabbedPane.addTab("HISTORIQUE DES VISITES", null, webTabbedPane, null);
        webTabbedPane.setTabbedPaneStyle(TabbedPaneStyle.attached);

        WebTabbedPane webTabbedPane_2 = new WebTabbedPane();
        tabbedPane.addTab("LABORATOIRES", null, webTabbedPane_2, null);

        WebTabbedPane webTabbedPane_1 = new WebTabbedPane();
        webTabbedPane_1.setBackground(SystemColor.scrollbar);
        tabbedPane.addTab("MEDICAMENTS", null, webTabbedPane_1, null);

        WebTabbedPane webTabbedPane_3 = new WebTabbedPane();
        webTabbedPane_3.setBackground(SystemColor.scrollbar);
        tabbedPane.addTab("PRATICIENS", null, webTabbedPane_3, null);

        WebTabbedPane webTabbedPane_4 = new WebTabbedPane();
        webTabbedPane_4.setBackground(SystemColor.scrollbar);
        tabbedPane.addTab("ECHANTILLONS", null, webTabbedPane_4, null);

        WebTabbedPane webTabbedPane_5 = new WebTabbedPane();
        tabbedPane.addTab("RAPPORTS DE VISITE", null, webTabbedPane_5, null);

        table = new JTable();
        webTabbedPane_5.addTab("LISTE DES RAPPORTS", null, table, null);
        JTextArea txtrDedieAuxVisiteurs = new JTextArea();
        txtrDedieAuxVisiteurs.setBounds(171, 51, 288, 29);
        contentPane.add(txtrDedieAuxVisiteurs);
        txtrDedieAuxVisiteurs.setEditable(false);
        txtrDedieAuxVisiteurs.setText("DEDIE AUX VISITEURS MEDICAUX");
        txtrDedieAuxVisiteurs.setFont(new Font("Segoe WP Black", Font.ITALIC, 14));

    }
}
