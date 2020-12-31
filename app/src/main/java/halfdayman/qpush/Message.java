package halfdayman.qpush;

public class Message {

    private int id;//在整个布局里算第几个Message
    private String time;//标题
    private String title;//标题
    private String  content;//内容

    //有参构造函数
    public Message(int id, String time, String title, String content) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String title) {
        this.title = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
