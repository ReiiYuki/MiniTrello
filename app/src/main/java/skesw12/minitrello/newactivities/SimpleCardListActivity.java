package skesw12.minitrello.newactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import skesw12.minitrello.R;
import skesw12.minitrello.adapters.adapter.simple.CardAdapter;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;

public class SimpleCardListActivity extends AppCompatActivity {
    private CardList cards;
    private EditText card_name_input,card_description_input;
    private LinearLayout card_input_panel;
    private Button addCardButton,removeCardListButton;
    private ListView cardListView;
    private CardAdapter cardAdapter;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_cardlist);
        getCardList();
        initComponent();
        setCardAdapter();
        setAllListener();
        refresh();
    }
    public void getCardList(){
        position = (Integer)getIntent().getSerializableExtra("position");
        cards = Storage.getInstance().loadCardLists().get(position);
    }
    public void initComponent(){
        TextView createTimeLab = (TextView) findViewById(R.id.simple_cardlist_createtime);
        createTimeLab.setText("Created Date : "+cards.getReadableCreatedTime());
        card_name_input = (EditText)findViewById(R.id.simple_card_name_input);
        card_description_input = (EditText) findViewById(R.id.simple_card_description_input);
        addCardButton = (Button) findViewById(R.id.simple_add_card_button);
        addCardButton.setVisibility(View.VISIBLE);
        cardListView = (ListView) findViewById(R.id.simple_card_list);
        cardAdapter = new CardAdapter(this,R.layout.simple_cardlist_component,cards);
        card_input_panel = (LinearLayout) findViewById(R.id.simple_add_card_panel);
        removeCardListButton = (Button) findViewById(R.id.simple_remove_cardlist_button);
    }
    public void setCardAdapter(){
        cardListView.setAdapter(cardAdapter);
    }
    public void setAllListener(){
        removeCardListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().removeCardList(cards);
                finish();
            }
        });
        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position2, long id) {
                Intent intent = new Intent(SimpleCardListActivity.this,SimpleCardActivity.class);
                intent.putExtra("position1",position);
                intent.putExtra("position2",position2);
                startActivity(intent);
            }
        });
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_input_panel.setVisibility(View.VISIBLE);
                addCardButton.setVisibility(View.GONE);
            }
        });
        card_name_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    card_input_panel.setVisibility(View.GONE);
                    addCardButton.setVisibility(View.VISIBLE);
                    if (card_name_input.getText().toString().equals("")) return true;
                    addCard();
                    card_name_input.setText("");
                    card_description_input.setText("");
                    refresh();
                    return true;
                }
                return false;
            }
        });
        card_description_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    card_input_panel.setVisibility(View.GONE);
                    addCardButton.setVisibility(View.VISIBLE);
                    if (card_name_input.getText().toString().equals("")) return true;
                    addCard();
                    card_name_input.setText("");
                    card_description_input.setText("");
                    refresh();
                    return true;
                }
                return false;
            }
        });
    }
    public void addCard(){
        Storage.getInstance().addCard(position, new Card(card_name_input.getText().toString(), card_description_input.getText().toString()));
    }
    public void refresh(){
        cards = Storage.getInstance().loadCardLists().get(position);
        cardAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }
}
