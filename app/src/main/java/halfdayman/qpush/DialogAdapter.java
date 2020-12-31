package halfdayman.qpush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by coder-tu on 2016/1/13.
 */
public class DialogAdapter extends ArrayAdapter<Dialog> {

    int resourceId=R.layout.dialog_item;

    public DialogAdapter(List<Dialog> datas, Context mContext) {
        super(mContext, R.layout.dialog_item, datas);
    }

    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Dialog dialog= (Dialog) getItem(position); //获取当前项的Fruit实例

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view=LayoutInflater.from(getContext()).inflate(resourceId, parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new ViewHolder();
            viewHolder.imageView=view.findViewById(R.id.image1);
            viewHolder.titleView=view.findViewById(R.id.text1);
            viewHolder.textView=view.findViewById(R.id.text2);

            // 将ViewHolder存储在View中（即将控件的实例存储在其中）
            view.setTag(viewHolder);
        }
        else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        // 获取控件实例，并调用set...方法使其显示出来
        viewHolder.imageView.setImageResource(dialog.getImageId());
        viewHolder.titleView.setText(dialog.getTheme());
        viewHolder.textView.setText(dialog.getContent());
        return view;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    class ViewHolder{
        ImageView imageView;
        TextView titleView;
        TextView textView;
    }

}
