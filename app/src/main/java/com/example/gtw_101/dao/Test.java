package com.example.gtw_101.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gtw_101.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Test {
     static FirebaseFirestore db;
    public static void test(){
        db = FirebaseFirestore.getInstance();

        HashMap<String, Object> user = new HashMap<>();

        //Account account = new Account(1, "Nghia", "hahaha", "12345");

        user.put("first", "Adrrrrrra");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        Log.e("aaa", "aaaaaaaaaaaaaaaaaaaaaaaaa");
        DocumentReference documentReference = db.collection("users").document();
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.e("herreee","121313");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("herreee",e.getMessage());
            }
        });



        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> a = document.getData();

                            }
                        } 
                    }
                });
    }
}
