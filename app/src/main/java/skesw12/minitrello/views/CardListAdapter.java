package skesw12.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.CardList;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class CardListAdapter extends ArrayAdapter<CardList>{
    List<Card> cards;
    public CardListAdapter(Context context, int resource, List<CardList> objects) {
        super(context, resource, objects);
        cards = new ArrayList<Card>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cardlist_component, null);
        }

        TextView title = (TextView)v.findViewById(R.id.cardlist_title);
        TextView dateTime = (TextView) v.findViewById(R.id.cardlist_createtime);
        ListView cardListList = (ListView) v.findViewById(R.id.cardlist_list);
        CardList cardList = getItem(position);
        title.setText(cardList.getTitle());
        dateTime.setText(cardList.getReadableCreatedTime());
        for (Card card : cardList) cards.add(card);
        //TODO WAIT FOR ADAPTER
        return v;
    }
}
