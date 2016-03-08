package skesw12.minitrello.newactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import skesw12.minitrello.R;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.Storage;

public class SimpleCardActivity extends AppCompatActivity {
    private Card card;
    private int position1;
    private int position2;
    private TextView cardName,cardDescription,createTime;
    private EditText editCardName,editCardDescription;
    private Button editButton,saveButton,removeCardButton;
    private LinearLayout showLayout,editLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_card);
        getCard();
        initComponent();
        addAllListener();
        refresh();
    }
    private void getCard(){
        position1 = (Integer) getIntent().getSerializableExtra("position1");
        position2 = (Integer) getIntent().getSerializableExtra("position2");
        card = Storage.getInstance().loadCardLists().get(position1).get(position2);
    }
    private void initComponent(){
        removeCardButton = (Button) findViewById(R.id.simple_remove_card_button);
        createTime = (TextView) findViewById(R.id.simple_card_createtime);
        createTime.setText("Created Date : "+card.getReadableCreatedTime());
        cardName = (TextView) findViewById(R.id.simple_card_name);
        cardDescription = (TextView) findViewById(R.id.simple_card_description);
        editButton = (Button) findViewById(R.id.simple_edit_card_button);
        saveButton = (Button) findViewById(R.id.simple_save_edit_card_button);
        editCardName = (EditText) findViewById(R.id.simple_edit_card_name);
        editCardDescription = (EditText) findViewById(R.id.simple_edit_card_description);
        showLayout = (LinearLayout)findViewById(R.id.simple_card_view_detail);
        editLayout = (LinearLayout)findViewById(R.id.card_edit_view);
    }
    private void addAllListener(){
        getSupportActionBar().setTitle(card.getName());
        removeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().removeCard(position1,card);
                finish();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLayout.setVisibility(View.VISIBLE);
                showLayout.setVisibility(View.GONE);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editCardName.getText().toString().equals(""))
                {
                    editLayout.setVisibility(View.GONE);
                    showLayout.setVisibility(View.VISIBLE);
                    edit();
                    refresh();
                }
            }
        });
        editCardName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    editLayout.setVisibility(View.GONE);
                    showLayout.setVisibility(View.VISIBLE);
                    if (editCardName.getText().toString().equals("")) return true;
                    edit();
                    refresh();
                    return true;
                }
                return false;
            }
        });
        editCardDescription.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    editLayout.setVisibility(View.GONE);
                    showLayout.setVisibility(View.VISIBLE);
                    if (editCardName.getText().toString().equals("")) return true;
                    edit();
                    refresh();
                    return true;
                }
                return false;
            }
        });
    }
    private void edit(){
        Storage.getInstance().loadCardLists().get(position1).get(position2).setName(editCardName.getText().toString());
        Storage.getInstance().loadCardLists().get(position1).get(position2).setDescription(editCardDescription.getText().toString());
    }
    private void refresh(){
        card = Storage.getInstance().loadCardLists().get(position1).get(position2);
        cardName.setText(card.getName());
        cardDescription.setText(card.getDescription());
        editCardDescription.setText(card.getDescription());
        editCardName.setText(card.getName());
    }
    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }
}
