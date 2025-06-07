import java.sql.*;

public class ProdutoDAO {
    private Connection conexao;

    public ProdutoDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/worldpieces", "root", "");
    }

    public Produto buscarPorId(int id) throws Exception {
        Produto p = null;
        String sql = "SELECT * FROM Produtos WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));
            p.setQuantidade(rs.getInt("quantidade"));
        }

        rs.close();
        stmt.close();
        return p;
    }
}

