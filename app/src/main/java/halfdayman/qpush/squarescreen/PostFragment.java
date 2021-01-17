package halfdayman.qpush.squarescreen;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import halfdayman.qpush.MessageViewModel;
import halfdayman.qpush.QPushApplication;
import halfdayman.qpush.R;

public class PostFragment extends Fragment {

    private MessageViewModel mViewModel;

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post_fragment, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);       //加载图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {   //为图标设置监听器
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_postFragment_to_squareFragment);
            }
        });

        Button button = (Button) view.findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("button action","send the message!");
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        // TODO: Use the ViewModel
    }

}