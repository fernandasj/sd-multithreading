package main;

import entity.Usuario;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Node1 {

    private static Socket socket;

    public static void main(String[] args) throws IOException {

        System.out.println("Iniciando Node1");
        gravaComThread();
    }


    public static void grava100() throws IOException {

        long time = System.currentTimeMillis();
        for (int k = 1; k <= 100; k++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Cem " + k);
            gravaNode2(usuario);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Tempo total: " + time / 1000 + " segundos");
    }

    public static void grava1000() throws IOException {

        long time = System.currentTimeMillis();
        for (int k = 1; k <= 1000; k++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Mil " + k);
            gravaNode2(usuario);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Tempo total: " + time / 1000 + " segundos");
    }

    public static void gravaComThread() throws IOException {

        long time = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Mil " + k);
            new GravaThread(usuario).start();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Tempo total: " + time / 1000 + " segundos");
    }

    public static void gravaNode2(Usuario usuario) throws IOException {
        socket = new Socket("127.0.0.1", 2222);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(usuario);
        byte[] b = new byte[10];
        socket.getInputStream().read(b);
        System.out.println("Recebido: " + new String(b));
        socket.close();
    }


    private static class GravaThread extends Thread {

        public Usuario usuario;

        public GravaThread(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public void run() {
            try {
                System.out.println("thread");
                for (int k = 1; k <= 100; k++) {
                    gravaNode2(usuario);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


