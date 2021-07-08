package com.example.gtw_101.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.dao.UserDAO;

import org.w3c.dom.Text;

public class PlayerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        getSupportActionBar().hide();
        loadData();
    }

    public void loadData(){
        TextView txtPlayerName = (TextView) findViewById(R.id.txt_player_name);
        TextView txtScore = (TextView) findViewById(R.id.txt_score);

        txtPlayerName.setText(UserDAO.account.getFullName());
        txtScore.setText(String.valueOf(UserDAO.account.getScore()));
    }

    public void onButtonLogoutClick(View view){
        MainActivity.mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}