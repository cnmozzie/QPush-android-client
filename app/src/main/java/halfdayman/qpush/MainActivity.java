package halfdayman.qpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // dialogList用于存储数据
    private List<Dialog> dialogList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // 先拿到数据并放在适配器上
        initDialogs();
        DialogAdapter adapter=new DialogAdapter(dialogList,MainActivity.this);

        // 将适配器上的数据传递给listView
        ListView listView=findViewById(R.id.message_list);
        listView.setAdapter(adapter);

        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog=dialogList.get(position);
                Toast.makeText(MainActivity.this,dialog.getTheme(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    // 初始化数据
    private void initDialogs(){
        for(int i=0;i<10;i++){
            dialogList.add(new Dialog(R.mipmap.ic_launcher,"QPush","Hello, thank you for trying QPush."));
            dialogList.add(new Dialog(R.mipmap.ic_launcher,"李四","你好，我是李四"));
            dialogList.add(new Dialog(R.mipmap.ic_launcher,"王五","你好，我是王五"));
        }
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