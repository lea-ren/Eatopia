package com.example.eateria10.ui.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eateria10.MainActivity;
import com.example.eateria10.R;
import com.example.eateria10.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import cn.leancloud.AVFile;
import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;
import io.reactivex.Observer;

public class InfoEditActivity extends AppCompatActivity {


    private EditText UserName;
    private Button EditInfoButton,LogoutButton;
    private CircleImageView ProfileImage;
    private ImageView img;
    private CheckBox IsDiet, IsSpicy, NeverCook;
    private TextView WhySetUp;
    final static int galleryPick = 1;
    AVUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);

        //initialize LeanCloud
        AVOSCloud.initialize(this, "ejS0eB9vEDF5g9m5U3g65gKn-gzGzoHsz", "Kh1q0vu7hRQqLDAu8ft4tbaf", "https://ejs0eb9v.lc-cn-n1-shared.com");

        //initialization
        UserName = (EditText) findViewById(R.id.info_username);
        EditInfoButton = (Button)findViewById(R.id.info_save_button);
        LogoutButton = (Button)findViewById(R.id.info_logout_button);
        ProfileImage = (CircleImageView)findViewById(R.id.info_profile_image);
        img = (ImageView)findViewById(R.id.info_img);
        IsDiet = (CheckBox) findViewById(R.id.info_isDiet);
        IsSpicy = (CheckBox) findViewById(R.id.info_isSpicy);
        NeverCook = (CheckBox) findViewById(R.id.info_neverCook);

        //get current user object and fetch profile pic
        currentUser = AVUser.getCurrentUser();
        String profilePic = currentUser.getString("profilePic");

        //get which is user's profile image and load it from drawable
        switch (profilePic){
            case "1":
                Picasso.get().load(R.drawable.profile_1).into(ProfileImage);
                break;
            case "2":
                Picasso.get().load(R.drawable.profile_2).into(ProfileImage);
                break;
            case "3":
                Picasso.get().load(R.drawable.profile_3).into(ProfileImage);
                break;
            case "4":
                Picasso.get().load(R.drawable.profile_4).into(ProfileImage);
                break;
            case "5":
                Picasso.get().load(R.drawable.profile_5).into(ProfileImage);
                break;
            case "6":
                Picasso.get().load(R.drawable.profile_6).into(ProfileImage);
                break;
            case "7":
                Picasso.get().load(R.drawable.profile_7).into(ProfileImage);
                break;
            case "8":
                Picasso.get().load(R.drawable.profile_8).into(ProfileImage);
                break;
            default:
                Picasso.get().load(R.drawable.me_profile_pic1).into(ProfileImage);
                break;
        }

        //send user to setProfileImageActivity after clicking that button
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToSetProfileImageActivity();
            }
        });

        //save the edited info after clicking the button
        EditInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveInformation();
            }
        });

        //logout after clicking the button
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser.logOut();
                sendUserToMainActivity();
            }
        });
    }

    //jump to setProfileImageActivity
    private void SendUserToSetProfileImageActivity() {
        Intent profileEditIntent = new Intent(InfoEditActivity.this, profileEditActivity.class);
        profileEditIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profileEditIntent);
        finish();
    }

    //get user input and save them to LeanCloud
    private void saveInformation() {

        String username = UserName.getText().toString();

        if (!TextUtils.isEmpty(username)){
            currentUser.setUsername(username);
        }
        currentUser.put("canSpicy",IsSpicy.isChecked());
        currentUser.put("loseWeightDiet",IsDiet.isChecked());
        currentUser.put("neverCook",NeverCook.isChecked());
        currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(AVObject todo) {
                Toast.makeText(InfoEditActivity.this, "Save successfully...", Toast.LENGTH_SHORT).show();
                sendUserToMainActivity();
            }
            public void onError(Throwable throwable) {
                Toast.makeText(InfoEditActivity.this, "Save failed...", Toast.LENGTH_SHORT).show();
            }
            public void onComplete() {}
        });

    }

    //jump to mainActivity
    private void sendUserToMainActivity() {
        Intent mainIntent = new Intent(InfoEditActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
