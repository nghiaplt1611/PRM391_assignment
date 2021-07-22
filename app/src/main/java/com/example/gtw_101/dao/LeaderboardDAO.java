package com.example.gtw_101.dao;

import androidx.annotation.NonNull;

import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LeaderboardDAO {
    public static ArrayList<Account> listTop3Ranking = new ArrayList<>();

    public static void getLeaderboardData(){
        MainActivity.db = FirebaseFirestore.getInstance();
        MainActivity.db.collection("users")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(3)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            listTop3Ranking.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Account acc = document.toObject(Account.class);
                                listTop3Ranking.add(acc);
                            }
                        }
                    }
                });
    }
}
