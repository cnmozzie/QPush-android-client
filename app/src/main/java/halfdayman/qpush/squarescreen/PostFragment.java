package halfdayman.qpush.squarescreen;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import halfdayman.qpush.MessageViewModel;
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
        View view = inflater.inflate(R.layout.fragment_post_, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);       //加载图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {   //为图标设置监听器
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_postFragment_to_squareFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);
        // TODO: Use the ViewModel
        Button button = (Button) view.findViewById(R.id.button_send);
        EditText editText = (EditText) view.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager manager = ((InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
                if (manager != null) {
                    manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                mViewModel.postMessage(editText.getText().toString());
                Navigation.findNavController(view).navigate(R.id.action_postFragment_to_squareFragment);
            }
        });
    }

}