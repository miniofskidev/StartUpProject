package com.codefobi.startupproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codefobi.startupproject.R;
import com.codefobi.startupproject.models.ReadContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tosantechnolocal on 11/3/2016.
 */
public class ReadContentAdapter extends RecyclerView.Adapter<ReadContentAdapter.Holder> {

    private static final int TYPE_NO_IMAGE = 0;
    private static final int TYPE_BODY = 1;
    private static final int TYPE_IMAGE_ONLY = 2;
    private static final int TYPE_FULL = 3;

    private Context context;
    private List<ReadContent> readContentList = new ArrayList<>();
    private LayoutInflater inflater;

    public ReadContentAdapter(Context context, List<ReadContent> readContentList) {
        this.context = context;
        this.readContentList = readContentList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO_IMAGE: {
                View view = inflater.inflate(R.layout.read_no_image_layout, parent, false);
                return new HolderNoImage(view);
            }
            case TYPE_BODY: {
                View view = inflater.inflate(R.layout.read_body_layout, parent, false);
                return new HolderBodyOnly(view);
            }
            case TYPE_IMAGE_ONLY: {
                View view = inflater.inflate(R.layout.read_image_layout, parent, false);
                return new HolderImage(view);
            }
            case TYPE_FULL: {
                View view = inflater.inflate(R.layout.read_full_layout, parent, false);
                return new HolderFull(view);
            }
            default: {
                View view = inflater.inflate(R.layout.read_full_layout, parent, false);
                return new HolderFull(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ReadContent current = readContentList.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_NO_IMAGE: {
                HolderNoImage holderNoImage = (HolderNoImage) holder;
                holderNoImage.setData(current, position);
                break;
            }
            case TYPE_BODY: {
                HolderBodyOnly holderBodyOnly = (HolderBodyOnly) holder;
                holderBodyOnly.setData(current, position);
                break;
            }
            case TYPE_IMAGE_ONLY: {
                HolderImage holderImage = (HolderImage) holder;
                holderImage.setData(current, position);
                break;
            }
            case TYPE_FULL: {
                HolderFull holderFull = (HolderFull) holder;
                holderFull.setData(current, position);
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        ReadContent current = readContentList.get(position);
        int itemViewType = 0;

        if (current.getSpecifier().equals("0"))
            itemViewType = TYPE_NO_IMAGE;
        else if (current.getSpecifier().equals("1"))
            itemViewType = TYPE_BODY;
        else if (current.getSpecifier().equals("2"))
            itemViewType = TYPE_IMAGE_ONLY;
        else itemViewType = TYPE_FULL;

        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return readContentList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    // Holder Class for Image only Contents
    public class HolderImage extends Holder {
        ReadContent current;
        int position;
        ImageView imageView;

        public HolderImage(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_image_only);
        }

        public void setData(ReadContent current, int position) {
            this.current = current;
            this.position = position;
//            Picasso.with(context).load(current.getImagePath()).into(imageView);
            Glide.with(context)
                    .load(current.getImagePath())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .into(imageView);
            Log.d("Adapter" , "Created a row for ImageOnly Layout");
        }
    }

    // Holder Class for Body only Contents
    public class HolderBodyOnly extends Holder {
        ReadContent current;
        int position;
        TextView textView;

        public HolderBodyOnly(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_body_only);
        }

        public void setData(ReadContent current, int position) {
            this.current = current;
            this.position = position;
            textView.setText(current.getBody());
            Log.d("Adapter" , "Created a row for BodyOnly layout");
        }
    }

    //Holder Class for Full Content
    public class HolderFull extends Holder {
        ReadContent current;
        int position;
        ImageView imageView;
        TextView title;
        TextView body;

        public HolderFull(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_full_iv);
            title = (TextView) itemView.findViewById(R.id.row_full_title);
            body = (TextView) itemView.findViewById(R.id.row_full_body);
        }

        public void setData(ReadContent current, int position) {
            this.current = current;
            this.position = position;
//            Picasso.with(context).load(current.getImagePath()).into(imageView);
            Glide.with(context)
                    .load(current.getImagePath())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .into(imageView);
            title.setText(current.getTitle());
            body.setText(current.getBody());
            Log.d("Adapter" , "Created a row for Full Layout");
        }
    }

    // Holder Class for Body And Title Contents
    public class HolderNoImage extends Holder {
        ReadContent current;
        int position;
        TextView title , body ;
        public HolderNoImage(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.row_noimg_title);
            body = (TextView) itemView.findViewById(R.id.row_noimg_body);
        }

        public void setData(ReadContent current, int position) {
            this.current = current;
            this.position = position;
            title.setText(current.getTitle());
            body.setText(current.getBody());
            Log.d("Adapter" , "Created a row for noImage Layout");
        }
    }
}
