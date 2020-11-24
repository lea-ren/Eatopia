package com.example.eateria10.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eateria10.MainActivity;
import com.example.eateria10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cn.leancloud.AVException;
import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import cn.leancloud.callback.SignUpCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class RegisterActivity extends AppCompatActivity {

    private EditText UserName, UserEmail, UserPassword, UserConfirmPassword;
    private Button CreateAccountButton;
    private ProgressDialog LoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize LeanCloud
        AVOSCloud.initialize(this, "ejS0eB9vEDF5g9m5U3g65gKn-gzGzoHsz", "Kh1q0vu7hRQqLDAu8ft4tbaf", "https://ejs0eb9v.lc-cn-n1-shared.com");

        //initialization
        UserName = (EditText)findViewById(R.id.register_username);
        UserEmail = (EditText)findViewById(R.id.register_email);
        UserPassword = (EditText)findViewById(R.id.register_password);
        UserConfirmPassword = (EditText)findViewById(R.id.register_confirm_password);
        CreateAccountButton = (Button)findViewById(R.id.register_create_account);
        LoadingBar = new ProgressDialog(this);

        //when user click the create button, create a new account
        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });


    }

    //check the correctness of the input, create a new account with input info
    private void CreateNewAccount() {

        //use get to get the user inputs
        String username = UserName.getText().toString();
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        String confirmPassword = UserConfirmPassword.getText().toString();

        //show messages if any information is missing or incorrect
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please write your username...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this, "Please confirm your password...", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPassword)){
            Toast.makeText(this, "Your password does not match your confirm password.", Toast.LENGTH_SHORT).show();
        }

        //create a new account using these information if anything goes right
        else{

            //show a loading bar when the new account is creating
            LoadingBar.setTitle("Creating new account.");
            LoadingBar.setMessage("Please wait, we are creating your new account...");
            LoadingBar.show();
            LoadingBar.setCanceledOnTouchOutside(true);

            //set the account info
            AVUser user = new AVUser();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.signUpInBackground().subscribe(new Observer<AVUser>(){
                public void onSubscribe(Disposable disposable) {}
                public void onNext(AVUser user) {
                    Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    System.out.println("register successfully。objectId：" + user.getObjectId());
                    SendUserToLoginActivity();
                }
                public void onError(Throwable throwable) {
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                }
                public void onComplete() {
                    LoadingBar.dismiss();
                }

            });
        }
    }

    //the function is used to send user to loginActivity
    private void SendUserToLoginActivity() {
        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        LoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(LoginIntent);
        finish();

    }
}














