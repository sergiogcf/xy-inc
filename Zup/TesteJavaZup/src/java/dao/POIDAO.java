package dao;

import conn.Conexao;
import modelo.POI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * .: ZUP - Teste Desenvolvedor JR :.
 * Autor: Sérgio Guimarães Costa Filho
 * Data: 25/09/2019
 */
public class POIDAO {

    public String CadastrarPOI(String nome, Integer coordenadaX, Integer coordenadaY) {
        // Verifica se o campo coordenadaX é inteiro e não negativo.
        if (coordenadaX < 0) {
            return "O campo [coordenadaX] não pode ser negativo.";
        }

        // Verifica se o campo coordenadaY é inteiro e não negativo.
        if (coordenadaY < 0) {
            return "O campo [coordenadaY] não pode ser negativo.";
        }

        Conexao cn = new Conexao();
        Connection conn = cn.getConexao();

        try {

            PreparedStatement preparedStmt = conn.prepareStatement("INSERT INTO TbPoi (nome, coordenadaX, coordenadaY) VALUES (?, ?, ?)");
            preparedStmt.setString(1, nome);
            preparedStmt.setInt(2, coordenadaX);
            preparedStmt.setInt(3, coordenadaY);

            preparedStmt.execute();
            preparedStmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(POIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Dados inseridos com sucesso!";
    }

    public List<POI> ListarPOI() {
        List<POI> listaPOI = new ArrayList<POI>();

        Conexao cn = new Conexao();
        Connection conn = cn.getConexao();

        try {
            Statement stm;
            stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT TbPoi.id, TbPoi.nome, TbPoi.coordenadaX, TbPoi.coordenadaY FROM TbPoi ORDER BY TbPoi.nome;");

            while (rs.next()) {
                POI p = new POI();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCoordenadaX(rs.getInt("CoordenadaX"));
                p.setCoordenadaY(rs.getInt("CoordenadaY"));

                listaPOI.add(p);
            }
            stm.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(POIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaPOI;
    }

    public List<String> ListarPOIProximidade(int coordenadaX, int coordenadaY, int distancia) {
        List<String> listaPOI = new ArrayList();

        Conexao cn = new Conexao();
        Connection conn = cn.getConexao();

        try {
            Statement stm;
            stm = conn.createStatement();

            // Query realizando busca de proximidade através do cálculo de Teorema de Pitágoras.
            ResultSet rs = stm.executeQuery("SELECT TbAux.nome, TbAux.distancia FROM (SELECT TbPoi.id, TbPoi.nome, TbPoi.coordenadaX, TbPoi.coordenadaY, (Sqrt(((" + coordenadaX + "-TbPoi.coordenadaX) * (" + coordenadaX + "-TbPoi.coordenadaX)) + ((" + coordenadaY + "-TbPoi.coordenadaY) * (" + coordenadaY + "-TbPoi.coordenadaY)))) distancia FROM TbPoi) TbAux WHERE TbAux.distancia < " + distancia + " order by TbAux.nome;");

            while (rs.next()) {
                listaPOI.add(rs.getString("nome"));
            }
            stm.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(POIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaPOI;
    }
}