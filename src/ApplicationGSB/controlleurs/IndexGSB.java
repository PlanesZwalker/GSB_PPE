package ApplicationGSB.controlleurs;


import ApplicationGSB.views.Login;
import ApplicationGSB.views.Accueil;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class IndexGSB {

	public static void main(String[] args) throws SQLException {
	 
           Login.main(args);
        }
        
        public static int connect(){
            
            Login monLogin = new Login();
            
            if(monLogin.connectVisiteur()== true){
                
                Accueil monAccueil = new Accueil();
                monAccueil.setVisible(true);
      
                return 1;
                
            }else{
                
                return 0;
                
            }
            
        }
}
