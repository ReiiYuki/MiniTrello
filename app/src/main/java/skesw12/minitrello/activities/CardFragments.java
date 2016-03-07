package skesw12.minitrello.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import skesw12.minitrello.R;
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
    private CardList cardLists;
    private TextView textView;
    private View rootView;

    private OnFragmentInteractionListener mListener;
    private EditText cardList_title_input;
    private Button addButton;

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
    public static CardFragments newInstance(String param1, String param2) {
        CardFragments fragment = new CardFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_card_fragments, container, false);
        textView = (TextView)rootView.findViewById(R.id.textview);
        textView.setText(title);
        cardList_title_input = (EditText)rootView.findViewById(R.id.edit_text);
        addButton = (Button)rootView.findViewById(R.id.add_button);

        int index = MainActivity.viewPager.getCurrentItem();
        CardList currentCardList = Storage.getInstance().loadCardLists().get(index);


        if(currentCardList.getSTATUS()==CardList.EMPTY){
            cardList_title_input.setVisibility(View.GONE);
            addButton.setVisibility(View.VISIBLE);
        }

        else if(currentCardList.getSTATUS()==CardList.ADDED){
            addButton.setVisibility(View.GONE);
            cardList_title_input.setVisibility(View.GONE);
        }


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardList_title_input.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.GONE);
            }
        });

        cardList_title_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    int index = MainActivity.viewPager.getCurrentItem();
                    cardList_title_input.setVisibility(View.GONE);
                    addButton.setVisibility(View.GONE);
                    Storage.getInstance().loadCardLists().get(index).setSTATUS(CardList.ADDED);
                    String newText = cardList_title_input.getText().toString();
                    Storage.getInstance().loadCardLists().get(index).setTitle(newText);
                    textView.setText("clicked");
                    MainActivity.pageradapter.notifyDataSetChanged();
                    if (cardList_title_input.getText().toString().equals("")) return true;
                    textView.setText(cardList_title_input.getText().toString());
                    Storage.getInstance().addCardList(new CardList("PAGE : " + Storage.getInstance().loadCardLists().size()));
                    MainActivity.pageradapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }});

        
        return rootView;
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
