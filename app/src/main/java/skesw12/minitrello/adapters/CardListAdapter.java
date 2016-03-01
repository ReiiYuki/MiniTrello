package skesw12.minitrello.adapters;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class CardListAdapter extends ArrayAdapter<CardList>{
    private Button addCardButton;
    private EditText cardNameInput;
    private EditText cardDescriptionInput;
    private CardAdapter adapter;
    private List<Card> cards;
    public CardListAdapter(Context context, int resource, List<CardList> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cardlist_component, null);
        }
        TextView title = (TextView)v.findViewById(R.id.cardlist_title);
        TextView dateTime = (TextView) v.findViewById(R.id.cardlist_createtime);
        ListView cardListList = (ListView) v.findViewById(R.id.cardlist_list);
        final CardList cardList = getItem(position);
        title.setText(cardList.getTitle());
        dateTime.setText(cardList.getReadableCreatedTime());
        addCardButton = (Button) v.findViewById(R.id.add_card_button);
        cardNameInput = (EditText)v.findViewById(R.id.card_id_input);
        cardNameInput.setVisibility(View.GONE);
        cardDescriptionInput = (EditText)v.findViewById(R.id.card_description_input);
        cardDescriptionInput.setVisibility(View.GONE);
        cards = new ArrayList<Card>();
        for (Card c:cardList) cards.add(c);
        adapter = new CardAdapter(getContext(),R.layout.card,cards);
        cardListList.setAdapter(adapter);
        addCardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vi) {
                addCardButton.setVisibility(View.GONE);
                cardNameInput.setVisibility(View.VISIBLE);
                cardDescriptionInput.setVisibility(View.VISIBLE);
            }
        });
        cardNameInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    cardList.add(new Card(cardNameInput.getText().toString(),cardDescriptionInput.getText().toString()));
                    cards.clear();
                    for (Card c:cardList) cards.add(c);
                    addCardButton.setVisibility(View.VISIBLE);
                    cardNameInput.setVisibility(View.INVISIBLE);
                    cardDescriptionInput.setVisibility(View.INVISIBLE);
                    adapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
        return v;
    }

}
