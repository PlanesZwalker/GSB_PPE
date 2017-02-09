package Controlleurs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdd.MyBdd;


public class IndexGSB {

	public static void main(String[] args) throws SQLException {
		
		//  création de l'objet de type Statement 
		String requetePraticien = "SELECT * FROM praticien";
		String requeteRapport = "SELECT * FROM rapportdevisite";
		String requeteMedicament = "SELECT * FROM medicament";
		String requeteOffre = "SELECT * FROM offreechantillon";
		String requeteVisiteur = "SELECT Nom, Prenom FROM visiteurmedical";
		
		Connection con = null;
		con = MyBdd.getInstance();
		
		Statement stmt = con.createStatement();
		
		// execution de la requete SQL de selection de tous les visiteurs medicaux
		ResultSet res;
		
		res = stmt.executeQuery(requeteVisiteur);

		/* String Nom;
		String Prenom;

		recuperation / affichage des résultats de la requete "SELECT Nom, Prenom FROM visiteurmedical";
		while(res.next()){
			Nom = res.getString(1);
			Prenom = res.getString(2);
			System.out.println("Prenom: "+ Prenom + "\nNom: " + Nom);
		}
		*/
		
		
		
		
		
		// liberation des ressources
		stmt.close();
		res.close();
		con.close();
		

	}
}
