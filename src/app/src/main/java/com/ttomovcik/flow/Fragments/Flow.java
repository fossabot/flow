package com.ttomovcik.flow.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ttomovcik.flow.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Flow extends Fragment {

    public Flow() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow, container, false);

        SharedPreferences settings = Objects.requireNonNull(getActivity())
                .getSharedPreferences("UserInfo", 0);

        // Init some stuff here-and-there
        de.hdodenhof.circleimageview.CircleImageView userInfo_profilePicture
                = view.findViewById(R.id.fragment_container_flow_block_greeter_profile_image);

        Log.d("Test", settings.getString("profile_profileImage", ""));
        if (Objects.requireNonNull(settings.getString("profile_profileImage", "")).contains(".")) {
            userInfo_profilePicture.setImageDrawable(
                    Drawable.createFromPath(settings.getString("profile_profileImage", "")));
        }

        // onClickListeners
        userInfo_profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
        return view;
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, 100);
    }

    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        SharedPreferences settings = Objects.requireNonNull(getActivity())
                .getSharedPreferences("UserInfo", 0);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 100:
                    Uri selectedImage = data.getData();
                    assert selectedImage != null;
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = Objects.requireNonNull(getActivity())
                            .getContentResolver()
                            .query(selectedImage,
                                    filePathColumn,
                                    null,
                                    null,
                                    null);
                    Objects.requireNonNull(cursor).moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String decodedImageFilename = cursor.getString(columnIndex);
                    cursor.close();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("profile_profileImage", String.valueOf(decodedImageFilename));
                    editor.apply();
                    ImageView imageView_profilePicture
                            = Objects.requireNonNull(getView())
                            .findViewById(R.id.fragment_container_flow_block_greeter_profile_image);
                    imageView_profilePicture.setImageBitmap(BitmapFactory.decodeFile(decodedImageFilename));
                    break;
            }
    }
}
