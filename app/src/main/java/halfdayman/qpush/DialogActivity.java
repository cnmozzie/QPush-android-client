package halfdayman.qpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
        mAdapter=new MessageAdapter(this,mDatas);

        mMsgList = findViewById(R.id.message_list);
        mMsgList.setAdapter(mAdapter);
    }

    // 初始化数据
    private void initMessages() {
        for(int i=0;i<5;i++){
            Message message = new Message(1+i*5,
                    "12月27日 上午11:56",
                    "如何才能不错过人工智能的时代？",
                    "下一个时代就是机器学习的时代，慕课网发大招，与你一起预见未来！");
            mDatas.add(message);

            message = new Message(2+i*5,
                    "12月27日 上午11:56",
                    "关于你的面试、实习心路历程",
                    "奖品丰富，更设有参与奖，随机抽取5名幸运用户，获得慕课网付费面试课程中的任意一门！");
            mDatas.add(message);

            message = new Message(3+i*5,
                    "12月27日 上午11:56",
                    "狗粮不是你想吃，就能吃的！",
                    "你的朋友圈开始了吗？一半秀恩爱，一半扮感伤！不怕，还有慕课网陪你坚强地走下去！！");
            mDatas.add(message);

            message = new Message(4+i*5,
                    "12月27日 上午11:56",
                    "前端跳槽面试那些事儿",
                    "工作有几年了，项目偏简单有点拿不出手怎么办？ 目前还没毕业，正在自学前端，请问可以找到一份前端工作吗，我该怎么办？");
            mDatas.add(message);

            message = new Message(5+i*5,
                    "12月27日 上午11:56",
                    "图解程序员怎么过七夕？",
                    "哈哈哈哈，活该单身25年！");
            mDatas.add(message);
        }
    }
}