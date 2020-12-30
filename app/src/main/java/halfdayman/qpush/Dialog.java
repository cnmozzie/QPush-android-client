package halfdayman.qpush;

public class Dialog {
    private String theme = "haha";
    private String content = "hahahaha";
    private int imageId = 0;

    public Dialog(int image1,String text1, String text2) {
        imageId = image1;
        theme = text1;
        content = text2;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }
}

