package com.example.lyaho340hw1;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.util.Consumer;

import java.util.ArrayList;
import java.util.List;

// communicates with firestore
public class MatchModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void getData(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        @SuppressLint("RestrictedApi") ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener(((queryDocumentSnapshots, e) -> {
                    if (e != null) dataErrorCallback.accept(e);
                    dataChangedCallback.accept(queryDocumentSnapshots);
                }));
        listeners.add(listener);
    }

    public void clear() {
        // clear all the listeners onPause
        listeners.forEach(ListenerRegistration::remove);
    }
}
