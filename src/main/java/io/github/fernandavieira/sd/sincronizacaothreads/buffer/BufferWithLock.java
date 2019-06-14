package io.github.fernandavieira.sd.sincronizacaothreads.buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class BufferWithLock implements Buffer {

    private Lock lock = new ReentrantLock(false);
    private Condition podeLer = lock.newCondition();
    private Condition podeEscrever = lock.newCondition();

    private Integer buffer = -1;
    private Boolean empty = true;

    @Override
    public void set(int valor) {
        lock.lock();

        try {
            while (!empty) {
                System.out.println("Produtor tenta gravar, mas o buffer esta cheio");
                podeEscrever.await();
            }

            buffer = valor;
            System.out.println("Produtor grava " + buffer);
            empty = false;
            podeLer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public int get() {
        lock.lock();

        try {

            while (empty) {
                System.out.println("Consumidor tenta consumir, mas o buffer esta vazio");
                podeLer.await();
            }

            empty = true;
            System.out.println("Consumidor le : " + buffer);
            podeEscrever.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return buffer;
    }
}
