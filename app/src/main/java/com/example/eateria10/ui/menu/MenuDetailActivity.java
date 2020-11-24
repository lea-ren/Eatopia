package com.example.eateria10.ui.menu;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eateria10.MainActivity;
import com.example.eateria10.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MenuDetailActivity extends AppCompatActivity {
    //button
    private Button button_mark;
    //text
    private TextView dishname_text;
    private TextView ingredients_text;
    private TextView cooker_text;
    private TextView procedure_text;
    //image
    private ImageView dish_image;
    //rating bar
    private RatingBar difficulty_ratingBar;
    private AVObject dish_obj;
    private String objectID;
    private Boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Intent intent = getIntent();
        final int dish_ID = intent.getIntExtra("dishID",0);
        button_mark = (Button)findViewById(R.id.button_mark);
        dishname_text = findViewById(R.id.dishname_text);
        ingredients_text = findViewById(R.id.ingredients_text);
        cooker_text = findViewById(R.id.cooker_text);
        procedure_text = findViewById(R.id.procedure_text);
        dish_image = findViewById(R.id.dish_image);
        difficulty_ratingBar = findViewById(R.id.difficulty_ratingBar);
        //set image
        String imagesrc = "menu_"+dish_ID;
        int imageID = getResources().getIdentifier(imagesrc, "drawable" , getPackageName());
        Picasso.get().load(imageID).into(dish_image);
        //get user
        final AVUser currentUser = AVUser.currentUser();
        AVQuery<AVObject> query = new AVQuery<>("testmenu");
        query.whereEqualTo("dishID",dish_ID);
        //get dish object
        query.selectKeys(Arrays.asList("dishID","dishName","ingredients","kitchenware","procedure","difficulty"));
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<AVObject> menu) {
                for (AVObject dish : menu) {
                    //put into list
                    dish_obj = dish;
                    int dishID = dish.getInt("dishID");
                    String dishName = dish.getString("dishName");
                    String ingredients = dish.getString("ingredients");
                    String kitchenware = dish.getString("kitchenware");
                    String procedure = dish.getString("procedure");
                    int difficulty = dish.getInt("difficulty");
                    //set Text
                    dishname_text.setText(dishName);
                    //deal with the string
                    ingredients = ingredients.replace(",","\n");
                    ingredients = ingredients.replace(";","\n");
                    ingredients_text.setText(ingredients);
                    kitchenware = kitchenware.replace(",","\n");
                    cooker_text.setText(kitchenware);
                    procedure_text.setText(procedure);
                    difficulty_ratingBar.setRating(difficulty+1);
                }
            }

            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });

        //check whether you have been done it before
        final AVQuery<AVObject> cooked_dishes = new AVQuery<>("CookedDishes");
        cooked_dishes.whereEqualTo("UserID", currentUser);
        cooked_dishes.whereEqualTo("dishID",dish_ID);
        cooked_dishes.getFirstInBackground().subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(AVObject dish) {
                isChecked = true;
            }
            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });

        button_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submit
                if(!isChecked){
                    //save to the database
                    AVObject cooked_dishes = new AVObject("CookedDishes");
                    cooked_dishes.put("UserID",currentUser);
                    cooked_dishes.put("dishID",dish_ID);
                    cooked_dishes.saveInBackground().subscribe(new Observer<AVObject>() {
                        public void onSubscribe(Disposable disposable) {
                        }
                        public void onNext(AVObject cooked_dishes) {
                            //succeed
                            Toast.makeText(MenuDetailActivity.this, "Save successfully...", Toast.LENGTH_SHORT).show();
                            isChecked = true;
                        }
                        public void onError(Throwable throwable) {
                            //fail
                            Toast.makeText(MenuDetailActivity.this, "Save failed...", Toast.LENGTH_SHORT).show();
                        }
                        public void onComplete() {
                        }
                    });

                }
                else{
                    // you have done it before
                    Toast.makeText(MenuDetailActivity.this, "You have done it before.", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
