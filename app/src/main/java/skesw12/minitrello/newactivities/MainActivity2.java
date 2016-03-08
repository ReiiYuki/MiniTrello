package skesw12.minitrello.newactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.adapters.adapter.simple.CardListAdapter;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;

public class MainActivity2 extends AppCompatActivity {
    private ListView cardListView;
    private Button add_cardListButton;
    private EditText cardList_title_input;
    private List<CardList> cardLists;
    private CardListAdapter cardListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initComponent();
        setCardListAdapter();
        setAllListener();
        refresh();
    }
    private void initComponent(){
        add_cardListButton = (Button)findViewById(R.id.simple_add_cardlist_button);
        cardList_title_input = (EditText)findViewById(R.id.simple_cardlist_input);
        cardLists = new ArrayList<CardList>();
        cardListAdapter = new CardListAdapter(this,R.layout.simple_cardlist_component,cardLists);
        cardListView = (ListView) findViewById(R.id.simple_cardlist_list);
    }
    private void setCardListAdapter(){
        cardListView.setAdapter(cardListAdapter);
    }
    private void setAllListener(){
        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity2.this,SimpleCardListActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        add_cardListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardList_title_input.setVisibility(View.VISIBLE);
                add_cardListButton.setVisibility(View.GONE);
            }
        });
        cardList_title_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    cardList_title_input.setVisibility(View.GONE);
                    add_cardListButton.setVisibility(View.VISIBLE);
                    if (cardList_title_input.getText().toString().equals("")) return true;
                    addCardList();
                    cardList_title_input.setText("");
                    refresh();
                    return true;
                }
                return false;
            }
        });
    }


    private void addCardList(){
        CardList temp = new CardList(cardList_title_input.getText().toString());
        temp.setSTATUS(CardList.ADDED);
        Storage.getInstance().addCardList(temp);
    }

    private void refresh(){
        cardLists.clear();
        for (CardList cl : Storage.getInstance().loadCardLists()) cardLists.add(cl);
        cardListAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }
}
