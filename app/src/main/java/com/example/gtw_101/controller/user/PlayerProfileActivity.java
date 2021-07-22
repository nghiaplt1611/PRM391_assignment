package com.example.gtw_101.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.controller.user.profile.ChangeAvatarActivity;
import com.example.gtw_101.controller.user.profile.EditProfilePlayerActivity;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.GetAvatarResource;
import com.google.firebase.auth.FirebaseAuth;

public class PlayerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        getSupportActionBar().hide();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData(){
        TextView txtPlayerName = (TextView) findViewById(R.id.txt_player_name);
        TextView txtScore = (TextView) findViewById(R.id.txt_score);
        ImageView avatar = (ImageView) findViewById(R.id.img_profile_image);

        txtPlayerName.setText(UserDAO.account.getFullName());
        txtScore.setText(String.valueOf(UserDAO.account.getScore()));
        avatar.setImageDrawable(getResources().getDrawable(GetAvatarResource.getAvatarImageID(UserDAO.account.getAvatar())));
    }

    public void onButtonLogoutClick(View view){
        MainActivity.mAuth.signOut();
        MainActivity.mAuth = null;
        MainActivity.user = null;
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finishAffinity();
    }

    public void onButtonChangePass(View view){
        MainActivity.mAuth = FirebaseAuth.getInstance();
        MainActivity.user = MainActivity.mAuth.getCurrentUser();
        MainActivity.mAuth.sendPasswordResetEmail(MainActivity.user.getEmail());
        AlertDialog dialog = AlertDialogBuilder.showAlertDialog("Notification!", "A email message has been sent to " + MainActivity.user.getEmail()+ " for verification. Please check it!", this);
        dialog.show();
    }

    public void onButtonEditProfile(View view){
        Intent intent = new Intent(this, EditProfilePlayerActivity.class);
        this.startActivity(intent);
    }

    public void onButtonChangeAvatar(View view){
        Intent intent = new Intent(this, ChangeAvatarActivity.class);
        this.startActivity(intent);
    }

    public void returnToUserMenuIntent(View view){
        this.finish();
    }


}