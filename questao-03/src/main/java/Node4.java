import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Node4 iniciado");
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2224));

        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao values = (ValoresOperacao) inputStream.readObject();
            if(values.getOperacao() == 1){
                executaOperacao1(values);
            }else{
                executaOperacao2(values);
            }
            socket.close();
        }
    }

    public static void executaOperacao1(ValoresOperacao values) {
        int soma = values.getNumero1() + values.getNumero2();
        System.out.println("Resultado da Op1: "+ soma);
    }

    public static void executaOperacao2(ValoresOperacao values) {
        int subtrai = values.getNumero1() - values.getNumero2();
        System.out.println("Resultado da Op2: "+ subtrai);
    }
}
