package home.java; // Use o mesmo nome da pasta

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletProduto extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/world_pieces";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "sua_senha"; // altere para a senha real

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        String idParam = req.getParameter("id");
        if (idParam == null) {
            res.setStatus(400);
            out.print("{\"erro\":\"ID não fornecido\"}");
            return;
        }

        int id = Integer.parseInt(idParam);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT nome, preco, quantidade FROM Produtos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");

                out.printf("{\"nome\":\"%s\",\"preco\":%.2f,\"quantidade\":%d}", nome, preco, quantidade);
            } else {
                res.setStatus(404);
                out.print("{\"erro\":\"Produto não encontrado\"}");
            }

            conn.close();
        } catch (Exception e) {
            res.setStatus(500);
            out.print("{\"erro\":\"Erro interno: " + e.getMessage() + "\"}");
        }
    }
}
