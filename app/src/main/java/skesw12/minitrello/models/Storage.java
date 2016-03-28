package skesw12.minitrello.models;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        if (instance==null) {
            instance = new Storage();
        }
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
    public void removeCard(int position,Card card){
        cardLists.get(position).remove(card);

    }
    public void addCard(int position,Card card){
        cardLists.get(position).add(card);

    }
    public void addComment(int position,int position2,Comment comment) {cardLists.get(position).get(position2).addComment(comment);}
    public void removeComment(int position,int position2,Comment comment) { cardLists.get(position).get(position2).removeComment(comment);}
    public void save(Context context){
        Log.w("Yes","Yes");
        try{
            File file = new File(context.getFilesDir()+"Storage.mt");

            if (!file.exists()) file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(Storage.getInstance());
            Log.w("Success", out.getFD().toString());
            os.close();
        }catch (Exception e){
            Log.e("No",e.getMessage());
        }
    }
    public void load(Context context){
        try {
            File file = new File(context.getFilesDir()+"Storage.mt");
            if (file.exists()){
                FileInputStream ins = new FileInputStream(file);
                ObjectInputStream is = new ObjectInputStream(ins);
                instance = (Storage)is.readObject();
                is.close();
            }
        }catch (Exception e){

        }
    }
}
