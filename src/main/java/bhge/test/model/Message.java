package bhge.test.model;

public class Message {
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
}
