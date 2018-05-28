package bhge.test;

import bhge.test.model.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    private final static int CONSUMER_NUMBER = 1;

    public static void main(String[] args) {

        final ExecutorService consumerService = Executors.newFixedThreadPool(CONSUMER_NUMBER);
        PriorityBlockingQueue<Message> queue = new PriorityBlockingQueue<Message>();
        SystemA producer = new SystemA(queue);
        producer.start();

        for (int i = 0; i < CONSUMER_NUMBER; i++) {
            consumerService.execute(new SystemB(queue, i));
        }
        consumerService.shutdown();
        System.out.println("Producer and Consumer has been started");
    }
}

