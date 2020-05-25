package com.example.lyaho340hw1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {

    private MatchViewModel vm;
    private ArrayList<Match> matchList;
    private static final String TAG = MatchesFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView scrollView = (ScrollView) inflater.inflate(
                R.layout.recycler_view, container, false);

        RecyclerView recyclerView = (RecyclerView) scrollView.findViewById(R.id.my_recycler_view);
//        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
//                R.layout.recycler_view, container, false);

        vm = new MatchViewModel();
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), vm);
        recyclerView.setAdapter(adapter);

        Log.d(TAG, "onCreateView invoked");
        return recyclerView;
    }

    @Override
    public void onPause() {
        vm.clear();
        super.onPause();
    }

    // this is for what happens in each "match"
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ImageButton likeButton;
        public TextView matchName;
        public ViewGroup parent;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            this.parent = parent;
            image = (ImageView) itemView.findViewById(R.id.match_picture);
            likeButton = (ImageButton) itemView.findViewById(R.id.like_button);
            matchName = (TextView) itemView.findViewById(R.id.match_name);
        }


    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        // I think this should be something like a getLength() from firebase?
        //private static final int LENGTH = 6;
        private MatchViewModel vm;
        private ArrayList<Match> matchList;

        public ContentAdapter(Context context, MatchViewModel viewModel) {
            this.vm = viewModel;
            matchList = new ArrayList<>();
            context.getResources();
            vm.getMatches((ArrayList<Match> matches) -> {
                // Log.e("onCreateViewHolder: matches.get(0).getImageUrl()", matches.get(0).getImageUrl());
                for (Match match : matches) {
                    if (!matchList.contains(match)) {
                        matchList.add(match);
                    }
                }
                notifyDataSetChanged();
            });
        }

        public void setMatchArray(ArrayList<Match> matches) {
            matchList.clear();
            matchList.addAll(matches);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.e("onBindViewHolder; first imageUrl in matchList", matchList.get(0).getImageUrl());
            Match currentMatch;
            if (matchList != null) {
                currentMatch = matchList.get(position % matchList.size());
                Log.e("first imageUrl in matchList", matchList.get(0).getImageUrl());
                Picasso.get().load(currentMatch.getImageUrl()).into(holder.image);
                holder.matchName.setText(currentMatch.getName());
            } else { currentMatch = new Match(); }
            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Context context = holder.parent.getContext();
                    StringBuilder text;
                    if (holder.likeButton.getContentDescription().equals(context.getString(R.string.liked))) {
                        holder.likeButton.setImageResource(R.drawable.ic_favorite_border);
                        holder.likeButton.setContentDescription(context.getString(R.string.not_liked));
                        currentMatch.setLiked(false);
                        text = new StringBuilder("You unliked ");
                    } else {
                        holder.likeButton.setImageResource(R.drawable.ic_favorite_fill);
                        holder.likeButton.setContentDescription(context.getString(R.string.liked));
                        currentMatch.setLiked(true);
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
            return matchList.size();
        }
    }
}
