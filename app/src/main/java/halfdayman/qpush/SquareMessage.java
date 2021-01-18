package halfdayman.qpush;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SquareMessage {

    @PrimaryKey
    public int id;

    public String user_name;
    public String message_content;

    public SquareMessage(int id, String user_name, String message_content) {
        this.id = id;
        this.user_name = user_name;
        this.message_content = message_content;
    }

}
