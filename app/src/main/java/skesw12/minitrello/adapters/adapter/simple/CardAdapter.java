package skesw12.minitrello.adapters.adapter.simple;

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
 * Created by YukiReii on 1/3/2559.
 */
public class CardAdapter extends ArrayAdapter<Card> {
    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.simple_cardlist_component, null);
        }
        Card cardList = getItem(position);
        TextView title = (TextView)v.findViewById(R.id.simple_cardlist_title);
        title.setText(cardList.getName());
        return v;
    }
}
