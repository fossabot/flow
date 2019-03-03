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
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.ttomovcik.flow.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Flow extends Fragment
{

    public Flow()
    {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_flow, container, false);

        SharedPreferences settings = Objects.requireNonNull(getActivity())
                .getSharedPreferences("UserInfo", 0);

        de.hdodenhof.circleimageview.CircleImageView userInfo_profilePicture
                = view.findViewById(R.id.textView_flowFragment_greeter);
        TextView textView_greeter_name = view.findViewById(R.id.textView_flowFragment_greeter_name);
        MaterialButton materialButtonShowPicturesAll
                = view.findViewById(R.id.button_flowFragment_action_showPhotosAll);

        if (Objects.requireNonNull(settings.getString("profile_profileImage", "")).contains("."))
        {
            Log.i("Flow", "Setting up profile picture from file: "
                    + settings.getString("profile_profileImage", ""));
            userInfo_profilePicture.setImageDrawable(
                    Drawable.createFromPath(settings.getString("profile_profileImage", "")));
        }

        userInfo_profilePicture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickImageFromGallery();
            }
        });
        textView_greeter_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                * TODO (1): Store user's name and show "Hello, {name}!", if already set.
                * Also, hide the hint informing that they can setup their profile.
                */
                Log.d("Flow", "Hello there!");
            }
        });
        materialButtonShowPicturesAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return view;
    }

    private void pickImageFromGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, 100);
    }

    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data)
    {
        SharedPreferences settings = Objects.requireNonNull(getActivity())
                .getSharedPreferences("UserInfo", 0);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode)
            {
                case 100:
                    Uri selectedImage = data.getData();
                    assert selectedImage != null;
                    Log.i("Flow", "Saving profile image path: " + selectedImage);
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
                            .findViewById(R.id.textView_flowFragment_greeter);
                    Log.i("Flow", "Setting up profile picture from file: " + decodedImageFilename);
                    imageView_profilePicture.setImageBitmap(BitmapFactory.decodeFile(decodedImageFilename));
                    break;
            }
    }
}
