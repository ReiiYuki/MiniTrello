package skesw12.minitrello.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import skesw12.minitrello.R;
import skesw12.minitrello.adapters.adapter.simple.CardAdapter;
import skesw12.minitrello.adapters.adapter.simple.CardListAdapter;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;
import skesw12.minitrello.newactivities.SimpleCardActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardFragments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragments extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String POSITION = "position";
    private static int count = 0;

    // TODO: Rename and change types of parameters
    private String title;
    private String mParam2;
    private String state;
    private View rootView;
    private int status;
    private OnFragmentInteractionListener mListener;

    private EditText cardList_title_input;
    private EditText card_title_input;
    private TextView textView;
    private Button addCardListButton;
    private static CardList C;
    private Button addCardButton;
    private ListView cardListView;
    private CardListAdapter cardListAdapter;
    private CardList cards;
    private CardAdapter cardAdapter;
    private int position;

    public CardFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static CardFragments newInstance(String param1, CardList param2) {
        CardFragments fragment = new CardFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM2,param2.getSTATUS());
        C = param2;
//        args.puInt(POSITION,param2.get)

        fragment.setArguments(args);

        Log.e("check","Fragement is created");
        return fragment;
    }

    public void setMyText(String s){
        title = s;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            status = getArguments().getInt(ARG_PARAM2);
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView =  inflater.inflate(R.layout.fragment_card_fragments, container, false);
        textView = (TextView)rootView.findViewById(R.id.textview_title);
        textView.setText(title);
        cardList_title_input = (EditText)rootView.findViewById(R.id.edit_text);
        addCardListButton = (Button)rootView.findViewById(R.id.add_cardlist_button);
        addCardButton = (Button)rootView.findViewById(R.id.add_card_button);
        card_title_input = (EditText)rootView.findViewById(R.id.card_title_input_editText);
        card_title_input.setVisibility(View.GONE);

        int position = MainActivity.viewPager.getCurrentItem();
        cards = Storage.getInstance().loadCardLists().get(position);
        cardListView = (ListView) rootView.findViewById(R.id.simple_cardlist);
        cardAdapter = new CardAdapter(getActivity(),R.layout.simple_cardlist_component,C);
        cardListView.setAdapter(cardAdapter);
        refresh();



//        int position = MainActivity.viewPager.getCurrentItem();
//        cards = Storage.getInstance().loadCardLists().get(position);
//        cardListView = (ListView) rootView.findViewById(R.id.simple_card_list);
//        cardAdapter = new CardAdapter(getActivity(),R.layout.simple_cardlist_component,cards);
//
//        cardListView.setAdapter(cardListAdapter);

        if(status == CardList.ADDED){
            Log.e("check","status =  added");

        }
        else{
            Log.e("check","status =  empty");

        }


        if(status==CardList.EMPTY){

            cardList_title_input.setVisibility(View.GONE);
            addCardListButton.setVisibility(View.VISIBLE);
            addCardButton.setVisibility(View.GONE);

        }

        else if(status==CardList.ADDED){

            addCardListButton.setVisibility(View.GONE);
            cardList_title_input.setVisibility(View.GONE);
            addCardButton.setVisibility(View.VISIBLE);

        }


        addCardListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardList_title_input.setVisibility(View.VISIBLE);
                addCardListButton.setVisibility(View.GONE);
            }
        });

        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position2, long id) {

                int position = MainActivity.viewPager.getCurrentItem();
                Intent intent = new Intent(getActivity(), SimpleCardActivity.class);
                intent.putExtra("position1", position);
                intent.putExtra("position2", position2);
                startActivity(intent);
            }
        });

        cardList_title_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    int index = Storage.getInstance().loadCardLists().size() - 1;


                    cardList_title_input.setVisibility(View.GONE);
                    addCardListButton.setVisibility(View.GONE);
                    addCardButton.setVisibility(View.VISIBLE);

                    Storage.getInstance().loadCardLists().get(index).setSTATUS(CardList.ADDED);

                    Log.e("check", "index = " + index);


                    //test
                    int temp = Storage.getInstance().loadCardLists().get(index).getSTATUS();
                    if (temp == CardList.ADDED) {
                        Log.e("check", "status =  added");

                    } else {
                        Log.e("check", "status =  empty");

                    }


                    String newText = cardList_title_input.getText().toString();
                    Storage.getInstance().loadCardLists().get(index).setTitle(newText);
                    if (cardList_title_input.getText().toString().equals("")) return true;
                    textView.setText(cardList_title_input.getText().toString());

                    addCardList();
                    refresh();

                    return true;
                }
                return false;
            }
        });


        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_title_input.setVisibility(View.VISIBLE);
                addCardButton.setVisibility(View.GONE);
                Log.e("check", "click");

            }
        });

        card_title_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String title = card_title_input.getText().toString();
                    addCard(title);
                    refresh();
                    card_title_input.setText("");
                    addCardButton.setVisibility(View.VISIBLE);
                    card_title_input.setVisibility(View.GONE);
                    return  true;
                }
                return false;
            }
        });



        return rootView;
    }


    public void addCard(String title){
        int position = MainActivity.viewPager.getCurrentItem();
        Log.e("check","postion = "+position);
        Storage.getInstance().addCard(position, new Card(title, ""));
    }

    private void addCardList() {
        Storage.getInstance().addCardList(new CardList("PAGE : " + Storage.getInstance().loadCardLists().size()));
    }

    private void refresh(){

        int position = MainActivity.viewPager.getCurrentItem();
        cards = Storage.getInstance().loadCardLists().get(position);
        Storage.getInstance().save(this.getContext());
        cardAdapter.notifyDataSetChanged();
        MainActivity.pageradapter.notifyDataSetChanged();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
