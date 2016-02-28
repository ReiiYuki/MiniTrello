package skesw12.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.models.Card;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class CardAdapter extends ArrayAdapter<Card>{

    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.card, null);
        }
        TextView title = (TextView)v.findViewById(R.id.card_title);
        TextView dateTime = (TextView) v.findViewById(R.id.card_createTime);
        Card cardList = getItem(position);
        title.setText(cardList.getName());
        dateTime.setText(cardList.getReadableCreatedTime());
        return v;
    }
}
