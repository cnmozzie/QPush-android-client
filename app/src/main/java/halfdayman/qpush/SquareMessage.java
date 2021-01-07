package halfdayman.qpush;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SquareMessage {
    @PrimaryKey(autoGenerate = true)
    public int mid;

    @ColumnInfo(name = "user_name")
    public String user_name;

    @ColumnInfo(name = "message_content")
    public String message_content;

    public SquareMessage(String user_name, String message_content) {
        this.user_name = user_name;
        this.message_content = message_content;
    }

}
