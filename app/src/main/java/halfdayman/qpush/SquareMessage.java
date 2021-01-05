package halfdayman.qpush;

public class SquareMessage {
    private String aName;
    private String aSpeak;

    public SquareMessage() {
    }

    public SquareMessage(String aName, String aSpeak) {
        this.aName = aName;
        this.aSpeak = aSpeak;
    }

    public String getaName() {
        return aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }

}
