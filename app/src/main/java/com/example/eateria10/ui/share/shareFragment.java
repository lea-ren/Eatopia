package com.example.eateria10.ui.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.eateria10.R;
import com.example.eateria10.ui.share.shareViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class shareFragment extends Fragment {
    private EditText Note;
    private Button UploadButton, PostButton, PhotoButton;
    private ImageView notePicture;
    AVUser currentUser;
    private shareViewModel dashboardViewModel;
    byte[] notePic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        Note = (EditText) root.findViewById(R.id.text_dashboard);
        notePicture = root.findViewById(R.id.note_image);
        PostButton = root.findViewById(R.id.post_button);
        currentUser = AVUser.getCurrentUser();
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
        UploadButton = root.findViewById(R.id.upload_button);
        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                album();
            }
        });
        PhotoButton = root.findViewById(R.id.upload_button1);
        PhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
            }
        });
        return root;
    }

    public void post() {
        AVObject notes = new AVObject("notes");
        String note = Note.getText().toString();
        notes.put("NoteText", note);
        notes.put("User", currentUser);
        notes.put("picture", notePic);
        notes.saveInBackground().subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext(AVObject notes) {
                Toast.makeText(getActivity(), "Post successfully...", Toast.LENGTH_SHORT).show();
            }
            public void onError(Throwable throwable) {
                Toast.makeText(getActivity(), "Post failed...", Toast.LENGTH_SHORT).show();
            }
            public void onComplete() {
            }
        });

    }
    //invoke phone OS camera
    public void camera() {
        Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);
    }
    //invoke phone OS album
    public void album() {
        Intent getImage = new Intent(Intent.ACTION_PICK, null);
        getImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(getImage, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case 1:
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap1 = (Bitmap) bundle.get("data");
                    notePic = Bitmap2Bytes(bitmap1);
                    break;
                case 2:
                    if (data != null) {
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                            notePic = Bitmap2Bytes(bitmap2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }}}}
    //transform bitmap into bytes
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 10, baos);
        notePicture.setImageBitmap(bm);
        return baos.toByteArray();
    }
}