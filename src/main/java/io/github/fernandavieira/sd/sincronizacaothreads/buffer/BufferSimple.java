package io.github.fernandavieira.sd.sincronizacaothreads.buffer;

public class BufferSimple implements Buffer {

    private Integer buffer = -1;


    @Override
    public void set(int value) {
        System.out.println("Produtor grava " + value);
        this.buffer = value;
    }

    @Override
    public int get() {
        System.out.println("Consumidor le " + buffer.toString());
        return buffer;
    }

}
