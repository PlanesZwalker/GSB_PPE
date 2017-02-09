package bdd;
/*
 * Si les classes  Statemetnt et ResultSet doivent �tre appel�es on les importes.
 */
//import java.sql.* 
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import javax.swing.JOptionPane;
//import java.sql.Statement;
//import java.sql.ResultSet;

import java.sql.*;
import javax.swing.JOptionPane;


public class MyBdd {
	
	 /** l'objet Connection*/
    private static Connection con = null;
    
    
    public static Connection getCon() {
		return con;
	}


	/** Le driver MySQL*/
    private final String DRIVER = "com.mysql.jdbc.Driver";
    /**URL de connection */
    private final String URL = "jdbc:mysql://localhost:3306/mydb";
    /** Nom de d'un utlisateur d ela base de données */
    private final String USER = "root";
    /**Mot de passe de l'utilisateur*/
    private final String PASSWORD = "";
    /**
     * Constructeur de cette claase.
     * On met sa visibilité à private pour empêcher d'un objet de cette classe
     * soit crée en dehors d'ici
     * @wbp.parser.entryPoint
     */
    private MyBdd()
    {
        try 
        {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL,USER,PASSWORD);  
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null,e.getMessage(),"mydb",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    /**
     * Pour avoir L'objet Connexion lié à notre base de données
     * @return l'objet Connection.
     */
    public static Connection getInstance()
    {
        if(con == null)
            new MyBdd();
        return con;
    }
    
   /* 
	public static void main(String[] args) throws ClassNotFoundException, SQLException // sans traitement des exceptions
	{
		con=getInstance();
		//  cr�ation de l'objet de type Statement 
		String requetePraticien = "SELECT * FROM praticien";
		String requeteRapport = "SELECT * FROM rapportdevisite";
		String requeteMedicament = "SELECT * FROM medicament";
		String requeteOffre = "SELECT * FROM offreechantillon";
		String requeteVisiteur = "SELECT Nom, Prenom FROM visiteurmedical";
		
		Statement stmt = con.createStatement();
		
		// execution de la requete SQL de selection de tous les visiteurs medicaux
		ResultSet res;
		res = stmt.executeQuery(requeteVisiteur);
		
		// recuperation / affichage des r�sultats 
		String Nom;
		String Prenom;

		while(res.next()){
			Nom = res.getString(1);
			Prenom = res.getString(2);
			System.out.println("Prenom: "+ Prenom + "\nNom: " + Nom);
		}
		
		// liberation des ressources
		stmt.close();
		res.close();
		con.close();
		
	}*/
		
		
}
