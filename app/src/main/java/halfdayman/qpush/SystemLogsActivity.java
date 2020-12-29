package halfdayman.qpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SystemLogsActivity extends AppCompatActivity {

    public static List<String> logList = new CopyOnWriteArrayList<String>();
    private TextView mLogView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_logs);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("System Logs");
        setSupportActionBar(myToolbar);
        mLogView = (TextView) findViewById(R.id.log);
        QPushApplication.setSystemLogsActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLogInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QPushApplication.setSystemLogsActivity(null);
    }

    public void refreshLogInfo() {
        String AllLog = "";
        for (String log : logList) {
            AllLog = AllLog + log + "\n\n";
        }
        mLogView.setText(AllLog);
    }
}