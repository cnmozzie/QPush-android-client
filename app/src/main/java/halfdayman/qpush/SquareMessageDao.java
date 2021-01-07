package halfdayman.qpush;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SquareMessageDao {
    /**
     * Counts the number of cheeses in the table.
     *
     * @return The number of cheeses.
     */
    @Query("SELECT COUNT(*) FROM squareMessage")
    int count();

    @Query("SELECT * FROM squareMessage")
    List<SquareMessage> getAll();

    @Query("SELECT * FROM squareMessage WHERE mid IN (:userIds)")
    List<SquareMessage> loadAllByIds(int[] userIds);

    /**
     * Select a cheese by the ID.
     *
     * @param name The row ID.
     * @return A {@link SquareMessage} of the selected message.
     */
    @Query("SELECT * FROM squareMessage WHERE user_name = :name")
    SquareMessage findByName(String name);

    @Insert
    void insertOneMessage(SquareMessage squareMessage);

    @Insert
    void insertMessages(SquareMessage... squareMessages);

    @Delete
    void delete(SquareMessage squareMessage);

    @Query("DELETE  FROM squareMessage")
    void deleteAllMessages();
}