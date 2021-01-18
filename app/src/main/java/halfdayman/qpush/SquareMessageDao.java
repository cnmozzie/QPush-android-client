package halfdayman.qpush;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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
    LiveData<List<SquareMessage>> getAll();

    @Query("SELECT * FROM squareMessage WHERE id IN (:userIds)")
    LiveData<List<SquareMessage>> loadAllByIds(int[] userIds);

    /**
     * Select a cheese by the ID.
     *
     * @param name The row ID.
     * @return A {@link SquareMessage} of the selected message.
     */
    @Query("SELECT * FROM squareMessage WHERE user_name = :name")
    SquareMessage findByName(String name);

    @Query("SELECT * FROM squareMessage ORDER BY id DESC LIMIT 1")
    SquareMessage findLatestMessage();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneMessage(SquareMessage squareMessage);

    @Insert
    void insertMessages(SquareMessage... squareMessages);

    @Delete
    void delete(SquareMessage squareMessage);

    @Query("DELETE  FROM squareMessage")
    void deleteAllMessages();

    @Query("DELETE FROM squareMessage WHERE id < 0")
    void deleteTemporaryMessages();
}