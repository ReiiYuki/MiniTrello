package skesw12.minitrello.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.adapters.adapter.simple.CardAdapter;
import skesw12.minitrello.adapters.adapter.simple.CardListAdapter;
import skesw12.minitrello.models.Card;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;

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

    // TODO: Rename and change types of parameters
    private String title;
    private String mParam2;
    private String state;
    private View rootView;
    private int status;
    private OnFragmentInteractionListener mListener;

    private EditText cardList_title_input;
    private TextView textView;
    private Button addCardListButton;
    private Button addCardButton;
    private ListView cardListView;
    private List<CardList> cardLists;
    private CardListAdapter cardListAdapter;
    private CardList cards;
    private CardAdapter cardAdapter;

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
    public static CardFragments newInstance(String param1, int param2) {
        CardFragments fragment = new CardFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM2,param2);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_card_fragments, container, false);
        textView = (TextView)rootView.findViewById(R.id.textview_title);
        textView.setText(title);
        cardList_title_input = (EditText)rootView.findViewById(R.id.edit_text);
        addCardListButton = (Button)rootView.findViewById(R.id.add_cardlist_button);
        addCardButton = (Button)rootView.findViewById(R.id.add_card_button);
        cardLists = new ArrayList<CardList>();

        cardListAdapter = new CardListAdapter(getActivity(),R.layout.simple_cardlist_component,cardLists);
        int position = MainActivity.viewPager.getCurrentItem();
        cards = Storage.getInstance().loadCardLists().get(position);
        cardListView = (ListView) rootView.findViewById(R.id.simple_cardlist);
        cardAdapter = new CardAdapter(getActivity(),R.layout.simple_cardlist_component,cards);


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
                    textView.setText("clicked");
                    MainActivity.pageradapter.notifyDataSetChanged();
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
                addCard();
                refresh();
                Log.e("check","click");

            }
        });

        
        return rootView;
    }


    public void addCard(){
        int position = MainActivity.viewPager.getCurrentItem();
        Log.e("check","postion = "+position);
        Storage.getInstance().addCard(position, new Card("test card","test"));
    }

    private void addCardList() {
        Storage.getInstance().addCardList(new CardList("PAGE : " + Storage.getInstance().loadCardLists().size()));
    }

    private void refresh(){
        cardLists.clear();
        for (CardList cl : Storage.getInstance().loadCardLists()) cardLists.add(cl);
        cardListAdapter.notifyDataSetChanged();


        int position = MainActivity.viewPager.getCurrentItem();
        cards = Storage.getInstance().loadCardLists().get(position);
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
