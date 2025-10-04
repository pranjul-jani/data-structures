import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PrintOddEven {
    int cap;
    int num;
    Semaphore even;
    Semaphore odd;

    public PrintOddEven(int cap, int num) {
        this.cap = cap;
        this.num = num;
        this.even = new Semaphore(1);
        this.odd = new Semaphore(0);
    }

    public void printEven() {
        try {
            while (true) {
                even.acquire();
                if (num > cap) {
                    odd.release();
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " -> " + num++);
                odd.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOdd() {
        try {
            while (true) {
                odd.acquire();
                if (num > cap) {
                    even.release();
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " -> " + num++);
                even.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintOddEven pe = new PrintOddEven(200, 0);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(() -> pe.printEven());
        ex.submit(() -> pe.printOdd());

        ex.shutdown();

        if(ex.awaitTermination(5, TimeUnit.SECONDS)) {
            ex.shutdownNow();
        }

    }
}
