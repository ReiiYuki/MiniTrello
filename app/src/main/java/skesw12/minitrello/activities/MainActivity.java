package skesw12.minitrello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.adapters.CardListAdapter;
import skesw12.minitrello.models.Storage;
import skesw12.minitrello.views.HorizontalListView;

public class MainActivity extends AppCompatActivity {
    private EditText inputCardListTitle;
    private TextView noCardLabel;
    private Button addCardListButton;
    private List<CardList> cardLists;
    private CardListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }
    private void initComponent(){
        cardLists = new ArrayList<CardList>();
        adapter = new CardListAdapter(this,R.layout.cardlist_component,cardLists);
        HorizontalListView horizontalListView = (HorizontalListView)findViewById(R.id.cardlist_hlistview);
        horizontalListView.setAdapter(adapter);
        refresh();
        addCardListButton = (Button)findViewById(R.id.add_cardlist_button);
        noCardLabel = (TextView) findViewById(R.id.no_card_list_label);
        inputCardListTitle = (EditText)findViewById(R.id.input_cardlist_box);
        inputCardListTitle.setVisibility(View.GONE);
        addCardListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCardListButton.setVisibility(View.GONE);
                inputCardListTitle.setVisibility(View.VISIBLE);
            }
        });
        inputCardListTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    addNewCardList();
                    return true;
                }
                return false;
            }
        });
    }

    private void addNewCardList() {
        noCardLabel.setVisibility(View.GONE);
        Storage.getInstance().addCardList(new CardList(inputCardListTitle.getText().toString()));
        inputCardListTitle.setVisibility(View.GONE);
        addCardListButton.setVisibility(View.VISIBLE);
        refresh();
        inputCardListTitle.setText("");


    }

    private void refresh(){

        cardLists.clear();
        for (CardList cl:Storage.getInstance().loadCardLists()) cardLists.add(cl);
        adapter.notifyDataSetChanged();
    }




}
