import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 2222));
        while (true) {
            Socket socket = server.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            List<Integer> numbers = (List<Integer>) ois.readObject();
            System.out.println("Recebendo do Node1: ");
            numbers.forEach(System.out::println);

            if (numbers.get(0) != numbers.get(1)) {
                System.out.println("Enviando numeros para o Node3");
                gravaNode3(numbers);
            } else {
                System.out.println("Enviando '0' para o Node3");
                numbers.clear();
                numbers.add(0);
                gravaNode3(numbers);
            }
            socket.close();
        }
    }

    public static void gravaNode3(List<Integer> numbers) throws IOException {
        Socket socket = new Socket("127.0.0.1",2221);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(numbers);
    }
}
