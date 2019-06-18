import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class Node2 {

    private static int idBanco;
    private static DAO dao;

    public static void main(String[] args) throws InterruptedException, SQLException, IOException {

        System.out.println("Iniciando Node2");
        dao = new DAO();
        idBanco = 0;

        while (true) {
            Entidade entidade = dao.getUltimo();
            if (entidade != null && entidade.getId() > idBanco) {
                idBanco = entidade.getId();
                notificaNode1(entidade);
            }

            Thread.sleep(1000);
        }
    }

    private static void notificaNode1(Entidade entidade) throws IOException {
        System.out.println("Valor inserido no banco, notificando Node1");
        Socket socket = new Socket("127.0.0.1", 2221);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(entidade);
        socket.close();
    }
}
