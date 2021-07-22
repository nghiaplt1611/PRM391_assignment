package com.example.gtw_101.dao;

import androidx.annotation.NonNull;

import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserDAO {

    public static Account account = new Account();

    public static boolean checkEmailVerified(){
        return MainActivity.user.isEmailVerified();
    }

    public static void getUserInfo(String email){
        MainActivity.db = FirebaseFirestore.getInstance();
        MainActivity.db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                account = document.toObject(Account.class);
                            }
                        }
                    }
                });

    }


    public static void updateAvatar(String id, int avatar){
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(id);

        docRef.update("avatar", avatar).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public static Account registeredNewAccount(String email, String fullName, String yearOfBirth){
        Account account = new Account();
        account.setEmail(email);
        account.setFullName(fullName);
        account.setYearOfBirth(Integer.parseInt(yearOfBirth));
        account.setQuestion("");
        account.setNumOfLetterShown(0);
        account.setAchievements("FFFFF");
        account.setScore(ScoreDAO.score.getInitialScore());
        account.setAvatar(1);
        account.setUseHint(false);
        return account;
    }

    public static void updateProfile(String id, String fullName, int yearOfBirth){
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(id);

        docRef.update("fullName", fullName, "yearOfBirth", yearOfBirth).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public static void updateQuestion(String id){
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(UserDAO.account.getId());
        docRef.update("question", id).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public static void updateScoreAndShowHints(String id, int score, int numOfShownHint){
        account.setScore(score);
        account.setNumOfLetterShown(numOfShownHint);
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(id);

        docRef.update("score", score, "numOfLetterShown", numOfShownHint).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public static void updateScore(String id, int score){
        account.setScore(score);
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(id);

        docRef.update("score", score).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public static void updateAchievement(String id, String achievement){
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(id);

        docRef.update("achievements", achievement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public static void updateUseHint(String id, boolean useHint){
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("users").document(id);

        docRef.update("useHint", useHint).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }
}
