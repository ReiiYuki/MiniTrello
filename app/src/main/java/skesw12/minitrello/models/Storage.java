package skesw12.minitrello.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class Storage implements Serializable{
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
    public void removeCard(int position,Card card){ cardLists.get(position).remove(card);}
    public void addCard(int position,Card card){
        cardLists.get(position).add(card);
    }
    public void addComment(int position,int position2,Comment comment) {cardLists.get(position).get(position2).addComment(comment);}
    public void removeComment(int position,int position2,Comment comment) { cardLists.get(position).get(position2).removeComment(comment);}
}
