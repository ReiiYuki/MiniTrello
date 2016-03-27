package skesw12.minitrello.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YukiReii on 27/3/2559.
 */
public class Comment {
    private String comment;
    private String author;
    private long createTime;

    public Comment(String comment,String author){
        this.comment = comment;
        this.author = author;
        this.createTime = System.currentTimeMillis();
    }

    public Comment(String comment){
        this.comment = comment;
        this.author = "Annonymous";
        this.createTime = System.currentTimeMillis();
    }

    public String getAuthor() {
        return author;
    }

    public String getComment() {
        return comment;
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

        Comment comment = (Comment) o;

        return createTime == comment.createTime;
    }
}
