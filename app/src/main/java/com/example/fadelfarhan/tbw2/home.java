package com.example.fadelfarhan.tbw2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends Activity {
    private Button mulai,tentang,keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mulai = (Button) findViewById(R.id.mulai);
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensoal();
            }
        });

        tentang = (Button) findViewById(R.id.tentang);
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opententang();
            }
        });

        keluar = (Button) findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keluar();
            }
        });
    }

    public void opensoal(){
        Intent intent = new Intent(this, soal.class);
        startActivity(intent);
    }

    public void opententang(){
        Intent intent2 = new Intent(this, tentang.class);
        startActivity(intent2);
    }

    public void keluar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
        builder.setMessage("Anda yakin ingin keluar ?")
                .setPositiveButton("Tidak",null)
                .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

