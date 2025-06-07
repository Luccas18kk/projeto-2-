import java.sql.*;

public class BuscarProduto {

    // Dados de conexão com o banco de dados
    static final String DB_URL = "jdbc:mysql://localhost:3306/seu_banco";
    static final String DB_USER = "seu_usuario";
    static final String DB_PASS = "sua_senha";

    public static void main(String[] args) {
        int id = 53; // Altere para o ID que você deseja buscar

        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conecta ao banco de dados
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                String sql = "SELECT nome, preco, quantidade FROM Produtos WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                // Verifica se o produto existe
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    double preco = rs.getDouble("preco");
                    int quantidade = rs.getInt("quantidade");

                    System.out.println("Produto encontrado:");
                    System.out.println("Nome: " + nome);
                    System.out.println("Preço: R$ " + preco);
                    System.out.println("Quantidade: " + quantidade);
                } else {
                    System.out.println("Produto com ID " + id + " não encontrado.");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
