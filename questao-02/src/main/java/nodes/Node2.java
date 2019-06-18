package nodes;

import object.ValoresOperacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Iniciando Node2");
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2222));

        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao values = (ValoresOperacao) inputStream.readObject();
            System.out.println("Números recebidos: " + values.getNumero1() + " e " + values.getNumero2());
            if(values.getOperacao() == 1){
                executaOperacao1(values.getNumero1(), values.getNumero2());
            }
            socket.close();
        }
    }

    public static void executaOperacao1(int n1, int n2) {

        double total = 2 * n1 * n2;
        BigDecimal bigDecimal = new BigDecimal(total);
        System.out.println("Valor da operação 1: " + bigDecimal.toString());
    }

}
