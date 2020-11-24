package com.example.eateria10.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

//import com.example.eateria10.FullMenuActivity;
import com.bumptech.glide.Glide;
import com.example.eateria10.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;

public class meFragment extends Fragment {

    private meViewModel myViewModel;
    private Button myEdit;
    private ImageView myNote1, myNote2, myNote3;
    private AVUser currentUser;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //initialize LeanCloud
        AVOSCloud.initialize("ejS0eB9vEDF5g9m5U3g65gKn-gzGzoHsz", "Kh1q0vu7hRQqLDAu8ft4tbaf", "https://ejs0eb9v.lc-cn-n1-shared.com");
        currentUser = AVUser.getCurrentUser();

        //initialization
        myViewModel = ViewModelProviders.of(this).get(meViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_me, container, false);
        final TextView username = root.findViewById(R.id.my_username);
        final TextView numOfDish = root.findViewById(R.id.my_dish_num);
        final TextView isSpicy = root.findViewById(R.id.my_user_spicy);
        final TextView isDiet = root.findViewById(R.id.my_user_diet);
        final CircleImageView profileImage = root.findViewById(R.id.my_profile_pic);
        myNote1 = root.findViewById(R.id.my_note_1);
        myNote2 = root.findViewById(R.id.my_note_2);
        myNote3 = root.findViewById(R.id.my_note_3);

        //query 3 latest notes
        AVQuery<AVObject> query = new AVQuery<>("notes");
        query.whereEqualTo("User",currentUser);
        query.findInBackground().subscribe(new io.reactivex.Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {}

            //show pics differently according to the number of notes
            @Override
            public void onNext(List<AVObject> avObjects) {
                int size = avObjects.size();
                System.out.println(size);
                if (size >= 1){
                    AVObject note1 = avObjects.get(0);
                    byte[] mbyte1 = note1.getBytes("picture");
                    Glide.with(root).load(mbyte1).into(myNote1);
                }
                if (size >= 2){
                    AVObject note2 = avObjects.get(1);
                    byte[] mbyte2 = note2.getBytes("picture");
                    Glide.with(root).load(mbyte2).into(myNote2);
                }
                if (size >=3){
                    AVObject note3 = avObjects.get(2);
                    byte[] mbyte3 = note3.getBytes("picture");
                    Glide.with(root).load(mbyte3).into(myNote3);
                }
            }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onComplete() { }
        });

        //initialize edit button and let user jump to infoEditActivity after
        //clicking the button
        myEdit = root.findViewById(R.id.my_edit_button);
        myEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setupIntent = new Intent(getActivity(), InfoEditActivity.class);
                startActivity(setupIntent);

            }
        });

        //update the messages displayed on screen if there is any updates
        myViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                username.setText(s);
            }
        });
        myViewModel.getNum().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                numOfDish.setText(s);
            }
        });
        myViewModel.getSpicy().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean s) {
                if (s.equals(true)){ isSpicy.setText("YES"); }
                else{isSpicy.setText("NO");}
            }
        });
        myViewModel.getDiet().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean s) {
                if (s.equals(true)){ isDiet.setText("YES"); }
                else{isDiet.setText("NO");}
            }
        });
        myViewModel.getProfilePic().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                switch (s){
                    case "1":
                        Picasso.get().load(R.drawable.profile_1).into(profileImage);
                        break;
                    case "2":
                        Picasso.get().load(R.drawable.profile_2).into(profileImage);
                        break;
                    case "3":
                        Picasso.get().load(R.drawable.profile_3).into(profileImage);
                        break;
                    case "4":
                        Picasso.get().load(R.drawable.profile_4).into(profileImage);
                        break;
                    case "5":
                        Picasso.get().load(R.drawable.profile_5).into(profileImage);
                        break;
                    case "6":
                        Picasso.get().load(R.drawable.profile_6).into(profileImage);
                        break;
                    case "7":
                        Picasso.get().load(R.drawable.profile_7).into(profileImage);
                        break;
                    case "8":
                        Picasso.get().load(R.drawable.profile_8).into(profileImage);
                        break;
                    default:
                        Picasso.get().load(R.drawable.me_profile_pic1).into(profileImage);
                        break;
                }
            }
        });

        return root;
    }



}




