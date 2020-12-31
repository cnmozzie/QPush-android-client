package halfdayman.qpush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Message> mDatas;

    /**
     * 构造方法
     */
    public MessageAdapter(Context context, List<Message> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView=mInflater.inflate(R.layout.message_item,parent,false);
            viewHolder=new ViewHolder();

            /**
             * 获取子布局中三个控件：ImageView TextView TextView
             */
            viewHolder.mTvTime=convertView.findViewById(R.id.date_time);
            viewHolder.mTvTitle=convertView.findViewById(R.id.textview_title);
            viewHolder.mTvContent=convertView.findViewById(R.id.textview_content);

            convertView.setTag(viewHolder);
        }

        else {

            viewHolder= (ViewHolder) convertView.getTag();
        }

        Message message=mDatas.get(position);
        viewHolder.mTvTime.setText(message.getTime());
        viewHolder.mTvTitle.setText(message.getTitle());
        viewHolder.mTvContent.setText(message.getContent());

        return convertView;
    }

    /**
     * 内部类：可省去findViewById的时间
     */
    public static class ViewHolder {

        TextView mTvTime;
        TextView mTvTitle;
        TextView mTvContent;

    }
}
