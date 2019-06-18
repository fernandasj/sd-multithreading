import java.sql.*;

public class DAO {

    private Connection conn;

    public DAO() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/atividade-sd",
                "postgres",
                "flavio22"
        );
    }

    public void salvar(String nome) throws SQLException {
        String sql = "INSERT INTO QUESTAO05 (nome, lida) values (?, false);";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.execute();
        stmt.close();
    }

    public Entidade getUltimo() throws SQLException {
        String sql = "SELECT * FROM QUESTAO05 WHERE LIDA = FALSE ORDER BY ID DESC LIMIT 1";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        Entidade entidade = null;
        if (rs.next()) {
            entidade = new Entidade(
                    rs.getString("Nome"),
                    rs.getInt("id")
            );
            String update = "UPDATE QUESTAO05 SET LIDA = TRUE WHERE id = ?;";
            PreparedStatement newStmt = conn.prepareStatement(update);
            newStmt.setInt(1, entidade.getId());
            newStmt.execute();
            newStmt.close();
        }
        rs.close();
        stmt.close();
        return entidade;
    }
}
