package halfdayman.qpush.homescreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import halfdayman.qpush.Dialog;
import halfdayman.qpush.DialogActivity;
import halfdayman.qpush.DialogAdapter;
import halfdayman.qpush.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogList extends Fragment {

    // dialogList用于存储数据
    private List<Dialog> dialogList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialogs, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("QPush");
        toolbar.inflateMenu(R.menu.toolbar_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.action_new:
                        Log.v("menu_action","add a new channel...");
                        return true;
                    default:
                        return false;
                }
            }
        });

        // 先拿到数据并放在适配器上
        initDialogs();
        DialogAdapter adapter=new DialogAdapter(dialogList, getActivity().getApplicationContext());

        // 将适配器上的数据传递给listView
        ListView listView=view.findViewById(R.id.message_list);
        listView.setAdapter(adapter);

        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog=dialogList.get(position);
                Toast.makeText(getActivity().getApplicationContext(),dialog.getTheme(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), DialogActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // 初始化数据
    private void initDialogs(){
        for(int i=0;i<10;i++){
            dialogList.add(new Dialog(R.mipmap.ic_launcher,"QPush","Hello, thank you for trying QPush."));
            dialogList.add(new Dialog(R.mipmap.ic_launcher,"李四","你好，我是李四"));
            dialogList.add(new Dialog(R.mipmap.ic_launcher,"王五","你好，我是王五"));
        }
    }
}