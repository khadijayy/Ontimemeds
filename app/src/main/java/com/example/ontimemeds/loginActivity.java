package com.example.ontimemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class loginActivity extends AppCompatActivity {
    EditText loginUserName, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.loginRedirectText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUserName() | !validatePassword()) {
                } else {
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, signupActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateUserName() {
        String val = loginUserName.getText().toString();
        if (val.isEmpty()) {
            loginUserName.setError("Please Enter Username");
            return false;
        } else {
            loginUserName.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Please Enter Password");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUserName = loginUserName.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        // Access the Room database
        AppDatabase db = AppDatabase.getInstance(loginActivity.this);
        UserDao userDao = db.userDao();

        // Use background thread for database operation
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Perform the database query in background
                User user = userDao.getUserByUsername(userUserName);

                // Return to UI thread to update UI components
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (user != null) {
                            String passwordFromDB = user.getPassword();
                            if (passwordFromDB.equals(userPassword)) {
                                Intent intent = new Intent(loginActivity.this, mainpageActivity.class);
                                startActivity(intent);
                            } else {
                                loginPassword.setError("Invalid Credentials");
                                loginPassword.requestFocus();
                            }
                        } else {
                            loginUserName.setError("User does not exist");
                            loginUserName.requestFocus();
                        }
                    }
                });
            }
        });
    }
}
