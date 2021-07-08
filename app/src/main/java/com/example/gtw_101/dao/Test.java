package com.example.gtw_101.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gtw_101.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class Test {
    static FirebaseFirestore db;
    static ArrayList<Account> listAccount = new ArrayList<>();
    public static void test(){
        db = FirebaseFirestore.getInstance();
        getData();

    }

    public static void readData(){
        for (Account a: listAccount){
            System.out.println(a.getFullName()+a.getEmail()+a.getYearOfBirth());
        }
    }

    public static void getData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Account account = document.toObject(Account.class);
                                Log.e("AAA", document.getId());
                                listAccount.add(account);
                            }
                            readData();
                        }
                    }
                });
    }
}
