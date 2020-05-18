package com.example.lyaho340hw1;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MatchesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }

    // this is for what happens in each "match"
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ImageButton likeButton;
        public TextView matchName;
        public ViewGroup parent;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_match, parent, false));
            this.parent = parent;
            image = (ImageView) itemView.findViewById(R.id.match_picture);
            likeButton = (ImageButton) itemView.findViewById(R.id.like_button);
            matchName = (TextView) itemView.findViewById(R.id.match_name);
        }


    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 10;
        private final int[] matchPicturesId;
        private final String[] matchNames;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            matchPicturesId = new int[]{
                    R.drawable.sansevieiria,
                    R.drawable.pilea,
                    R.drawable.senecio,
                    R.drawable.philodendron,
                    R.drawable.oxalis,
                    R.drawable.ludisia,
                    R.drawable.asplenium,
                    R.drawable.peperomia,
                    R.drawable.syngonium,
                    R.drawable.aglaonema
            };
            matchNames = new String[] {
                    resources.getString(R.string.match_1),
                    resources.getString(R.string.match_2),
                    resources.getString(R.string.match_3),
                    resources.getString(R.string.match_4),
                    resources.getString(R.string.match_5),
                    resources.getString(R.string.match_6),
                    resources.getString(R.string.match_7),
                    resources.getString(R.string.match_8),
                    resources.getString(R.string.match_9),
                    resources.getString(R.string.match_10)
            };
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setImageResource(matchPicturesId[position % matchPicturesId.length]);
            holder.matchName.setText(matchNames[position % matchNames.length]);
            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Context context = holder.parent.getContext();
                    StringBuilder text;
                    if (holder.likeButton.getContentDescription().equals(context.getString(R.string.liked))) {
                        holder.likeButton.setImageResource(R.drawable.ic_favorite_border);
                        holder.likeButton.setContentDescription(context.getString(R.string.not_liked));
                        text = new StringBuilder("You unliked ");
                    } else {
                        holder.likeButton.setImageResource(R.drawable.ic_favorite_fill);
                        holder.likeButton.setContentDescription(context.getString(R.string.liked));
                        text = new StringBuilder("You liked ");
                    }

                    text.append(holder.matchName.getText());
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
