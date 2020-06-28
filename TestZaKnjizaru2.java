/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testzaknjizaru2;

import fajlovanje.FajlerKnjizara2;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Uros
 */
public class TestZaKnjizaru2 {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "PASS123";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/knjizara2";
    
    
    
    public static void main(String[] args) throws SQLException {
        try {
            Server server = new Server();
            server.testirajServer();
        } catch (Exception ex) {
            Logger.getLogger(TestZaKnjizaru2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
