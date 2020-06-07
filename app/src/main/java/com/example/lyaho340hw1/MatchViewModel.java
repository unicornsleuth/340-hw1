package com.example.lyaho340hw1;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.util.Consumer;

import java.util.ArrayList;
// how to communicate with MatchModel - how to ask it for which types of data
public class MatchViewModel {

    private MatchModel matchModel;
    private ArrayList<Match> matchList = new ArrayList<>();
    private static final String TAG = MatchViewModel.class.getSimpleName();

    public MatchViewModel() {
        matchModel = new MatchModel();
    }

    @SuppressLint("RestrictedApi")
    public void getMatches(Consumer<ArrayList<Match>> responseCallback) {
        matchModel.getData(
                (QuerySnapshot querySnapshot) -> {
                    for (DocumentSnapshot matchSnapShot : querySnapshot.getDocuments()) {
                        Match currMatch = matchSnapShot.toObject(Match.class);
                        if (currMatch != null) {
                            currMatch.setUid(matchSnapShot.getId());
                            matchList.add(currMatch);
                        }
                    }
                    responseCallback.accept(matchList);
                },
                (databaseError -> System.out.println("Error reading from Matches: " + databaseError))
        );
    }

    public void clear() {
        matchModel.clear();
    }
}
