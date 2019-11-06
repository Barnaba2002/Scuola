/*
 * Connessione al dataBase Biblioteca.accdb e lettura tabella Autori
 */
package gestionebiblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.ucanaccess.jdbc.UcanaccessSQLException;

/**
 *
 * @author Stefano Giacomello
 */

public class GestioneBiblioteca {

    static String str;
    static final String SPAZI = "                    ";
    
    public static void main(String[] args) {
        
        // variabili
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // Step 1: caricamento/registrazione MS Access JDBC driver
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problema di caricamento/registrazione "
                    + "di MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: apertura della connessione con il database
        try {
            String msAccDB = "Biblioteca.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            // Step 2.A: creazione della connessione
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: creazione di un'asserzione
            statement = connection.createStatement();
			/*
			try {
				statement.executeUpdate("CREATE TABLE Utenti (IDUtente integer NOT NULL, Name varchar(20) NOT NULL, primary key(IDUtente))");
			} catch(UcanaccessSQLException e) {
				System.out.println(e.getCause());
			}
			*/
			
			//statement.executeUpdate("INSERT INTO Utenti (IDUtente,Name) VALUES (101,\"Giovanni\")");
			statement.executeUpdate("UPDATE Utenti SET Name=\"Giorgio\" WHERE IDUtente=1");
			statement.executeUpdate("DELETE FROM Utenti WHERE IDUtente=2");
// scorre i record del set visualizzando i dati
            /*while (resultSet.next()) {
                str = String.format("%.7s", resultSet.getInt(1)+SPAZI);
                str = str + String.format("%.18s", resultSet.getString(2)+SPAZI);
                str = str + resultSet.getString(3);
                System.out.println(str);
            }*/
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            
        } finally {
            // Step 3: chiusura della connessione
            try {
                if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			}catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}
