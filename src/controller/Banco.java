package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Banco {
    private final Semaphore semaforo;

    public Banco() {
        this.semaforo = new Semaphore(1);
    }

    public void transacao(int threadId, double duration) throws InterruptedException {
        semaforo.acquire();
        try {
            System.out.println("Thread " + threadId + " está realizando transação no banco de dados por " + duration + " segundos.");
            TimeUnit.SECONDS.sleep((long) duration);
        } finally {
            semaforo.release();
        }
    }
}
