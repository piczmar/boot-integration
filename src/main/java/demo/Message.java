package demo;

import java.io.Serializable;


public class Message {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    private String text;

    private String selector;
}
