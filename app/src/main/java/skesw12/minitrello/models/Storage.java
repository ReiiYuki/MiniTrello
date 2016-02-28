package skesw12.minitrello.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class Storage {
    private static Storage instance;
    private List<CardList> cardLists;
    private Storage(){
        cardLists = new ArrayList<CardList>();
    }
    public static Storage getInstance(){
        if (instance==null) instance = new Storage();
        return instance;
    }
    public void addCardList(CardList cardList){
        cardLists.add(cardList);
    }
    public List<CardList> loadCardLists(){
        return cardLists;
    }
    public void removeCardList(CardList cardList){
        cardLists.remove(cardList);
    }
}
