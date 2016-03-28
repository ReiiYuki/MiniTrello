package skesw12.minitrello.newactivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.adapters.adapter.simple.CommentAdapter;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.Comment;
import skesw12.minitrello.models.Storage;

public class SimpleCardActivity extends AppCompatActivity {
    private Card card;
    private int position1;
    private int position2;
    private TextView cardName,cardDescription,createTime;
    private EditText editCardName,editCardDescription,commentBox,authorBox;
    private Button editButton,saveButton,removeCardButton,addCommentButton;
    private View showLayout,editLayout,commentB,commentP;
    private ListView commentList;
    private CommentAdapter adapter;
    private List<Comment> comments;
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
        addCommentButton = (Button)findViewById(R.id.add_comment);
        commentB = (View) findViewById(R.id.add_comment_p);
        commentP = (View) findViewById(R.id.comment_p);
        commentList = (ListView)findViewById(R.id.comment_list);
        commentBox = (EditText) findViewById(R.id.comment);
        authorBox = (EditText) findViewById(R.id.author);
        comments = new ArrayList<Comment>();
        for (Comment c :Storage.getInstance().loadCardLists().get(position1).get(position2).getCommentList() ) comments.add(c);
        adapter = new CommentAdapter(this,R.layout.comment_component,Storage.getInstance().loadCardLists().get(position1).get(position2).getCommentList());
        commentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showButton(Storage.getInstance().loadCardLists().get(position1).get(position2).getCommentList().get(position));
            }
        });
    }
    private void addAllListener(){
        commentList.setAdapter(adapter);
        getSupportActionBar().setTitle(card.getName());
        removeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().removeCard(position1, card);
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
        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentB.setVisibility(View.GONE);
                commentP.setVisibility(View.VISIBLE);
            }
        });
        authorBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    commentB.setVisibility(View.VISIBLE);
                    commentP.setVisibility(View.GONE);
                    if (commentBox.getText().toString().equals("")) return true;
                    addComment();
                    refresh();
                    return true;
                }
                return false;
            }
        });
        commentBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    commentB.setVisibility(View.VISIBLE);
                    commentP.setVisibility(View.GONE);
                    if (commentBox.getText().toString().equals("")) return true;
                    addComment();
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
    private void addComment(){
        if (authorBox.getText().toString().equals("")) {
            Storage.getInstance().addComment(position1,position2,new Comment(commentBox.getText().toString()));
            return;
        }
        Storage.getInstance().addComment(position1, position2, new Comment(commentBox.getText().toString(), authorBox.getText().toString()));
    }
    private void refresh(){
        card = Storage.getInstance().loadCardLists().get(position1).get(position2);
        comments = Storage.getInstance().loadCardLists().get(position1).get(position2).getCommentList();
        adapter.notifyDataSetChanged();
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
    private void showButton(final Comment comment){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DELETE COMMENT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Storage.getInstance().removeComment(position1,position2,comment);
                refresh();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
