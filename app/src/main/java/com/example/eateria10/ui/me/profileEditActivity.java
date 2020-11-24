package com.example.eateria10.ui.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eateria10.R;
import com.example.eateria10.ui.login.LoginActivity;
import com.example.eateria10.ui.login.RegisterActivity;
import com.squareup.picasso.Picasso;

import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class profileEditActivity extends AppCompatActivity {

    TextView chooseProfile, confirmProfile;
    ImageView profile1,profile2,profile3,profile4,
            profile5,profile6,profile7,profile8,
            profileConfirm;
    Button confirmButton;
    AVUser currentUser;
    String currentProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        //initialize LeanCloud
        AVOSCloud.initialize(this, "ejS0eB9vEDF5g9m5U3g65gKn-gzGzoHsz", "Kh1q0vu7hRQqLDAu8ft4tbaf", "https://ejs0eb9v.lc-cn-n1-shared.com");

        //initialization
        chooseProfile = (TextView)findViewById(R.id.profile_edit_text);
        confirmProfile = (TextView)findViewById(R.id.profile_confirm_text);

        confirmButton = (Button)findViewById(R.id.profile_confirm_button);

        profile1 = (ImageView)findViewById(R.id.profile_edit_image1);
        profile2 = (ImageView)findViewById(R.id.profile_edit_image2);
        profile3 = (ImageView)findViewById(R.id.profile_edit_image3);
        profile4 = (ImageView)findViewById(R.id.profile_edit_image4);
        profile5 = (ImageView)findViewById(R.id.profile_edit_image5);
        profile6 = (ImageView)findViewById(R.id.profile_edit_image6);
        profile7 = (ImageView)findViewById(R.id.profile_edit_image7);
        profile8 = (ImageView)findViewById(R.id.profile_edit_image8);
        profileConfirm = (ImageView)findViewById(R.id.profile_edit_image_confirm);

        //get user information
        currentUser = AVUser.getCurrentUser();
        currentProfilePic = currentUser.getString("profilePic");

        //get and display the current profile picture
        switch (currentProfilePic){
            case "1":
                Picasso.get().load(R.drawable.profile_1).into(profileConfirm);
                break;
            case "2":
                Picasso.get().load(R.drawable.profile_2).into(profileConfirm);
                break;
            case "3":
                Picasso.get().load(R.drawable.profile_3).into(profileConfirm);
                break;
            case "4":
                Picasso.get().load(R.drawable.profile_4).into(profileConfirm);
                break;
            case "5":
                Picasso.get().load(R.drawable.profile_5).into(profileConfirm);
                break;
            case "6":
                Picasso.get().load(R.drawable.profile_6).into(profileConfirm);
                break;
            case "7":
                Picasso.get().load(R.drawable.profile_7).into(profileConfirm);
                break;
            case "8":
                Picasso.get().load(R.drawable.profile_8).into(profileConfirm);
                break;
            default:
                Picasso.get().load(R.drawable.me_profile_pic1).into(profileConfirm);
                break;
        }

        //onclick method for different profile image as button
        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","1");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_1).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","2");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_2).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","3");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_3).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","4");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_4).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","5");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_5).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","6");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_6).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","7");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_7).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        profile8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.put("profilePic","8");
                //currentUser.save();
                currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVObject currentUser) {
                        Picasso.get().load(R.drawable.profile_8).into(profileConfirm);
                    }
                    public void onError(Throwable throwable) {
                    }
                    public void onComplete() {}
                });
            }
        });

        //confirm the changes and save the current profile to LeanCloud
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(profileEditActivity.this, InfoEditActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });





    }
}
