package bhge.test;

import bhge.test.model.Message;
import org.apache.commons.lang.RandomStringUtils;

import java.util.concurrent.PriorityBlockingQueue;

import static bhge.test.model.Priotity.*;

public class SystemA extends Thread {
    private final static int COUNT_PER_SECOND = 10;
    private final static int SEND_PERIOD = 1000 / COUNT_PER_SECOND;
    private final static int IDLE_PERIOD = 3600000;
    private final static int TEXT_LENGTH = 10;

    private PriorityBlockingQueue<Message> queue;

    public SystemA(PriorityBlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.printf("<--- SystemA start sending messages.%n%n");
            while (true) {
                long start = System.currentTimeMillis();
                for (int i = 0; i < COUNT_PER_SECOND; i++) {
                    Message msg = new Message(getPriorityByValue(i), RandomStringUtils.randomAlphabetic(TEXT_LENGTH));
                    Thread.sleep(SEND_PERIOD);
                    queue.put(msg);
                    System.out.printf("SystemA sent message (%s) with priority %s.%n", msg.getText(), msg.getPriotity());
                }
                System.out.printf("%n<--- SystemA sent %d messages for %dmc.%n%n", COUNT_PER_SECOND, System.currentTimeMillis() - start);

                System.out.printf("<--- SystemA start idle for hours.%n%n");
                Thread.sleep(IDLE_PERIOD);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
