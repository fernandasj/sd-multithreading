import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Node1 {
    public static void main(String[] args) throws IOException {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um número maior que 0");
        int opt = scanner.nextInt();

        while(opt != 0){
            Socket socket = new Socket("127.0.0.1", 2222);
            int number1 = random.nextInt(100);
            int number2 = random.nextInt(100);
            List<Integer> numbers = new ArrayList<>();
            numbers.add(number1);
            numbers.add(number2);

            System.out.println("Enviando a lista de números para o Node2");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(numbers);

            socket.close();
            System.out.println("Digite um número maior que 0");
            opt = scanner.nextInt();
        }
    }
}
