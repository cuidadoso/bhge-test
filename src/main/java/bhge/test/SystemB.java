package bhge.test;

import bhge.test.model.Message;

import java.util.concurrent.PriorityBlockingQueue;

public class SystemB implements Runnable {
    private final static int COUNT_PER_SECOND = 1;
    private final static int PERIOD = 1000 / COUNT_PER_SECOND;

    private PriorityBlockingQueue<Message> queue;
    private int number;

    public SystemB(PriorityBlockingQueue<Message> queue, int number) {
        this.queue = queue;
        this.number = number;
    }

    public void run() {
        try{
            Message msg;
            System.out.printf("<--- SystemB-%d start receiving messages.%n%n", number);
            while(true){
                msg = queue.take();
                Thread.sleep(PERIOD);
                System.out.printf("SystemB-%d received message (%s) with priority %s.%n", number, msg.getText(), msg.getPriotity());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
