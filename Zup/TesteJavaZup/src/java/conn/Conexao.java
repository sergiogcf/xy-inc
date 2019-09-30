package conn;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * .: ZUP - Teste Desenvolvedor JR :.
 * Autor: Sérgio Guimarães Costa Filho
 * Data: 25/09/2019
 */
public class Conexao {

    Connection conn;
    String URL = "jdbc:mysql://localhost/bdTesteZup";
    String usuario = "usuarioZup";
    String senha = "Zup123";

    public Connection getConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection(URL, usuario, senha);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;

    }
}