package com.example.gtw_101.dao;

import androidx.annotation.NonNull;

import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.model.Score;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ScoreDAO {
    public static Score score = new Score();

    public static void getScore(){
        MainActivity.db = FirebaseFirestore.getInstance();
        MainActivity.db.collection("scores")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                score = document.toObject(Score.class);
                            }
                        }
                    }
                });
    }
}
