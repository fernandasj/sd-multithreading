import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Node3 {

    private static DAO dao;

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println("Iniciando Node1");
        dao = new DAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um nome que deseja salvar: ");
        String op = scanner.next();

        while (!op.equals("exit")) {
            dao.salvar(op);
            System.out.println("Digite um nome que deseja salvar: ");
            op = scanner.next();
        }

    }
}
