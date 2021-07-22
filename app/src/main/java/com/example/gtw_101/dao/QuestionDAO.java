package com.example.gtw_101.dao;

import androidx.annotation.NonNull;

import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.model.Question;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Random;

public class QuestionDAO {

    public static Question question;
    public static ArrayList<Question> listQuestion = new ArrayList<>();

    public static void getQuestion(String id){
        MainActivity.db = FirebaseFirestore.getInstance();
        DocumentReference docRef = MainActivity.db.collection("questions").document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                question = documentSnapshot.toObject(Question.class);
                getAllQuestionsInLevel(question.getLevel());
            }
        });
    }

    public static void getAllQuestionsInLevel(int level){
        MainActivity.db = FirebaseFirestore.getInstance();
        MainActivity.db.collection("questions")
                .whereEqualTo("level", level)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            listQuestion.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Question ques = document.toObject(Question.class);
                                listQuestion.add(ques);
                            }
                        }
                    }
                });
    }

    public static void loadQuestion(){

        if (MainActivity.user == null){
            if (GuestDAO.guest.getQuestion().isEmpty()){
                getAllQuestionsInLevel(1);
            }
            else {
                getQuestion(GuestDAO.guest.getQuestion());
            }
        }
        else {

            if (UserDAO.account.getQuestion().isEmpty()){
                getAllQuestionsInLevel(1);
            }
            else {
                getQuestion(UserDAO.account.getQuestion());
            }
        }

    }

    public static void getRandomQuestion(){
        int numOfQuestions = listQuestion.size();
        Random rand = new Random();
        int randomQuestion = rand.nextInt(numOfQuestions);
        question = listQuestion.get(randomQuestion);
        if (MainActivity.user == null){
            GuestDAO.guest.setQuestion(question.getId());
            GuestDAO.updateQuestion(question.getId());
        }
        else {
            UserDAO.account.setQuestion(question.getId());
            UserDAO.updateQuestion(question.getId());
        }

    }

    public static void getCurrentQuestion(){
        if (MainActivity.user == null){
            if (!GuestDAO.guest.getQuestion().isEmpty()){
                getQuestion(GuestDAO.guest.getQuestion());
            }
            else {
                getRandomQuestion();
            }
        }
        else {
            if (!UserDAO.account.getQuestion().isEmpty()){
                getQuestion(UserDAO.account.getQuestion());
            }
            else {
                getRandomQuestion();
            }
        }

    }



}
