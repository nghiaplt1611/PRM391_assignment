package com.example.gtw_101.controller.user.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.account.RegisterActivity;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.GetAvatarResource;
import com.example.gtw_101.utilities.Validation;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfilePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_player);
        getSupportActionBar().hide();
        loadData();
    }

    public void loadData(){
        CircleImageView avatar = (CircleImageView) findViewById(R.id.profile_image);
        EditText txtEmail = (EditText) findViewById(R.id.txt_input_email);
        EditText txtFullName = (EditText) findViewById(R.id.txt_input_name);
        EditText txtYearOfBirth = (EditText) findViewById(R.id.txt_input_dob);

        avatar.setImageResource(GetAvatarResource.getAvatarImageID(UserDAO.account.getAvatar()));
        txtEmail.setText(UserDAO.account.getEmail());
        txtFullName.setText(UserDAO.account.getFullName());
        txtYearOfBirth.setText(String.valueOf(UserDAO.account.getYearOfBirth()));
    }

    public void onButtonUpdateClick(View view){
        EditText txtFullName = (EditText) findViewById(R.id.txt_input_name);
        EditText txtYearOfBirth = (EditText) findViewById(R.id.txt_input_dob);

        String fullName = txtFullName.getText().toString().trim();
        String yearOfBirth = txtYearOfBirth.getText().toString().trim();

        if (Validation.checkNullData(new String[]{fullName, yearOfBirth})){
            if (fullName.isEmpty()){
                txtFullName.setError("Full name cannot be empty! Please input!");
            }

            if (yearOfBirth.isEmpty()){
                txtYearOfBirth.setError("Year of birth cannot be empty! Please input!");
            }
        }
        else if (!Validation.checkUpdateFormat(fullName, yearOfBirth)){
            if (!Validation.checkFullNameFormat(fullName)){
                txtFullName.setError("Full name must be in correct format. (Ex: Tri Thanh)");
            }

            if (!Validation.checkYearOfBirth(yearOfBirth)){
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                txtYearOfBirth.setError("Year of birth must be a positive number between 1900 and " + currentYear);
            }
        }
        else {
            UserDAO.account.setFullName(fullName);
            UserDAO.account.setYearOfBirth(Integer.parseInt(yearOfBirth));
            UserDAO.updateProfile(UserDAO.account.getId(), UserDAO.account.getFullName(), UserDAO.account.getYearOfBirth());

            AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilePlayerActivity.this);
            builder.setMessage("Updated successfully!");
            builder.setTitle("Notification!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();
        }
    }

    public void hideFullNameError(View view){
        EditText txtFullName = findViewById(R.id.txt_input_name);
        txtFullName.setError(null);
    }

    public void hideYearOfBirthError(View view){
        EditText txtYearOfBirth = findViewById(R.id.txt_input_dob);
        txtYearOfBirth.setError(null);
    }
}