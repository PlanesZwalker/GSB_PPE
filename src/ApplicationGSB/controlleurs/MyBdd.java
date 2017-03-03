package ApplicationGSB.controlleurs;


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
    private final String URL = "jdbc:mysql://localhost:3306/gsb_ppe";
    /** Nom de d'un utilisateur de la base*/
    private final String USER = "root";
    /** Mot de passe de l'utilisateur*/
    private final String PASSWORD = "";
    
    /**
     * Constructeur de cette classe.
     * On met sa visibilite a private pour empecher qu'un objet de cette classe
     * soit cree en dehors d'ici
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
     * 
     * @return l'objet Connection.
     */
    public static Connection getInstance()
    {
        if(con == null)
            new MyBdd();
        return con;
    }
  
		
		
}
