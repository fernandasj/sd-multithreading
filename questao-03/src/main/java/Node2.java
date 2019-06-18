import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2222));
        System.out.println("Node2 iniciado");
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao values = (ValoresOperacao) inputStream.readObject();
            if (values.getOperacao() == 1){
                executaOperacao1(values);
            }else{
                executaOperacao2(values);
            }
            socket.close();
        }
    }

    public  static void executaOperacao1(ValoresOperacao values) throws IOException {
        System.out.println("Chama op1 em Node4");
        Socket socket = new Socket("127.0.0.1", 2224);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(values);
        outputStream.flush();
        socket.close();
    }

    public  static void executaOperacao2(ValoresOperacao values) throws IOException {
        System.out.println("Passando dados da operaçao 2 para Node3");
        Socket socket = new Socket("127.0.0.1", 2223);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(values);
        outputStream.flush();
        socket.close();
    }
}
