package com.example.eateria10.ui.me;

import android.media.Image;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eateria10.R;
import com.squareup.picasso.Picasso;

import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class meViewModel extends ViewModel {
    private MutableLiveData<String> username;
    private MutableLiveData<String> numOfDish;
    private MutableLiveData<String> profileImage;
    private MutableLiveData<Boolean> isSpicy;
    private MutableLiveData<Boolean> isDiet;

    AVUser currentUser;

    public meViewModel() {
        //initialize LeanCloud
        AVOSCloud.initialize("ejS0eB9vEDF5g9m5U3g65gKn-gzGzoHsz", "Kh1q0vu7hRQqLDAu8ft4tbaf", "https://ejs0eb9v.lc-cn-n1-shared.com");
        currentUser = AVUser.getCurrentUser();

        //initialization
        username = new MutableLiveData<>();
        numOfDish = new MutableLiveData<>();
        profileImage = new MutableLiveData<>();
        isSpicy = new MutableLiveData<>();
        isDiet = new MutableLiveData<>();

        //fetch value from LeanCloud and save the value in current variable
        profileImage.setValue(currentUser.getString("profilePic"));
        username.setValue(currentUser.getUsername());
        isSpicy.setValue(currentUser.getBoolean("canSpicy"));
        isDiet.setValue(currentUser.getBoolean("loseWeightDiet"));

        //query number of dishes in the table cooked dishes
        AVQuery<AVObject> query = new AVQuery<>("CookedDishes");
        query.whereEqualTo("UserID", currentUser);
        query.countInBackground().subscribe(new Observer<Integer>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(Integer count) {
                numOfDish.setValue(count.toString());
            }
            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });


    }

    //allow the display to update using folowing methods
    public LiveData<Boolean> getSpicy() {return isSpicy;}
    public LiveData<Boolean> getDiet() {return isDiet;}
    public LiveData<String> getProfilePic() {return profileImage;}
    public LiveData<String> getText() {
        return username;
    }
    public LiveData<String> getNum() {return numOfDish;}

}
