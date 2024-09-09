package controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadCalc extends  Thread {
    private final int threadId;
    private final Banco db;
    private final Random random = new Random();

    public ThreadCalc(int threadId, Banco db) {
        this.threadId = threadId;
        this.db = db;
    }

    @Override
    public void run() {
        try {
            if (threadId % 3 == 1) {
                A();
            } else if (threadId % 3 == 2) {
                B();
            } else {
                C();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void A() throws InterruptedException {
        System.out.println("Thread " + threadId + " começou A.");
        calculo(0.2, 1.0);
        db.transacao(threadId, 1.0);
        calculo(0.2, 1.0);
        db.transacao(threadId, 1.0);
        System.out.println("Thread " + threadId + " terminou A.");
    }

    public void B() throws InterruptedException {
        System.out.println("Thread " + threadId + " começou B.");
        calculo(0.5, 1.5);
        db.transacao(threadId, 1.5);
        calculo(0.5, 1.5);
        db.transacao(threadId, 1.5);
        calculo(0.5, 1.5);
        db.transacao(threadId, 1.5);
        System.out.println("Thread " + threadId + " terminou B.");
    }

    public void C() throws InterruptedException {
        System.out.println("Thread " + threadId + " começou C.");
        calculo(1.0, 2.0);
        db.transacao(threadId, 1.5);
        calculo(1.0, 2.0);
        db.transacao(threadId, 1.5);
        calculo(1.0, 2.0);
        db.transacao(threadId, 1.5);
        System.out.println("Thread " + threadId + " terminou C.");
    }

    private void calculo(double minSeconds, double maxSeconds) throws InterruptedException {
        double duration = minSeconds + (maxSeconds - minSeconds) * random.nextDouble();
        System.out.println("Thread " + threadId + " está realizando cálculos por " + String.format("%.2f", duration) + " segundos.");
        TimeUnit.MILLISECONDS.sleep((long) (duration * 1000));
    }
}
