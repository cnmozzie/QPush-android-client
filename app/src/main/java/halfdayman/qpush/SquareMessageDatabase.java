package halfdayman.qpush;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SquareMessage.class}, version = 1, exportSchema = false)
public abstract class SquareMessageDatabase extends RoomDatabase {


    /**
     * @return The DAO for the Cheese table.
     */
    @SuppressWarnings("WeakerAccess")
    public abstract SquareMessageDao squareMessage();

    /** The only instance */
    private static SquareMessageDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized SquareMessageDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), SquareMessageDatabase.class, "square_message_database")
                    .build();
            sInstance.populateInitialData();
        }
        return sInstance;
    }

    /**
     * Switches the internal implementation with an empty in-memory database.
     *
     * @param context The context.
     */
    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                SquareMessageDatabase.class).build();
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private void populateInitialData() {
        if (squareMessage().count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    squareMessage().insertOneMessage(new SquareMessage("本地消息", "欢迎来到广场！"));
                    squareMessage().insertOneMessage(new SquareMessage("狗说", "你是狗么?"));
                    squareMessage().insertOneMessage(new SquareMessage("鸭说", "你是鸭么?"));
                    squareMessage().insertOneMessage(new SquareMessage("马说", "你是马么?"));
                }
            });
        }
    }
}