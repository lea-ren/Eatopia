package com.example.eateria10.ui.menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.eateria10.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class menuFragment extends Fragment {
    private menuViewModel menuViewModel;
    //button
    private Button button_mon;//
    private Button button_tue;
    private Button button_wed;//
    private Button button_thu;
    private Button button_fri;//
    private Button button_sat;
    private Button button_sun;
    //text
    private TextView bre_text;
    private TextView lun_text;
    private TextView sup_text;
    //image
    private ImageView bre_graph;
    private ImageView lun_graph;
    private ImageView sup_graph;

    private boolean selectflag = false;
    //initialize a container to store menu
    private List<List<String>> select_menu = new ArrayList<>();
    //initialize a list to store menu id.
    private List<Integer> menu_id = new ArrayList<>();
    //record the weekday
    private int day;

    //bytes to drawable
    public Drawable byteToDrawable(byte[] mByte){
        return Drawable.createFromStream(new ByteArrayInputStream(mByte),null);
    }
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

    //select menu
    public void  select(boolean canSpicy, boolean loseWeightDiet, boolean neverCook){
        //select menu
        AVQuery<AVObject> query = new AVQuery<>("testmenu");
        //spicy
        query.whereContainedIn("spicy",Arrays.asList(canSpicy,Boolean.FALSE));
        //lose weight
        query.whereContainedIn("weightLossDiet",Arrays.asList(loseWeightDiet,Boolean.TRUE));
        //difficulty
        if(neverCook){
            //difficult < 2
            query.whereLessThan("difficulty",2);
        }
        query.selectKeys(Arrays.asList("dishID","dishName","ingredients","kitchenware","procedure","difficulty"));
        Log.v("second","success");
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<AVObject> menu) {
                for (AVObject dish : menu) {
                    //put into list
                    Log.v("dish","success");
                    int dishID = dish.getInt("dishID");
                    String dishName = dish.getString("dishName");
                    String ingredients = dish.getString("ingredients");
                    String kitchenware = dish.getString("kitchenware");
                    String procedure = dish.getString("procedure");
                    int difficulty = dish.getInt("difficulty");
                    menu_id.add(dishID);
                    List<String> temp = new ArrayList<>();
                    temp.add(Integer.toString(dishID));
                    temp.add(dishName);
                    temp.add(ingredients);
                    temp.add(kitchenware);
                    temp.add(procedure);
                    temp.add(Integer.toString(difficulty));
                    select_menu.add(temp);
                }
                changeUI(day,bre_text,lun_text,sup_text);
            }

            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });

    }

    //change text and image
    public void changeUI(int w, TextView bre_text, TextView lun_text, TextView sup_text){
        int size = select_menu.size();
        //get filename of the pictures
        String bre = "menu_"+select_menu.get((w*3-3)%size).get(0);
        String lun = "menu_"+select_menu.get((w*3-2)%size).get(0);
        String sup = "menu_"+select_menu.get((w*3-1)%size).get(0);
        //get resID of the pictures
        int bre_resID = getResources().getIdentifier(bre, "drawable" , getContext().getPackageName());
        int lun_resID = getResources().getIdentifier(lun, "drawable" , getContext().getPackageName());
        int sup_resID = getResources().getIdentifier(sup, "drawable" , getContext().getPackageName());
        //load pictures using picasso
        Picasso.get().load(bre_resID).into(bre_graph);
        Picasso.get().load(lun_resID).into(lun_graph);
        Picasso.get().load(sup_resID).into(sup_graph);
        //get dish name and set text
        String bre_dish = select_menu.get((w*3-3)%size).get(1);
        bre_text.setText(bre_dish);
        String lun_dish = select_menu.get((w*3-2)%size).get(1);
        lun_text.setText(lun_dish);
        String sup_dish = select_menu.get((w*3-1)%size).get(1);
        sup_text.setText(sup_dish);

    }


    //get date
    public int getWeekOfDate() {
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0)
            w = 7;
        return w;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        menuViewModel =
                ViewModelProviders.of(this).get(menuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        // set buttons and graphs
        button_mon = root.findViewById(R.id.button_mon);
        button_tue = root.findViewById(R.id.button_tue);
        button_wed = root.findViewById(R.id.button_wed);
        button_thu = root.findViewById(R.id.button_thu);
        button_fri = root.findViewById(R.id.button_fri);
        button_sat = root.findViewById(R.id.button_sat);
        button_sun = root.findViewById(R.id.button_sun);
        bre_graph = root.findViewById(R.id.bre_graph);
        bre_text = root.findViewById(R.id.text_bre);
        lun_text = root.findViewById(R.id.text_lun);
        sup_text = root.findViewById(R.id.text_sup);
        bre_graph = root.findViewById(R.id.bre_graph);
        lun_graph = root.findViewById(R.id.lun_graph);
        sup_graph = root.findViewById(R.id.sup_graph);
        //if login
        AVUser currentUser = AVUser.currentUser();
        if(currentUser != null) {
            //default choose today's menu
            int w = getWeekOfDate();
            day = w;
            Log.v("day",Integer.toString(day));
            //get user information
            boolean canSpicy = currentUser.getBoolean("canSpicy");
            boolean loseWeightDiet = currentUser.getBoolean("loseWeightDiet");
            boolean neverCook = currentUser.getBoolean("neverCook");
            select(canSpicy,loseWeightDiet,neverCook);


            //random change the flag
            if(selectflag){
                Collections.shuffle(select_menu);
            }


        }


        //when click button
        //mon
        button_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(1,bre_text,lun_text,sup_text);
                day = 1;
            }
        });
        //tue
        button_tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(2,bre_text,lun_text,sup_text);
                day = 2;
            }
        });
        //wed
        button_wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(3,bre_text,lun_text,sup_text);
                day = 3;
            }
        });
        //thur
        button_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(4,bre_text,lun_text,sup_text);
                day = 4;
            }
        });
        //fri
        button_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(5,bre_text,lun_text,sup_text);
                day = 5;
            }
        });
        //sat
        button_sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(6,bre_text,lun_text,sup_text);
                day = 6;
            }
        });
        //sun
        button_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI(7,bre_text,lun_text,sup_text);
                day = 7;
            }
        });
        //when click picture
        //bre
        bre_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuDetailIntent = new Intent(getActivity(), MenuDetailActivity.class);
                int dishID = Integer.valueOf(select_menu.get(day*3-3).get(0));
                MenuDetailIntent.putExtra("dishID",dishID);
                startActivity(MenuDetailIntent);

            }
        });
        //lun
        lun_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuDetailIntent = new Intent(getActivity(), MenuDetailActivity.class);
                int dishID = Integer.valueOf(select_menu.get(day*3-2).get(0));
                MenuDetailIntent.putExtra("dishID",dishID);
                startActivity(MenuDetailIntent);

            }
        });
        //sup
        sup_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuDetailIntent = new Intent(getActivity(), MenuDetailActivity.class);
                int dishID = Integer.valueOf(select_menu.get(day*3-1).get(0));
                MenuDetailIntent.putExtra("dishID",dishID);
                startActivity(MenuDetailIntent);

            }
        });



        return root;
    }





}
