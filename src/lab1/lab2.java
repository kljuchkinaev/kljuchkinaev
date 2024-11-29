package lab1;
import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumer {
    private final Queue<Integer> buffer;
    private final int limit;

    public ProducerConsumer(int limit) {
        this.buffer = new LinkedList<>();
        this.limit = limit;
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == limit) {
                    System.out.println("Производитель ждет, буфер заполнен...");
                    wait();
                }
                buffer.add(value);
                System.out.println("Производитель произвел: " + value);
                value++;
                notifyAll();
            }
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    System.out.println("Потребитель ждет, буфер пуст...");
                    wait();
                }
                int value = buffer.remove();
                System.out.println("Потребитель потребил: " + value);
                notifyAll();
            }
            Thread.sleep(1500);
        }
    }
}

class ProducerConsumerDemo {
    public static void main(String[] args) {
        final ProducerConsumer pc = new ProducerConsumer(5); // Размер буфера 5

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
