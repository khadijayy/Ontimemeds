package com.example.ontimemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class signupActivity extends AppCompatActivity {
    EditText signupName, signupEmail, signupUserName, signupPassword;
    TextView loginRedirectText;
    Button signupButton;

    // Room Database instance
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Initialize UI components
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupUserName = findViewById(R.id.signup_username);
        loginRedirectText = findViewById(R.id.LoginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        // Initialize Room Database instance
        AppDatabase db = AppDatabase.getInstance(signupActivity.this);
        userDao = db.userDao();

        // Signup button click listener
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String username = signupUserName.getText().toString().trim();

                // Validation for empty fields
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(signupActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution if fields are empty
                }

                // Create User object
                User user = new User(name, email, username, password);

                // Insert user into Room database in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.insertUser(user); // Insert user into Room
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(signupActivity.this, "Sign Up Successful dija!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signupActivity.this, loginActivity.class);
                                startActivity(intent); // Redirect to login page after successful sign up
                            }
                        });
                    }
                }).start();
            }
        });
    }

    // Login page redirect method
    public void LoginPage(View view) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}
