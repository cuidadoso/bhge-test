package bhge.test;

import bhge.test.model.Message;
import bhge.test.util.TopicCollection;
import org.apache.commons.lang.RandomStringUtils;

import static bhge.test.model.Priotity.*;

public class SystemA extends Thread {
    private final static int IDLE_PERIOD = 3600000;
    private final static int TEXT_LENGTH = 10;

    private final TopicCollection topicCollection;
    private final int messagePerSecond;

    public SystemA(TopicCollection topicCollection, int messagePerSecond) {
        this.topicCollection = topicCollection;
        this.messagePerSecond = messagePerSecond;
    }

    @Override
    public void run() {
        try {
            System.out.printf("<--- SystemA start sending messages.%n%n");
            while (true) {
                long start = System.currentTimeMillis();
                for (int i = 0; i < messagePerSecond; i++) {
                    Message msg = new Message(getPriorityByValue(i), RandomStringUtils.randomAlphabetic(TEXT_LENGTH));
                    Thread.sleep(1000 / messagePerSecond);
                    topicCollection.put(msg);
                    System.out.printf("SystemA sent message (%s) with priority %s.%n", msg.getText(), msg.getPriotity());
                }
                System.out.printf("%n<--- SystemA sent %d messages for %dms.%n%n", messagePerSecond, System.currentTimeMillis() - start);

                System.out.printf("<--- SystemA start idle for hours.%n%n");
                Thread.sleep(IDLE_PERIOD);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
