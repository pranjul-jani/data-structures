import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MySemaphore {
    int permits;

    MySemaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        if (this.permits == 0) {
            wait();
        } else {
            this.permits -= 1;
        }
    }

    public synchronized void release() {
        this.permits += 1;
        notifyAll();
    }
}

public class PPAS {

    int num = 10;
    int currNum = 0;
    MySemaphore mySemaphoreEven = new MySemaphore(1);
    MySemaphore mySemaphoreOdd = new MySemaphore(0);

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void printEven() {
        while(true) {
            try{
                mySemaphoreEven.acquire();
                if(currNum > num) {
                    mySemaphoreOdd.release();
                    break;
                }
                System.out.print(num++);
                mySemaphoreOdd.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printOdd() {
        while(true) {
            try{
                mySemaphoreOdd.acquire();
                if(currNum > num) {
                    mySemaphoreEven.release();
                    break;
                }
                System.out.print(num++);
                mySemaphoreEven.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PPAS ppas = new PPAS();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> ppas.printEven());
        executorService.submit(() -> ppas.printOdd());

        while(executorService.awaitTermination(3, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

        System.out.print("Completed");

    }
}


/*

1
2
3
4

5
6
7
8

9
10
11
12

13
14
15
16










 */
