package halfdayman.qpush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class SquareMessageAdapter extends BaseAdapter {
    private LinkedList<SquareMessage> mData;
    private Context mContext;

    public SquareMessageAdapter(LinkedList<SquareMessage> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.square_message_item, parent, false);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.textview_title);
        TextView txt_aSpeak = (TextView) convertView.findViewById(R.id.textview_content);
        txt_aName.setText(mData.get(position).getaName());
        txt_aSpeak.setText(mData.get(position).getaSpeak());
        return convertView;
    }
}
