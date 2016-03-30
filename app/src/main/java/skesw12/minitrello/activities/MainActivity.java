package skesw12.minitrello.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.adapters.PagerAdapter;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;

public class MainActivity extends AppCompatActivity {
//    private EditText inputCardListTitle;
//    private TextView noCardLabel;
//    private Button addCardListButton;
//    private List<CardList> cardLists;
//    private CardListAdapter adapter;
//    private Button addCardButton;

    private Button button;
    private List<CardList> cardlists;
    public static PagerAdapter pageradapter;
    public static ViewPager viewPager;
    private TextView textView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initComponent(){

        if(Storage.getInstance().loadCardLists().size()==0) {
            Storage.getInstance().addCardList(new CardList(" STARTER "));
            Log.e("check", "starter?");
        }
        pageradapter = new PagerAdapter
                (getSupportFragmentManager(), Storage.getInstance().loadCardLists());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pageradapter);


//        cardLists = new ArrayList<CardList>();
//        adapter = new CardListAdapter(this,R.layout.cardlist_component,Storage.getInstance().loadCardLists());
//        HorizontalListView horizontalListView = (HorizontalListView)findViewById(R.id.cardlist_hlistview);
//        horizontalListView.setAdapter(adapter);
//        refresh();
//        addCardListButton = (Button)findViewById(R.id.add_cardlist_button);
//
//        noCardLabel = (TextView) findViewById(R.id.no_card_list_label);
//        inputCardListTitle = (EditText)findViewById(R.id.input_cardlist_box);
//        inputCardListTitle.setVisibility(View.GONE);
//        addCardListButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addCardListButton.setVisibility(View.GONE);
//                inputCardListTitle.setVisibility(View.VISIBLE);
//            }
//        });
//
//
//        inputCardListTitle.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    addNewCardList();
//                    return true;
//                }
//                return false;
//            }
//        });

//        if(getActiveFragment(viewPager,viewPager.getCurrentItem())==null){
//            String s = Storage.getInstance().loadCardLists().get(viewPager.getCurrentItem()).getTitle();
//            Log.e("check",s);
//        }
//        button = (Button)getActiveFragment(viewPager,viewPager.getCurrentItem()).getView().findViewById(R.id.add_button);
//        textView = (TextView)getActiveFragment(viewPager,viewPager.getCurrentItem()).getView().findViewById(R.id.textview);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int index = MainActivity.viewPager.getCurrentItem();
//                textView.setText("clicked");
//                Storage.getInstance().addCardList(new CardList("PAGE : " + Storage.getInstance().loadCardLists().size()));
//            }
//        });

    }
    public Fragment getActiveFragment(ViewPager container, int position) {
        String name = "android:switcher:" + container.getId() + ":" + position;
        return getSupportFragmentManager().findFragmentByTag(name);
    }

    private void addNewCardList() {
//        noCardLabel.setVisibility(View.GONE);
//        Storage.getInstance().addCardList(new CardList(inputCardListTitle.getText().toString()));
//        inputCardListTitle.setVisibility(View.GONE);
//        addCardListButton.setVisibility(View.VISIBLE);
//        refresh();
//        inputCardListTitle.setText("");
    }

    private void refresh()
    {
//        adapter.notifyDataSetChanged();
    }




}
