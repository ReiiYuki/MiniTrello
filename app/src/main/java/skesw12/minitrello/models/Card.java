package skesw12.minitrello.models;

import android.graphics.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YukiReii on 29/2/2559.
 */
public class Card implements Serializable{
    private String name;
    private String description;
    private long createTime;
    private List<Comment> commentList;
    private int color;
    public Card(String name,String description){
        this.name = name;
        this.description = description;
        createTime = System.currentTimeMillis();
        commentList = new ArrayList<Comment>();
        color = Color.BLUE;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void addComment(Comment c){
        commentList.add(c);
    }

    public void removeComment(Comment c){
        commentList.remove(c);
    }

    public List<Comment> getCommentList(){
        return commentList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        Date date = new Date(createTime);
        return sdf.format(date);
    }

    @Override
    public int hashCode() {
        return (int) (createTime ^ (createTime >>> 32));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return createTime == card.createTime;
    }
}
