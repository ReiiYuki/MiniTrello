package skesw12.minitrello.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class CardList extends ArrayList<Card> {
    private String title;
    private long createTime;
    public CardList(String title){
        this.title = title;
        createTime = System.currentTimeMillis();
    }

    @Override
    public int hashCode() {
        return (int) (createTime ^ (createTime >>> 32));
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

    public long getCreateTime() {
        return createTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        Date date = new Date(createTime);
        return sdf.format(date);
    }
}
