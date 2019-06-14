package io.github.fernandavieira.sd.sincronizacaothreads.buffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferWithBlockingQueue implements Buffer {

    private final BlockingQueue<Integer> queue;

    public BufferWithBlockingQueue() {
        this.queue = new ArrayBlockingQueue<>(3);
    }

    @Override
    public void set(int valor) {
        try {
            queue.put(valor);
            System.out.println("Produtor grava " + valor);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int get() {
        int valorLido = 0;

        try {
            valorLido = queue.take();
            System.out.println("Consumidor le " + valorLido);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return valorLido;
    }
}
