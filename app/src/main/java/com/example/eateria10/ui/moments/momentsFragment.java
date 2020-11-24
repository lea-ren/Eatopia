package com.example.eateria10.ui.moments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.eateria10.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.lang.String;

import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;

public class momentsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private ImageView pic1, pic2, pic3;
    private String userID1, userID2, userID3;

    //bytes to bitmap
    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            Log.v("Menu",Integer.toString(b.length));
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            Log.v("Menu","empty-bitmap");
            return null;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AVOSCloud.initialize("ejS0eB9vEDF5g9m5U3g65gKn-gzGzoHsz", "Kh1q0vu7hRQqLDAu8ft4tbaf", "https://ejs0eb9v.lc-cn-n1-shared.com");

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_moments, container, false);

        final TextView name1 = root.findViewById(R.id.name_1);
        final TextView name2 = root.findViewById(R.id.name_2);
        final TextView name3 = root.findViewById(R.id.name_3);
        final ImageView p1 = root.findViewById(R.id.profileImg_1);
        final ImageView p2 = root.findViewById(R.id.profileImg_2);
        final ImageView p3 = root.findViewById(R.id.profileImg_3);
        final TextView date1 = root.findViewById(R.id.date_1);
        final TextView date2 = root.findViewById(R.id.date_2);
        final TextView date3 = root.findViewById(R.id.date_3);
        final TextView c1 = root.findViewById(R.id.content_1);
        final TextView c2 = root.findViewById(R.id.content_2);
        final TextView c3 = root.findViewById(R.id.content_3);
        pic1 = root.findViewById(R.id.pic_1);
        pic2 = root.findViewById(R.id.pic_2);
        pic3 = root.findViewById(R.id.pic_3);

        AVQuery<AVObject> query = new AVQuery<>("notes");
        query.orderByDescending("createdAt");
        query.limit(3);
//        query.whereExists("picture");
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(List<AVObject> avObjects) {
                Log.v("first success","success");
                int size = avObjects.size();
                Log.v("size",Integer.toString(size));
                if (size >= 1){
                    AVObject note1 = avObjects.get(0);
                    userID1 = note1.getString("User");
                    String content1 = note1.getString("NoteText");
                    c1.setText(content1);
                    String d1 = String.valueOf(note1.getCreatedAt());
                    date1.setText(d1);
                    byte[] mbyte1 = note1.getBytes("picture");
                    if(mbyte1 != null){
                        Bitmap picture1 = Bytes2Bimap(mbyte1);
                        pic1.setImageBitmap(picture1);
//                    pic1.setImageResource(R.drawable.menu_29);
//                    Glide.with(root).load(mbyte1).into(pic1);
                    }

                }
                if (size >= 2){
                    AVObject note2 = avObjects.get(1);
                    userID2 = note2.getString("User");
                    String content2 = note2.getString("NoteText");
                    c2.setText(content2);
                    String d2 = String.valueOf(note2.getCreatedAt());
                    date2.setText(d2);
                    byte[] mbyte2 = note2.getBytes("picture");
                    if(mbyte2 != null){
                        Glide.with(root).load(mbyte2).into(pic2);
                    }

                }
                if (size >=3){
                    AVObject note3 = avObjects.get(2);
                    userID3 = note3.getString("User");
                    String content3 = note3.getString("NoteText");
                    c3.setText(content3);
                    String d3 = String.valueOf(note3.getCreatedAt());
                    date3.setText(d3);
                    byte[] mbyte3 = note3.getBytes("picture");
                    if(mbyte3 != null){
                        Glide.with(root).load(mbyte3).into(pic3);
                    }


                }
            }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onComplete() { }
        });

        AVQuery<AVObject> userquery1 = new AVQuery<>("_User");
        userquery1.whereEqualTo("objectId",userID1);
        userquery1.findInBackground().subscribe(new io.reactivex.Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {}
            @Override
            public void onNext(List<AVObject> avObjects) {
                AVObject user1 = avObjects.get(0);
                String n1 = user1.getString("username");
                name1.setText(n1);
                String pro1 = user1.getString("profilePic");
                switch (pro1){
                    case "1":
                        Picasso.get().load(R.drawable.profile_1).into(p1);
                        break;
                    case "2":
                        Picasso.get().load(R.drawable.profile_2).into(p1);
                        break;
                    case "3":
                        Picasso.get().load(R.drawable.profile_3).into(p1);
                        break;
                    case "4":
                        Picasso.get().load(R.drawable.profile_4).into(p1);
                        break;
                    case "5":
                        Picasso.get().load(R.drawable.profile_5).into(p1);
                        break;
                    case "6":
                        Picasso.get().load(R.drawable.profile_6).into(p1);
                        break;
                    case "7":
                        Picasso.get().load(R.drawable.profile_7).into(p1);
                        break;
                    case "8":
                        Picasso.get().load(R.drawable.profile_8).into(p1);
                        break;
                    default:
                        Picasso.get().load(R.drawable.me_profile_pic1).into(p1);
                        break;
                }
            }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onComplete() { }
        });

        AVQuery<AVObject> userquery2 = new AVQuery<>("_User");
        userquery2.whereEqualTo("objectId",userID2);
        userquery2.findInBackground().subscribe(new io.reactivex.Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {}
            @Override
            public void onNext(List<AVObject> avObjects) {
                AVObject user2 = avObjects.get(0);
                String n2 = user2.getString("username");
                name2.setText(n2);
                String pro2 = user2.getString("profilePic");
                switch (pro2){
                    case "1":
                        Picasso.get().load(R.drawable.profile_1).into(p2);
                        break;
                    case "2":
                        Picasso.get().load(R.drawable.profile_2).into(p2);
                        break;
                    case "3":
                        Picasso.get().load(R.drawable.profile_3).into(p2);
                        break;
                    case "4":
                        Picasso.get().load(R.drawable.profile_4).into(p2);
                        break;
                    case "5":
                        Picasso.get().load(R.drawable.profile_5).into(p2);
                        break;
                    case "6":
                        Picasso.get().load(R.drawable.profile_6).into(p2);
                        break;
                    case "7":
                        Picasso.get().load(R.drawable.profile_7).into(p2);
                        break;
                    case "8":
                        Picasso.get().load(R.drawable.profile_8).into(p2);
                        break;
                    default:
                        Picasso.get().load(R.drawable.me_profile_pic1).into(p2);
                        break;
                }
            }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onComplete() { }
        });

        AVQuery<AVObject> userquery3 = new AVQuery<>("_User");
        userquery3.whereEqualTo("objectId",userID3);
        userquery3.findInBackground().subscribe(new io.reactivex.Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {}
            @Override
            public void onNext(List<AVObject> avObjects) {
                AVObject user3 = avObjects.get(0);
                String n3 = user3.getString("username");
                name3.setText(n3);
                String pro3 = user3.getString("profilePic");
                switch (pro3){
                    case "1":
                        Picasso.get().load(R.drawable.profile_1).into(p3);
                        break;
                    case "2":
                        Picasso.get().load(R.drawable.profile_2).into(p3);
                        break;
                    case "3":
                        Picasso.get().load(R.drawable.profile_3).into(p3);
                        break;
                    case "4":
                        Picasso.get().load(R.drawable.profile_4).into(p3);
                        break;
                    case "5":
                        Picasso.get().load(R.drawable.profile_5).into(p3);
                        break;
                    case "6":
                        Picasso.get().load(R.drawable.profile_6).into(p3);
                        break;
                    case "7":
                        Picasso.get().load(R.drawable.profile_7).into(p3);
                        break;
                    case "8":
                        Picasso.get().load(R.drawable.profile_8).into(p3);
                        break;
                    default:
                        Picasso.get().load(R.drawable.me_profile_pic1).into(p3);
                        break;
                }
            }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onComplete() { }
        });

        return root;
    }
}