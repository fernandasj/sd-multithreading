package main;

import database.Conexao;
import entity.Usuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {

    private static Conexao conexao;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        conexao = new Conexao();

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 2222));
        System.out.println("Iniciando Node2");
        
        
        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Usuario usuario = (Usuario) inputStream.readObject();
            conexao.salvarPostgre(usuario);
            conexao.salvarPostgre2(usuario);
            System.out.println("Usuário salvo nos bancos de dados");

            socket.getOutputStream().write(usuario.getNome().getBytes());
        }
    }
}
