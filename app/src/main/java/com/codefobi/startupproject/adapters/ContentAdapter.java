package com.codefobi.startupproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codefobi.startupproject.R;
import com.codefobi.startupproject.activity.ReadContentActivity;
import com.codefobi.startupproject.models.Content;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tosantechnolocal on 11/3/2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.Holder> {

    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;

    private Context context;
    private LayoutInflater inflater;
    private List<Content> contentList = new ArrayList<>();

    public ContentAdapter(Context context, List<Content> contentList) {
        this.context = context;
        this.contentList = contentList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                View typeOne = inflater.inflate(R.layout.content_row_type_one, parent, false);
                return new Holder_TypeOne(typeOne);
            case TYPE_TWO:
                View typeTwo = inflater.inflate(R.layout.content_row_type_two, parent, false);
                return new Holder_TypeTwo(typeTwo);
            default:
                View defaultRow = inflater.inflate(R.layout.content_row_type_one, parent, false);
                return new Holder_TypeOne(defaultRow);
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Content current = contentList.get(position);
        switch (holder.getItemViewType()){
            case TYPE_ONE:
                Holder_TypeOne holder_typeOne = (Holder_TypeOne) holder;
                holder_typeOne.setData(current , position);
                break;
            case TYPE_TWO:
                Holder_TypeTwo holder_typeTwo = (Holder_TypeTwo) holder;
                holder_typeTwo.setData(current , position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Content current = contentList.get(position);
        int itemViewType = 0;

        if (current.getSpecifier().equals("0"))
            itemViewType = TYPE_ONE;
        else if (current.getSpecifier().equals("1"))
            itemViewType = TYPE_TWO;

        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public class Holder_TypeOne extends Holder {

        TextView title , body ;
        int position ;
        Content current ;

        ImageView imageView ;

        public Holder_TypeOne(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.content_row_title);
            body = (TextView) itemView.findViewById(R.id.content_row_body);
            imageView = (ImageView) itemView.findViewById(R.id.content_row_iv);
        }

        public void setData(final Content current, int position) {
            this.current = current;
            this.position = position;
            title.setText(current.getTitle());
            body.setText(current.getBody());
            Picasso.with(context)
                    .load("http://api.androidhive.info/json/movies/1.jpg")
                    .into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "works fine !" + current.getWhodoyou(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context , ReadContentActivity.class);
                    intent.putExtra("WHO" , current.getWhodoyou());
                    intent.putExtra("TITLE" , current.getTitle());
                    context.startActivity(intent);
                }
            });
        }
    }

    public class Holder_TypeTwo extends Holder {

        TextView title , body ;
        int position ;
        Content current ;
        ImageView imageView ;

        public Holder_TypeTwo(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.content_row_title);
            body = (TextView) itemView.findViewById(R.id.content_row_body);
            imageView = (ImageView) itemView.findViewById(R.id.content_row_iv);
        }

        public void setData(final Content current, int position) {
            this.current = current;
            this.position = position;
            title.setText(current.getTitle());
            body.setText(current.getBody());
            Picasso.with(context).
                    load("http://api.androidhive.info/json/movies/2.jpg").
                    into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, String.valueOf(current.getID()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
