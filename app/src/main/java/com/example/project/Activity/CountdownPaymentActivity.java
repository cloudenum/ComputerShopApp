package com.example.project.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Domain.ProfileDomain;
import com.example.project.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CountdownPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        String rekening = "5657 9090 8889";
        TextView txtName = findViewById(R.id.txtName);
        TextView txtRekening = findViewById(R.id.txtRekening);
        TextView txtCountdown = findViewById(R.id.txtCountdown);
        Button btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        txtName.setText("Angel Monica");
        txtRekening.setText(rekening);
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String name) {
                if (name != null) {
                    if (!name.equals("")) {
                        txtName.setText(name);
                    }
                }
            }
        };

        ProfileDomain profile = ProfileDomain.getInstance(this);
        profile.getName().observe(this, nameObserver);

        // This should be in Service
        new CountDownTimer(60000*60*3, 1000) {
            public void onTick(long millisUntilFinished) {
                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                txtCountdown.setText(String.format(Locale.getDefault() ,
                        "%02d : %02d : %02d", hours, minutes, seconds));
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Pembayaran gagal", Toast.LENGTH_SHORT).show();
            }
        }.start();

        btnConfirmPayment.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ConfirmPaymentActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}