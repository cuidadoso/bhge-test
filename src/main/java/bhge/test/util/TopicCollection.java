package bhge.test.util;

import bhge.test.model.Message;

import java.util.concurrent.PriorityBlockingQueue;

public class TopicCollection {
    private PriorityBlockingQueue<CacheMessage> queue;
    private int consumerNumber;

    @SuppressWarnings("unchecked")
    public TopicCollection(PriorityBlockingQueue queue, int consumerNumber) {
        this.queue = queue;
        this.consumerNumber = consumerNumber;
    }

    public void put(Message msg) {
        CacheMessage cacheMessage = new CacheMessage(msg, consumerNumber);
        queue.put(cacheMessage);
    }

    public Message get() throws InterruptedException {
        CacheMessage cacheMessage = queue.take();
        int _count = cacheMessage.getCount() - 1;
        if(_count > 0) {
            queue.put(cacheMessage.setCount(_count));
        }
        return cacheMessage.getMsg();
    }

    private class CacheMessage implements Comparable<CacheMessage> {
        private Message msg;
        private Integer count;

        CacheMessage(Message msg, Integer count) {
            this.msg = msg;
            this.count = count;
        }

        Message getMsg() {
            return msg;
        }

        Integer getCount() {
            return count;
        }

        CacheMessage setCount(Integer count) {
            this.count = count;
            return this;
        }

        public int compareTo(CacheMessage o) {
            return msg.getPriotity().getValue().compareTo(o.getMsg().getPriotity().getValue());
        }
    }
}
