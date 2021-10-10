package com.example.project.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.Domain.ProfileDomain;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.example.project.Helper.TinyDB;
import com.example.project.R;

import org.jetbrains.annotations.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {
    public static final int REQUEST_IMAGE = 100;
    private static final String TAG = MainActivity.class.getSimpleName();

    TinyDB tinyDB;

    CircleImageView imgProfile;
    Button btnChangeImage, btnSave;
    EditText etName, etTelp, etEmail, etAddress;

    String name, address, telp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tinyDB = new TinyDB(this);

        imgProfile = findViewById(R.id.imgProfile);
        btnChangeImage = findViewById(R.id.btnEditPhoto);
        btnSave = findViewById(R.id.btnSubmit);
        etName = findViewById(R.id.etName);
        etTelp = findViewById(R.id.etPhoneNumber);
        etAddress = findViewById(R.id.etAddress);
        etEmail = findViewById(R.id.etEmail);

        btnChangeImage.setOnClickListener(ChangeImage);
        btnSave.setOnClickListener(save);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@androidx.annotation.Nullable final String name) {
                if (name != null) {
                    if (!name.equals("")) {
                        etName.setText(name);
                    }
                }
            }
        };

        final Observer<String> telpObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String telp) {
                if (telp != null) {
                    if (!telp.equals("")) {
                        etTelp.setText(telp);
                    }
                }
            }
        };

        final Observer<String> emailObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String email) {
                if (email != null) {
                    if (!email.equals("")) {
                        etEmail.setText(email);
                    }
                }
            }
        };

        final Observer<String> addressObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String address) {
                if (address != null) {
                    if (!address.equals("")) {
                        etAddress.setText(address);
                    }
                }
            }
        };

        final Observer<String> imgProfileObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String imageURI) {
                if (imageURI != null) {
                    if (!imageURI.equals("")) {
                        imgProfile.setImageURI(Uri.parse(imageURI));
                    }
                }
            }
        };

        ProfileDomain profile = ProfileDomain.getInstance(this);
        profile.getName().observe(this, nameObserver);
        profile.getAddress().observe(this, addressObserver);
        profile.getTelp().observe(this, telpObserver);
        profile.getEmail().observe(this, emailObserver);
        profile.getImageURI().observe(this, imgProfileObserver);

        ImagePickerActivity.clearCache(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    View.OnClickListener ChangeImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Dexter.withActivity(EditProfileActivity.this)
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                showImagePickerOptions();
                            }

                            if (report.isAnyPermissionPermanentlyDenied()) {
                                showSettingsDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        }
    };

    View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            name = etName.getText().toString();
            address = etAddress.getText().toString();
            telp = etTelp.getText().toString();
            email = etEmail.getText().toString();

            boolean success = false;
            ProfileDomain profile = ProfileDomain.getInstance(view.getContext());

            if(!name.equals("")){
                profile.getName().setValue(name);
                success = true;
            }
            if(!address.equals("")){
                profile.getAddress().setValue(address);
                success = true;
            }
            if(!telp.equals("")){
                profile.getTelp().setValue(telp);
                success = true;
            }
            if(!email.equals("")){
                profile.getEmail().setValue(email);
                success = true;
            }

            profile.save(view.getContext());

            if (success) {
                Toast.makeText(view.getContext(), "Profil tersimpan!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(view.getContext(), "Gagal!", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void OnTakeCamera() {
                launchCameraIntent();
            }

            @Override
            public void OnChooseGallery() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(EditProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(EditProfileActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityIfNeeded(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Uri uri = data.getParcelableExtra("path");
                    // You can update this bitmap to your server
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    // loading profile image from local cache
                    ProfileDomain profile = ProfileDomain.getInstance(this);
                    profile.getImageURI().setValue(uri.toString());
                    profile.save(this);
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
}