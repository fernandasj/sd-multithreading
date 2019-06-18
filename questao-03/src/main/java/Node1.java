import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Node1 {

    private static Random random;

    public static void main(String[] args) throws IOException {

        System.out.println("Node1 iniciado");
        random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Operação 1 ou 2?");
        int opt = scanner.nextInt();

        while (opt > 0 && opt < 3) {
            if (opt == 1) {
                gravaNodes(2223, 1);
            } else {
                gravaNodes(2222, 2);
            }
            System.out.println("Operação 1 ou 2?");
            opt = scanner.nextInt();
        }
    }

    public static void gravaNodes(int port, int operation) throws IOException {
        Socket socket = new Socket("127.0.0.1", port);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(numbers(operation));
        outputStream.flush();
        socket.close();
    }

    public static ValoresOperacao numbers(int operation) {
        ValoresOperacao values = new ValoresOperacao();
        values.setNumero1(random.nextInt(10));
        values.setNumero2(random.nextInt(10));
        values.setOperacao(operation);

        return values;
    }
}
