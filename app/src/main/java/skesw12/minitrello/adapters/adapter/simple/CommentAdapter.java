package skesw12.minitrello.adapters.adapter.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import skesw12.minitrello.R;
import skesw12.minitrello.models.Comment;

/**
 * Created by YukiReii on 28/3/2559.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.comment_component, null);
        }
        Comment comment = getItem(position);
        TextView text = (TextView)v.findViewById(R.id.comment_text);
        text.setText(comment.getComment());
        TextView author = (TextView) v.findViewById(R.id.author_comment);
        author.setText(comment.getAuthor());
        return v;
    }
}
