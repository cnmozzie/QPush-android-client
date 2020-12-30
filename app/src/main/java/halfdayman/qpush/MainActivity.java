package halfdayman.qpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Message> lists;
    private MyAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView = (ListView) findViewById(R.id.dialog_list);
        lists = new ArrayList<>();
        lists.add(new Message(R.mipmap.ic_launcher,"QPush","Hello, thank you for trying QPush."));
        lists.add(new Message(R.mipmap.ic_launcher,"李四","你好，我是李四"));
        lists.add(new Message(R.mipmap.ic_launcher,"王五","你好，我是王五"));
        adapter = new MyAdapter(lists,MainActivity.this);
        listView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Log.v("menu_action","show the app settings UI...");
                return true;

            case R.id.action_new:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Log.v("menu_action","subscribe a new channel...");
                return true;

            case R.id.action_logs:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = new Intent(this, SystemLogsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}