class TimerThread extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (running) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Время с начала сессии: " + elapsedTime + " секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopTimer() {
        running = false;
    }
}

class MessageThread extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Сообщение каждые 5 секунд!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopMessage() {
        running = false;
    }
}

class MessageThread7Sec extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Сообщение каждые 7 секунд!");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopMessage() {
        running = false;
    }
}
 class TimeSessionApp {
    public static void main(String[] args) {
        TimerThread timer = new TimerThread();
        MessageThread message5Sec = new MessageThread();
        MessageThread7Sec message7Sec = new MessageThread7Sec();

        timer.start();
        message5Sec.start();
        message7Sec.start();

        try {
            Thread.sleep(20000);  // Даем этим потокам работать 20 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.stopTimer();
        message5Sec.stopMessage();
        message7Sec.stopMessage();
    }
}
