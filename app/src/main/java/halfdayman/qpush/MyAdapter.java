package halfdayman.qpush;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coder-tu on 2016/1/13.
 */
public class MyAdapter extends BaseAdapter {
    private List<Message> Datas;
    private Context mContext;

    public MyAdapter(List<Message> datas, Context mContext) {
        Datas = datas;
        this.mContext = mContext;
    }

    /**
     * 返回item的个数
     * @return
     */
    @Override
    public int getCount() {
        return Datas.size();
    }

    /**
     * 返回每一个item对象
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return Datas.get(i);
    }

    /**
     * 返回每一个item的id
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 暂时不做优化处理，后面会专门整理BaseAdapter的优化
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.list_item,viewGroup,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image1);
        TextView textView1 = (TextView) view.findViewById(R.id.text1);
        TextView textView2 = (TextView) view.findViewById(R.id.text2);
        imageView.setImageResource(Datas.get(i).getImageId());
        textView1.setText(Datas.get(i).getTheme());
        textView2.setText(Datas.get(i).getContent());
//        此处需要返回view 不能是view中某一个
        return view;
    }
}

class Message {
    private String theme = "haha";
    private String content = "hahahaha";
    private int imageId = 0;

    public Message(int image1,String text1, String text2) {
        imageId = image1;
        theme = text1;
        content = text2;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }
}