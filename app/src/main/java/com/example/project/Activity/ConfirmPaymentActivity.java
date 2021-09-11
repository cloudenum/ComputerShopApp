package com.example.project.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

public class ConfirmPaymentActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Button btnSelectFile = findViewById(R.id.btnSelectFile);
        TextView txtSelectedFile = findViewById(R.id.txtSelectedFile);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> txtSelectedFile.setText(getFileName(uri)));

        btnSelectFile.setOnClickListener(view -> mGetContent.launch("image/*"));

        btnSubmit.setOnClickListener(v -> {
            startActivity(new Intent(v.getContext(), MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            Toast.makeText(v.getContext(),
                    "Terima kasih sudah melakukan pembayaran. Silahkan tunggu konfirmasi dari kami",
                    Toast.LENGTH_LONG).show();
        });
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }

        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }

        return result;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}