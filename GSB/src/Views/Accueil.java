package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Accueil extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public Accueil() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Accueil.class.getResource("/com/sun/java/swing/plaf/motif/icons/TreeClosed.gif")));
		setTitle("GSB - LOGICIEL COMPTE RENDU VISITEURS MEDICAUX");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 610);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeConnecter = new JButton("Se Connecter");
		btnSeConnecter.setBounds(393, 355, 141, 23);
		contentPane.add(btnSeConnecter);
		
		txtNom = new JTextField();
		txtNom.setBounds(393, 179, 141, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(393, 245, 141, 20);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JTextArea txtrBienvenueSurLespace = new JTextArea();
		txtrBienvenueSurLespace.setFont(new Font("Segoe WP Black", Font.BOLD, 18));
		txtrBienvenueSurLespace.setText("BIENVENUE SUR L'ESPACE -COMPTE RENDU- DU LOGICIEL GSB ");
		txtrBienvenueSurLespace.setBounds(173, 40, 666, 29);
		contentPane.add(txtrBienvenueSurLespace);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(393, 297, 141, 23);
		contentPane.add(passwordField);
		
		JTextArea txtrMotDePasse = new JTextArea();
		txtrMotDePasse.setText("MOT DE PASSE");
		txtrMotDePasse.setBounds(393, 276, 108, 23);
		contentPane.add(txtrMotDePasse);
		
		JTextArea txtrPrenom = new JTextArea();
		txtrPrenom.setText("PRENOM");
		txtrPrenom.setBounds(393, 221, 84, 28);
		contentPane.add(txtrPrenom);
		
		JTextArea txtrDedieAuxVisiteurs = new JTextArea();
		txtrDedieAuxVisiteurs.setText("DEDIE AUX VISITEURS MEDICAUX");
		txtrDedieAuxVisiteurs.setFont(new Font("Segoe WP Black", Font.BOLD, 18));
		txtrDedieAuxVisiteurs.setBounds(323, 80, 343, 29);
		contentPane.add(txtrDedieAuxVisiteurs);
		
		JTextArea txtrNom = new JTextArea();
		txtrNom.setText("NOM");
		txtrNom.setBounds(393, 156, 60, 23);
		contentPane.add(txtrNom);
	}
}
