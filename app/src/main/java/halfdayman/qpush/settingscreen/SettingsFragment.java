package halfdayman.qpush.settingscreen;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import halfdayman.qpush.QPushApplication;
import halfdayman.qpush.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public static List<String> logList = new CopyOnWriteArrayList<String>();
    private TextView mLogView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");

        mLogView = (TextView) view.findViewById(R.id.logView);
        Log.v("important","new log view");
        QPushApplication.setSettingsFragment(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLogInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QPushApplication.setSettingsFragment(null);
    }

    public void refreshLogInfo() {
        String AllLog = "System Logs:\n\n";
        for (String log : logList) {
            AllLog = AllLog + log + "\n\n";
        }
        mLogView.setText(AllLog);
    }
}