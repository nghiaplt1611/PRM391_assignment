package com.example.gtw_101.dao;

import androidx.annotation.NonNull;

import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.model.Question;
import com.example.gtw_101.model.Score;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

    public static Account registeredNewAccount(String email, String fullName, String yearOfBirth){
        Account account = new Account();
        account.setEmail(email);
        account.setFullName(fullName);
        account.setYearOfBirth(Integer.parseInt(yearOfBirth));
        account.setQuestion(null);
        account.setNumOfLetterShown(0);
        account.setAchievements("FFFFF");
        account.setScore(ScoreDAO.score.getInitialScore());
        return account;
    }
}
