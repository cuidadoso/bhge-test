package bhge.test;

import bhge.test.util.TopicCollection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
* Turn two parameters MESSAGES_PER_SECONDS and CONSUMER_NUMBER to check.
* */
public class Main {
    private final static int MESSAGES_PER_SECONDS = 3;
    private final static int CONSUMER_NUMBER = 2;

    public static void main(String[] args) {

        final ExecutorService consumerService = Executors.newFixedThreadPool(CONSUMER_NUMBER);
        TopicCollection topic = new TopicCollection(new PriorityBlockingQueue(), CONSUMER_NUMBER);
        SystemA producer = new SystemA(topic, MESSAGES_PER_SECONDS);
        producer.start();

        for (int i = 0; i < CONSUMER_NUMBER; i++) {
            consumerService.execute(new SystemB(topic, i));
        }
        System.out.println("Producer and Consumer has been started");
        consumerService.shutdown();
    }
}

