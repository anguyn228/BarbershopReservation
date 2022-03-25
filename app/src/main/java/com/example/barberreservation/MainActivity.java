package com.example.barberreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button register, signIn;
    EditText username, password;
    private ArrayList<UserModel> users;
    private String USERID = "", TITLE = "", NAME = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.loginbtn1);
        register = (Button) findViewById(R.id.signupbtnreturn);

        signIn.setOnClickListener(v ->
        {
            String userN = username.getText().toString().trim();
            String passW = password.getText().toString().trim();

            // validate credentials
            if (userN.isEmpty() || passW.isEmpty()) {
                Toast.makeText(this, "Username and password required", Toast.LENGTH_SHORT).show();
                return;

            }  if (userN.equalsIgnoreCase("admin") && passW.equals("admin")) {
            // default user
            startActivity(new Intent(MainActivity.this, UserDashBoard.class));
            finish();

            } else {
                signIn(userN, passW);
            }
        });

    }

        private void signIn(String userN, String passW) {

            boolean status = false;
            // find if this user exists
            for (UserModel d : users) {
                if (d.getUserId().equalsIgnoreCase(userN) && d.getPassword().equals(passW)) {

                    String role = d.getTitle();
                    USERID = d.getUserId();
                    NAME = d.getName();
                    signInByRole(role);
                    status = true;
                    break;
                }
            }


    }
    private void signInByRole(String role) {
        switch (role.toLowerCase()) {
            case "admin":
                startActivity(new Intent(MainActivity.this, AdminDashBoard.class)
                        .putExtra("userid", USERID));
                finish();
                break;
            case "customer":
                startActivity(new Intent(MainActivity.this, UserDashBoard.class)
                        .putExtra("userid", USERID)
                        .putExtra("name", NAME));
                finish();
                break;
            case "barber":
                startActivity(new Intent(MainActivity.this, StaffDashBoard.class)
                        .putExtra("userid", USERID));
                finish();
                break;

            default:
                break;
        }
    }
}