package skesw12.minitrello.models;

import android.graphics.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class CardList extends ArrayList<Card> implements Serializable{
    private String title;
    private long createTime;
    private int STATUS;
    public final static int EMPTY  = 0 ;
    public final static int ADDED = 1;
    private int color;
    public CardList(String title){
        this.title = title;
        this.STATUS = EMPTY;
        color = Color.BLUE;
        createTime = System.currentTimeMillis();
    }

    @Override
    public int hashCode() {
        return (int) (createTime ^ (createTime >>> 32));
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardList cardList = (CardList) o;

        return createTime == cardList.createTime;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        Date date = new Date(createTime);
        return sdf.format(date);
    }



    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS){
        this.STATUS = STATUS;
    }


}
