package cn.huntdog.first.entity;

/**
 * Created by JonDai on 2016/7/12.
 */
public class Greeting {

    private String content;
    public  Greeting(){

    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
