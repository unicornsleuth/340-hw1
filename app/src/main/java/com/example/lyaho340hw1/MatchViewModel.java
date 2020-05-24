package com.example.lyaho340hw1;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.util.Consumer;

import java.util.ArrayList;
// how to communicate with UserModel - how to ask it for which types of data
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
//    public ArrayList<Match> getMatches(onGetDataListener<Match> activityCallback) {
//        matchModel.getData(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    System.out.println("Error reading from database: " + e);
//                    activityCallback.onFailure();
//                    return;
//                }
//                if (documentSnapshot != null) {
//                    // gets all data in the collection
//                    Map<String, Object> data = documentSnapshot.getData();
//                    // data is null!
//                    //Log.e("documentSnapshot.getData() results", data.toString());
//                    if (data != null) {
//                        Collection<Object> dataMapValues = data.values();
//                        // get all documents in this collection separately
//                        for (Object firebaseUser : dataMapValues) {
//                            // check the info that comes in from firebaseUser - use setters
//                            if (firebaseUser != null) {
//                                Match currMatch = new Match();
//                                // get data out of it! - set it to user?
//                                //currMatch.setName(firebaseUser);
//                                Log.e("data.toString() results", firebaseUser.toString());
//                                matchList.add(currMatch);
//                                // change onSuccess to fill in fields in each match - should take a match (give it match)
//                                activityCallback.onSuccess(currMatch);
//                        }
//                    }
//                    }
//                }
//            }
//        });
//        return matchList;
//    }

    public void clear() {
        matchModel.clear();
    }
}
