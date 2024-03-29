package io.github.fernandavieira.sd.sincronizacaothreads;

import io.github.fernandavieira.sd.sincronizacaothreads.buffer.Buffer;

import java.util.Random;

public class Consumidor implements Runnable {

    private final Buffer buffer;
    private final Random random = new Random();

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i <= 10; i++ ) {
            try {
                Thread.sleep(random.nextInt(3000));
                sum += buffer.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("\nFIM - CONSUMIDOR");
        System.out.printf("\nVALOR TOTAL: %d", sum);
    }
}
