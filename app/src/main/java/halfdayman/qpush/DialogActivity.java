package halfdayman.qpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends AppCompatActivity {

    private ListView mMsgList;
    private List<Message> mDatas = new ArrayList<>();
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);       //加载图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {   //为图标设置监听器
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initMessages();

    }

    // 初始化数据
    private void initMessages() {
        String url ="https://www.pkumozzie.cn/blade-trap/get?table=messages&orderBy=create_time";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for(int i = 0;i < response.length();i++){

                            try {
                                jsonObject = (JSONObject) response.get(i);
                                Message message = new Message(i,
                                        jsonObject.getString("create_time"),
                                        jsonObject.getString("user_name"),
                                        jsonObject.getString("text"));
                                mDatas.add(message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter=new MessageAdapter(DialogActivity.this, mDatas);

                        mMsgList = findViewById(R.id.message_list);
                        mMsgList.setAdapter(mAdapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Volley Error","That didn't work!");
                    }
                });
        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}