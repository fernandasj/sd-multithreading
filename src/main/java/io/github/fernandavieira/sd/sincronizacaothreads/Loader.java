package io.github.fernandavieira.sd.sincronizacaothreads;

import io.github.fernandavieira.sd.sincronizacaothreads.buffer.Buffer;
import io.github.fernandavieira.sd.sincronizacaothreads.buffer.BufferWithCicleBuffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Buffer buffer = new BufferWithCicleBuffer();

        executorService.execute(new Produtor(buffer));
        executorService.execute(new Consumidor(buffer));

        executorService.shutdown();
    }
}
