package bhge.test.model;

public class Message implements Comparable<Message> {
    private Priotity priotity;
    private String text;

    public Message(Priotity priotity, String text) {
        this.priotity = priotity;
        this.text = text;
    }

    public Priotity getPriotity() {
        return priotity;
    }

    public String getText() {
        return text;
    }

    public int compareTo(Message o) {
        return priotity.getValue().compareTo(o.getPriotity().getValue());
    }
}
