package com.example.gtw_101.dao;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gtw_101.controller.MainActivity;
import com.example.gtw_101.model.Achievement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AchievementDAO {
    public static ArrayList<Achievement> listAchievement = new ArrayList<>();

    public static void loadAchievementData(){
        if (listAchievement.size() == 5)
            return;
        MainActivity.db = FirebaseFirestore.getInstance();
        MainActivity.db.collection("achievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Achievement achievement = document.toObject(Achievement.class);
                                listAchievement.add(achievement);
                            }
                        }
                    }
                });
    }

}
