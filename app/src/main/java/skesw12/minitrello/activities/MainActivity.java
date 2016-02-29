package skesw12.minitrello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.adapters.CardListAdapter;
import skesw12.minitrello.views.HorizontalListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<CardList> cardLists = new ArrayList<CardList>();
        cardLists.add(new CardList("AAA"));
        cardLists.add(new CardList("AAA"));
        CardListAdapter adapter = new CardListAdapter(this,R.layout.cardlist_component,cardLists);
        HorizontalListView horizontalListView = (HorizontalListView)findViewById(R.id.cardlist_hlistview);
        horizontalListView.setAdapter(adapter);
    }
}
