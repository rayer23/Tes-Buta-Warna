package com.example.fadelfarhan.tbw2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.lang.*;

public class penjelasan extends Activity {
    private int soal;
    ImageButton selanjutnya,kembali,sebelumnya,cobalagi;
    TextView isi,isi2,isi3,vbenar,hasil;
    ImageView iv_flag;
    List<CountryItem> list,list2,list3;
    Random r;
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjelasan);
        Toast.makeText(penjelasan.this, "Anda Telah menyelesaikan soal", Toast.LENGTH_SHORT).show();
        r = new Random() ;
        iv_flag = (ImageView) findViewById(R.id.iv_flag);

        isi = (TextView)findViewById(R.id.isi);
        isi2 = (TextView)findViewById(R.id.isi2);
        isi3 = (TextView)findViewById(R.id.isi3);
        vbenar = (TextView) findViewById(R.id.vbenar);
        hasil = (TextView)findViewById(R.id.hasil);

        selanjutnya= (ImageButton) findViewById(R.id.selanjutnya);
        sebelumnya= (ImageButton) findViewById(R.id.sebelumnya);
        cobalagi = (ImageButton) findViewById(R.id.cobalagi);
        kembali = (ImageButton) findViewById(R.id.kembali);

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        //Isi semua Gambar dan Hasil jawaban
        for(int i = 0;i< new Database().answers.length;i++){
            list.add(new CountryItem(new Database().penjelasan[i],new Database().flags[i]));
            list2.add(new CountryItem(new Database().penjelasan2[i],new Database().flags[i]));
            list3.add(new CountryItem(new Database().penjelasan3[i],new Database().flags[i]));
        }

        //Menampilkan Skor
        soal = list.size();
        int benar = getIntent().getIntExtra("Benar",0);
        vbenar.setText("Jumlah Benar : "+benar+"/"+soal);

        if (benar < 3){
            hasil.setText("Anda buta warna total");
        }
        else if(benar < 8){
            hasil.setText("Anda buta warna sebagian");
        }
        else{
            hasil.setText("Mata anda normal");
        }
        newQuestion(turn);

        selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn<list.size()){
                    turn++;
                    newQuestion(turn);
                }else{
                    Toast.makeText(penjelasan.this, "Ini Penjelasan Terakhir", Toast.LENGTH_SHORT).show();
                }
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(penjelasan.this);
                builder.setMessage("Anda yakin ingin kembali ke Menu ?")
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
        });

        sebelumnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn == 1){
                    Toast.makeText(penjelasan.this, "Ini Penjelasan Pertama", Toast.LENGTH_SHORT).show();
                }else{
                    turn--;
                    newQuestion(turn);
                }
            }
        });

        cobalagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(penjelasan.this);
                builder.setMessage("Anda ingin mengulang kembali ?")
                        .setPositiveButton("Tidak",null)
                        .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cobalagi();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void cobalagi(){
        finish();
        Intent intent2 = new Intent(this, soal.class);
        startActivity(intent2);
    }
    private void newQuestion(int number) {
        //Isi gambar dan penjelasan ke dalam screen
        int satu = number - 1;
        iv_flag.setImageResource(list.get(satu).getImage());
        isi.setText(list.get(satu).getName());
        isi2.setText(list2.get(satu).getName());
        isi3.setText(list3.get(satu).getName());
    }
}
