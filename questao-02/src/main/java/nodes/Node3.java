package nodes;

import object.ValoresOperacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Iniciando Node3");
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2223));

        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ValoresOperacao values = (ValoresOperacao) inputStream.readObject();
            System.out.println("Números recebidos: " + values.getNumero1() + " e " + values.getNumero2());

            if (values.getOperacao() == 2) {
                executaOperacao2(values.getNumero1(), values.getNumero2());
            } else {
                executaOperacao1(values, 2221);
            }
            socket.close();
        }
    }

    public static void executaOperacao2(int number1, int number2) {
        double valor = (2 * number1) / number2;
        System.out.println("Valor da operação 2: " + new BigDecimal(valor).toString());
    }

    public static void executaOperacao1(ValoresOperacao values, int port){

        System.out.println("Chamando op1");

        ObjectOutputStream outputStream = null;
        try {
            Socket socket = new Socket("127.0.0.1", port);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(values);

            socket.close();
        } catch (IOException e) {
            if (port == 2221){
                executaOperacao1(values, 2222);
            }else{
                executaOperacao1(values, 2221);
            }
        }
    }
}
