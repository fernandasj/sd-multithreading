import object.ValoresOperacao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {

    private static Random random;

    public static void main(String[] args) throws IOException {
        random = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o node desejado(1, 2 ou 3)");
        int node = scanner.nextInt();
        System.out.println("Informe a operação desejada(1 ou 2)");
        int operation = scanner.nextInt();

        while(node <4 && node > 0){
            // 2221 = nodes.Node1  |  2222 = nodes.Node2 | 2223 = nodes.Node3
            Socket socket = new Socket("127.0.0.1",2220 + node);

            System.out.println("Realiza operação no Node: "+ node);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(geraOperacao(operation));

            socket.close();
            System.out.println("Iforme o node desejado(1, 2 ou 3)");
            node = scanner.nextInt();
            System.out.println("Informe a operação desejada(1 ou 2 )");
            operation = scanner.nextInt();
        }
    }

    public static ValoresOperacao geraOperacao(int operation){

        ValoresOperacao values = new ValoresOperacao();
        values.setNumero1(random.nextInt(10));
        values.setNumero2(random.nextInt(10));
        values.setOperacao(operation);
        return  values;
    }
}
