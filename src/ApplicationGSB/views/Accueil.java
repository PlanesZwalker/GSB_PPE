package ApplicationGSB.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.popup.PopupStyle;
import com.alee.managers.popup.WebPopup;

import ApplicationGSB.controlleurs.IndexGSB;
import java.awt.EventQueue;
import java.sql.SQLException;

public class Accueil extends JFrame {

    // Code for dispatching events from components to event handlers.

    private JPanel contentPane;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JPasswordField passwordField;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTextField textField_15;
    private JTextField textField_16;

    /**
     * Create the frame.
     */
    public Accueil() {

	/*
	 * FENETRE
	 */
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(Accueil.class.getResource("/com/sun/java/swing/plaf/motif/icons/TreeClosed.gif")));
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
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }
	});
	mntmQuitter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	mntmQuitter.setSelectedIcon(new ImageIcon(Accueil.class.getResource("/com/alee/extended/tab/icons/close.png")));
	mntmQuitter
		.setIcon(new ImageIcon(Accueil.class.getResource("/com/alee/extended/tab/icons/close-rollover.png")));
	mnProgramme.add(mntmQuitter);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JTextArea txtrBienvenueSurLespace = new JTextArea();
	txtrBienvenueSurLespace.setBounds(173, 11, 621, 29);
	txtrBienvenueSurLespace.setEditable(false);
	txtrBienvenueSurLespace.setFont(new Font("Segoe WP Black", Font.BOLD, 18));
	txtrBienvenueSurLespace.setText("BIENVENUE SUR L'ESPACE -COMPTE RENDU- DU LOGICIEL GSB ");
	contentPane.add(txtrBienvenueSurLespace);

	/*
	 * NOM
	 *
	 */
	JTextArea txtrNom = new JTextArea();
	txtrNom.setBounds(986, 51, 60, 23);
	txtrNom.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
	txtrNom.setEditable(false);
	txtrNom.setText("Nom");
	contentPane.add(txtrNom);

	txtNom = new JTextField();
	txtNom.setBounds(986, 74, 141, 20);
	contentPane.add(txtNom);
	txtNom.setColumns(10);

	/*
	 * PRENOM
	 */
	JTextArea txtrPrenom = new JTextArea();
	txtrPrenom.setBounds(986, 116, 84, 28);
	txtrPrenom.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
	txtrPrenom.setEditable(false);
	txtrPrenom.setText("Pr\u00E9nom");
	contentPane.add(txtrPrenom);

	txtPrenom = new JTextField();
	txtPrenom.setBounds(986, 145, 141, 20);
	contentPane.add(txtPrenom);
	txtPrenom.setColumns(10);

	/*
	 * MOT DE PASSE
	 */
	JTextArea txtrMotDePasse = new JTextArea();
	txtrMotDePasse.setBounds(986, 171, 108, 23);
	txtrMotDePasse.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
	txtrMotDePasse.setEditable(false);
	txtrMotDePasse.setText("Mot de passe");
	contentPane.add(txtrMotDePasse);

	passwordField = new JPasswordField();
	passwordField.setBounds(986, 197, 141, 23);
	contentPane.add(passwordField);

	/*
	 * BOUTON DE CONNEXION
	 *
	 */
	JButton btnSeConnecter = new JButton("Se Connecter");
	btnSeConnecter.setBounds(986, 249, 141, 23);
	btnSeConnecter.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
	btnSeConnecter.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {

		IndexGSB Visiteurs = new IndexGSB();
		JOptionPane.showMessageDialog(null, Visiteurs);
	    }
	});
	contentPane.add(btnSeConnecter);

	/*
	 * POPUP
	 */
	WebPopup webPopup = new WebPopup();
	webPopup.setBounds(934, 11, 263, 316);
	webPopup.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(153, 180, 209), new Color(51, 153, 255),
		new Color(153, 180, 209), new Color(51, 153, 255)));
	webPopup.setPaintFocus(true);
	webPopup.setPopupStyle(PopupStyle.bevel);
	webPopup.setCloseOnFocusLoss(true);
	webPopup.setAnimated(true);
	webPopup.setLanguageContainerKey("");
	contentPane.add(webPopup);

	/*
	 * WEB TABBED PANE
	 */
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(20, 103, 858, 631);
	tabbedPane.setBackground(new Color(211, 211, 211));
	contentPane.add(tabbedPane);

	/*
	 * MENU A ONGLETS
	 */

	WebTabbedPane webTabbedPane_5 = new WebTabbedPane();
	tabbedPane.addTab("RAPPORTS DE VISITE", null, webTabbedPane_5, null);

	JLayeredPane layeredPane_1 = new JLayeredPane();
	webTabbedPane_5.addTab("Liste des rapports de visites", null, layeredPane_1, null);
	webTabbedPane_5.setBackgroundAt(0, SystemColor.scrollbar);

	JButton button_4 = new JButton();
	button_4.setText("New");
	button_4.setBounds(332, 325, 71, 23);
	layeredPane_1.add(button_4);

	JButton button_5 = new JButton();
	button_5.setText("Delete");
	button_5.setBounds(409, 325, 71, 23);
	layeredPane_1.add(button_5);

	JButton button_6 = new JButton();
	button_6.setText("Refresh");
	button_6.setBounds(486, 325, 71, 23);
	layeredPane_1.add(button_6);

	JButton button_7 = new JButton();
	button_7.setText("Save");
	button_7.setBounds(563, 325, 71, 23);
	layeredPane_1.add(button_7);

	JLabel label_2 = new JLabel();
	label_2.setText("Idrapport:");
	label_2.setBounds(145, 146, 109, 14);
	layeredPane_1.add(label_2);

	JLabel label_3 = new JLabel();
	label_3.setText("Motifvisite:");
	label_3.setBounds(145, 172, 112, 14);
	layeredPane_1.add(label_3);

	JLabel label_4 = new JLabel();
	label_4.setText("Bilan:");
	label_4.setBounds(144, 198, 86, 14);
	layeredPane_1.add(label_4);

	JLabel label_5 = new JLabel();
	label_5.setText("Daterapport:");
	label_5.setBounds(145, 224, 122, 14);
	layeredPane_1.add(label_5);

	JLabel label_6 = new JLabel();
	label_6.setText("Idvisiteur:");
	label_6.setBounds(145, 250, 108, 14);
	layeredPane_1.add(label_6);

	JLabel label_7 = new JLabel();
	label_7.setText("Idpraticien:");
	label_7.setBounds(145, 276, 114, 14);
	layeredPane_1.add(label_7);

	JLabel label_8 = new JLabel();
	label_8.setText("Idechantillon:");
	label_8.setBounds(145, 302, 124, 14);
	layeredPane_1.add(label_8);

	textField_2 = new JTextField();
	textField_2.setBounds(273, 143, 361, 20);
	layeredPane_1.add(textField_2);

	textField_3 = new JTextField();
	textField_3.setBounds(273, 169, 361, 20);
	layeredPane_1.add(textField_3);

	textField_4 = new JTextField();
	textField_4.setBounds(273, 195, 361, 20);
	layeredPane_1.add(textField_4);

	textField_5 = new JTextField();
	textField_5.setBounds(273, 247, 361, 20);
	layeredPane_1.add(textField_5);

	textField_6 = new JTextField();
	textField_6.setBounds(273, 273, 361, 20);
	layeredPane_1.add(textField_6);

	textField_7 = new JTextField();
	textField_7.setBounds(273, 299, 361, 20);
	layeredPane_1.add(textField_7);

	JFormattedTextField formattedTextField = new JFormattedTextField();
	formattedTextField.setToolTipText("");
	formattedTextField.setText("01-02-2017");
	formattedTextField.setBounds(273, 221, 122, 20);
	layeredPane_1.add(formattedTextField);

	WebTabbedPane webTabbedPane_1 = new WebTabbedPane();
	webTabbedPane_1.setBackground(SystemColor.scrollbar);
	tabbedPane.addTab("MEDICAMENTS", null, webTabbedPane_1, null);

	JLayeredPane layeredPane_4 = new JLayeredPane();
	webTabbedPane_1.addTab("Liste des m\u00E9dicaments", null, layeredPane_4, null);

	JButton button_8 = new JButton();
	button_8.setText("New");
	button_8.setBounds(482, 178, 63, 23);
	layeredPane_4.add(button_8);

	JButton button_9 = new JButton();
	button_9.setText("Delete");
	button_9.setBounds(551, 178, 63, 23);
	layeredPane_4.add(button_9);

	JButton button_10 = new JButton();
	button_10.setText("New");
	button_10.setBounds(312, 320, 71, 23);
	layeredPane_4.add(button_10);

	JButton button_11 = new JButton();
	button_11.setText("Delete");
	button_11.setBounds(389, 320, 71, 23);
	layeredPane_4.add(button_11);

	JButton button_12 = new JButton();
	button_12.setText("Refresh");
	button_12.setBounds(466, 320, 71, 23);
	layeredPane_4.add(button_12);

	JButton button_13 = new JButton();
	button_13.setText("Save");
	button_13.setBounds(543, 320, 71, 23);
	layeredPane_4.add(button_13);

	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(184, 65, 430, 107);
	layeredPane_4.add(scrollPane_1);

	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(184, 207, 430, 107);
	layeredPane_4.add(scrollPane_2);

	WebTabbedPane webTabbedPane_3 = new WebTabbedPane();
	webTabbedPane_3.setBackground(SystemColor.scrollbar);
	tabbedPane.addTab("PRATICIENS", null, webTabbedPane_3, null);

	JLayeredPane layeredPane_5 = new JLayeredPane();
	webTabbedPane_3.addTab("Liste des praticiens", null, layeredPane_5, null);

	JButton button_14 = new JButton();
	button_14.setText("New");
	button_14.setBounds(479, 216, 63, 23);
	layeredPane_5.add(button_14);

	JButton button_15 = new JButton();
	button_15.setText("Delete");
	button_15.setBounds(548, 216, 63, 23);
	layeredPane_5.add(button_15);

	JButton button_16 = new JButton();
	button_16.setText("New");
	button_16.setBounds(309, 358, 71, 23);
	layeredPane_5.add(button_16);

	JButton button_17 = new JButton();
	button_17.setText("Delete");
	button_17.setBounds(386, 358, 71, 23);
	layeredPane_5.add(button_17);

	JButton button_18 = new JButton();
	button_18.setText("Refresh");
	button_18.setBounds(463, 358, 71, 23);
	layeredPane_5.add(button_18);

	JButton button_19 = new JButton();
	button_19.setText("Save");
	button_19.setBounds(540, 358, 71, 23);
	layeredPane_5.add(button_19);

	JScrollPane scrollPane_3 = new JScrollPane();
	scrollPane_3.setBounds(181, 103, 430, 107);
	layeredPane_5.add(scrollPane_3);

	JScrollPane scrollPane_4 = new JScrollPane();
	scrollPane_4.setBounds(181, 245, 430, 107);
	layeredPane_5.add(scrollPane_4);

	WebTabbedPane webTabbedPane_4 = new WebTabbedPane();

	webTabbedPane_4.setBackground(SystemColor.scrollbar);
	tabbedPane.addTab("ECHANTILLONS", null, webTabbedPane_4, null);

	JLayeredPane layeredPane = new JLayeredPane();
	webTabbedPane_4.addTab("Ajouter un Echantillon", null, layeredPane, null);

	JButton button = new JButton();
	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	button.setText("Ajouter");
	button.setBounds(651, 78, 89, 23);
	layeredPane.add(button);

	JButton button_1 = new JButton();
	button_1.setText("Supprimer");
	button_1.setBounds(44, 78, 117, 23);
	layeredPane.add(button_1);

	JButton button_2 = new JButton();
	button_2.setText("Modifier");
	button_2.setBounds(331, 316, 126, 23);
	layeredPane.add(button_2);

	JButton button_3 = new JButton();
	button_3.setText("Enregistrer");
	button_3.setBounds(481, 316, 146, 23);
	layeredPane.add(button_3);

	JLabel label = new JLabel();
	label.setText("Quantite:");
	label.setBounds(141, 267, 102, 14);
	layeredPane.add(label);

	JLabel label_1 = new JLabel();
	label_1.setText("Medicament:");
	label_1.setBounds(141, 293, 117, 14);
	layeredPane.add(label_1);

	textField = new JTextField();
	textField.setBounds(262, 264, 365, 20);
	layeredPane.add(textField);

	textField_1 = new JTextField();
	textField_1.setBounds(262, 290, 365, 20);
	layeredPane.add(textField_1);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(197, 61, 430, 197);
	layeredPane.add(scrollPane);

	WebTabbedPane webTabbedPane_6 = new WebTabbedPane();
	webTabbedPane_6.setBackground(SystemColor.scrollbar);
	tabbedPane.addTab("VISITEURS MEDICAUX", null, webTabbedPane_6, null);

	JLayeredPane layeredPane_6 = new JLayeredPane();
	webTabbedPane_6.addTab("Liste des visiteurs m\u00E9dicaux", null, layeredPane_6, null);

	JButton btnNouveau = new JButton();
	btnNouveau.setText("Nouveau");
	btnNouveau.setBounds(688, 79, 92, 23);
	layeredPane_6.add(btnNouveau);

	JButton btnSupprimer = new JButton();
	btnSupprimer.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	btnSupprimer.setText("Supprimer");
	btnSupprimer.setBounds(255, 342, 134, 23);
	layeredPane_6.add(btnSupprimer);

	JButton btnModifier = new JButton();
	btnModifier.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	btnModifier.setText("Modifier");
	btnModifier.setBounds(688, 133, 92, 23);
	layeredPane_6.add(btnModifier);

	JButton btnEnregistrer = new JButton();
	btnEnregistrer.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	btnEnregistrer.setText("Enregistrer");
	btnEnregistrer.setBounds(487, 342, 142, 23);
	layeredPane_6.add(btnEnregistrer);

	JLabel label_9 = new JLabel();
	label_9.setText("Idvisiteur:");
	label_9.setBounds(141, 111, 107, 14);
	layeredPane_6.add(label_9);

	JLabel label_10 = new JLabel();
	label_10.setText("Nom:");
	label_10.setBounds(141, 137, 83, 14);
	layeredPane_6.add(label_10);

	JLabel label_11 = new JLabel();
	label_11.setText("Motdepasse:");
	label_11.setBounds(141, 163, 120, 14);
	layeredPane_6.add(label_11);

	JLabel label_12 = new JLabel();
	label_12.setText("Prenom:");
	label_12.setBounds(141, 189, 98, 14);
	layeredPane_6.add(label_12);

	JLabel label_13 = new JLabel();
	label_13.setText("Adresse:");
	label_13.setBounds(141, 215, 101, 14);
	layeredPane_6.add(label_13);

	JLabel label_14 = new JLabel();
	label_14.setText("Ville:");
	label_14.setBounds(141, 241, 80, 14);
	layeredPane_6.add(label_14);

	JLabel label_15 = new JLabel();
	label_15.setText("Secteur:");
	label_15.setBounds(141, 267, 99, 14);
	layeredPane_6.add(label_15);

	JLabel label_16 = new JLabel();
	label_16.setText("Labo:");
	label_16.setBounds(141, 293, 85, 14);
	layeredPane_6.add(label_16);

	JLabel label_17 = new JLabel();
	label_17.setText("Email:");
	label_17.setBounds(141, 319, 86, 14);
	layeredPane_6.add(label_17);

	textField_8 = new JTextField();
	textField_8.setBounds(265, 108, 364, 20);
	layeredPane_6.add(textField_8);

	textField_9 = new JTextField();
	textField_9.setBounds(265, 134, 364, 20);
	layeredPane_6.add(textField_9);

	textField_10 = new JTextField();
	textField_10.setBounds(265, 160, 364, 20);
	layeredPane_6.add(textField_10);

	textField_11 = new JTextField();
	textField_11.setBounds(265, 186, 364, 20);
	layeredPane_6.add(textField_11);

	textField_12 = new JTextField();
	textField_12.setBounds(265, 212, 364, 20);
	layeredPane_6.add(textField_12);

	textField_13 = new JTextField();
	textField_13.setBounds(265, 238, 364, 20);
	layeredPane_6.add(textField_13);

	textField_14 = new JTextField();
	textField_14.setBounds(265, 264, 364, 20);
	layeredPane_6.add(textField_14);

	textField_15 = new JTextField();
	textField_15.setBounds(265, 290, 364, 20);
	layeredPane_6.add(textField_15);

	textField_16 = new JTextField();
	textField_16.setBounds(265, 316, 364, 20);
	layeredPane_6.add(textField_16);

	JScrollPane scrollPane_5 = new JScrollPane();
	scrollPane_5.setBounds(199, 75, 430, 27);
	layeredPane_6.add(scrollPane_5);
	JTextArea txtrDedieAuxVisiteurs = new JTextArea();
	txtrDedieAuxVisiteurs.setBounds(171, 51, 288, 29);
	contentPane.add(txtrDedieAuxVisiteurs);
	txtrDedieAuxVisiteurs.setEditable(false);
	txtrDedieAuxVisiteurs.setText("DEDIE AUX VISITEURS MEDICAUX");
	txtrDedieAuxVisiteurs.setFont(new Font("Segoe WP Black", Font.ITALIC, 14));

    }

    public static void main(String[] args) throws SQLException {

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Accueil frame = new Accueil();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});

	// VisiteurMedical monVisiteur = new VisiteurMedical();
	// JOptionPane.showMessageDialog(null, monVisiteur);

    }

}
