package halfdayman.qpush.squarescreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

import halfdayman.qpush.Dialog;
import halfdayman.qpush.DialogActivity;
import halfdayman.qpush.DialogAdapter;
import halfdayman.qpush.Message;
import halfdayman.qpush.R;
import halfdayman.qpush.SquareMessage;
import halfdayman.qpush.SquareMessageAdapter;
import halfdayman.qpush.SquareMessageDao;
import halfdayman.qpush.SquareMessageDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {

    private List<SquareMessage> mData = null;
    private Context mContext;
    private SquareMessageAdapter mAdapter = null;
    private ListView list_message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_square, container, false);

        mContext = getActivity().getApplicationContext();
        list_message = view.findViewById(R.id.message_list);
        mData = new ArrayList<SquareMessage>();
        mAdapter = new SquareMessageAdapter((List<SquareMessage>) mData, mContext);
        list_message.setAdapter(mAdapter);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //mContext.deleteDatabase("square_message_database"); // for test only, should remove later
                SquareMessageDao message = SquareMessageDatabase.getInstance(mContext).squareMessage();
                mData =  message.getAll();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<mData.size(); i++) {
                            mAdapter.add(mData.get(i));
                        }
                    }
                });

            }
        });


        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        list_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SquareMessage message=mData.get(position);
                Log.v("message", message.user_name);
            }
        });

        return view;
    }

}