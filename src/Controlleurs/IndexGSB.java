package Controlleurs;


import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Views.Accueil;
import bdd.MyBdd;


public class IndexGSB {

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
		
		
//		VisiteurMedical monVisiteur = new VisiteurMedical();
//		JOptionPane.showMessageDialog(null, monVisiteur);

	}
}
