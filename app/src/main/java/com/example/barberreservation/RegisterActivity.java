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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText username1, password1, address, cusname;
    private DatabaseHelper dataBaseHelper;
    Button signupbtn, loginreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataBaseHelper = new DatabaseHelper(this);

        username1 = (EditText) findViewById(R.id.editTextUserName);
        password1 = (EditText) findViewById(R.id.editTextPassWord);
        address = (EditText) findViewById(R.id.editTextAddress);
        cusname = (EditText) findViewById(R.id.editTextTextPersonName);

        signupbtn = (Button) findViewById(R.id.signupbtn2);

        loginreturn = (Button) findViewById(R.id.loginreturn);

        signupbtn.setOnClickListener(v -> {
            // get user inputs
            String username = username1.getText().toString().trim();
            String pw = password1.getText().toString().trim();
            String name = cusname.getText().toString().trim();
            String location = address.getText().toString().trim();
            // validate input
            if(username.isEmpty() || pw.isEmpty()){
                Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            registerUser(username, pw, name, location);

        });
    }
    private void registerUser(String username, String pw, String name, String location) {


       CustomerModel customer = new CustomerModel( username, location, name);
        String name1 = name + "";
        String role = "Customer";
        UserModel data = new UserModel(name, role, pw, username);

        // save user to db
        if(dataBaseHelper.addUser(data)){ // user was added successfully
            // add patient's data
            if(dataBaseHelper.addCustomerInfo(customer)){
                Toast.makeText(this, "Registration was successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();

        }
    }


    }









