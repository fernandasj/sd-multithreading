import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 2221));
        while(true){
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            List<Integer> numbers = (List<Integer>) inputStream.readObject();
            if(numbers.size() == 1){
                System.out.println("Números iguais!!");
            }else{
                int number1 = numbers.get(0);
                int number2 = numbers.get(1);

                double result = Math.pow(number1,number1) + Math.pow(number2, number2);
                System.out.println("Resultado: "+ result);
            }

        }
    }
}
