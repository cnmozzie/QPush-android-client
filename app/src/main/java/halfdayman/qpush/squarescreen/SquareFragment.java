package halfdayman.qpush.squarescreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;


import halfdayman.qpush.MessageViewModel;
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
    private SquareMessageAdapter mAdapter = null;
    private ListView list_message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        Context mContext = getActivity().getApplicationContext();

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Square");
        toolbar.inflateMenu(R.menu.toolbar_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.action_new:
                        Navigation.findNavController(view).navigate(R.id.action_squareFragment_to_postFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });


        list_message = view.findViewById(R.id.message_list);
        mData = new ArrayList<SquareMessage>();
        mAdapter = new SquareMessageAdapter((List<SquareMessage>) mData, mContext);
        list_message.setAdapter(mAdapter);



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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MessageViewModel model = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);
        model.getMessages().observe(getViewLifecycleOwner(), messages -> {
            // Update the UI.
            mAdapter.update(messages);
            Log.v("message", "adapter updated!");
        });
    }

}