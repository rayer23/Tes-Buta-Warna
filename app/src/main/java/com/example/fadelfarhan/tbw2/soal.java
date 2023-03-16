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

public class soal extends Activity {
    public int benar = 0;
    private int soal = 0;
    Button b_answer1,b_answer2,b_answer3,b_answer4;
    ImageButton exit,info;
    ImageView iv_flag;
    TextView vbenar;
    List<CountryItem> list;
    Random r;
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        r = new Random() ;
        iv_flag = (ImageView) findViewById(R.id.iv_flag);

        vbenar = (TextView) findViewById(R.id.vbenar);

        b_answer1 = (Button) findViewById(R.id.b_answer1);
        b_answer2 = (Button) findViewById(R.id.b_answer2);
        b_answer3 = (Button) findViewById(R.id.b_answer3);
        b_answer4 = (Button) findViewById(R.id.b_answer4);
        exit = (ImageButton) findViewById(R.id.exit);
        info = (ImageButton) findViewById(R.id.info);

        list = new ArrayList<>();
        //Isi semua Gambar dan Hasil jawaban
        for(int i = 0;i< new Database().answers.length;i++){
            list.add(new CountryItem(new Database().answers[i],new Database().flags[i]));
        }

        //Menampilkan Skor
        soal = list.size();
        vbenar.setText("Benar : "+benar+"/"+soal);
        //Gambar Acak
        Collections.shuffle(list);
        newQuestion(turn);

        b_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b_answer1.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    jawabanbenar();
                }else{
                    jawabansalah();
                }

            }
        });

        b_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b_answer2.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    jawabanbenar();
                }else {
                    jawabansalah();
                }
            }
        });

        b_answer3.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if(b_answer3.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                                                 jawabanbenar();
                                             }else{
                                                 jawabansalah();
                                             }}
                                     }
        );

        b_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b_answer4.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    jawabanbenar();
                }else{
                    jawabansalah();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(soal.this);
                builder.setMessage("Anda yakin ingin kembali ke Menu ?")
                        .setPositiveButton("Tidak",null)
                        .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                balikmenu();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(soal.this);
                builder.setMessage("Pilihlah angka sesuai dengan yang anda lihat.")
                        .setPositiveButton("Ok",null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    public void balikmenu(){
        finish();
    }

    public void jawabanbenar(){
        Toast.makeText(soal.this, "Benar !", Toast.LENGTH_SHORT).show();
        benar++;
        vbenar.setText("Benar : "+benar+"/"+soal);
        //cek jawaban terakhir
        if(turn<list.size()){
            turn++;
            newQuestion(turn);
        }else{
            openpenjelasan();
        }
    }

    public void jawabansalah(){
        Toast.makeText(soal.this, "Salah !", Toast.LENGTH_SHORT).show();

        //cek jawaban terakhir
        if(turn<list.size()){
            turn++;
            newQuestion(turn);
        }else{
            openpenjelasan();
        }
    }

    public void openpenjelasan(){
        finish();
        Intent intent = new Intent(getApplicationContext(),penjelasan.class);
        intent.putExtra("Benar",benar);
        startActivity(intent);
    }


    private void newQuestion(int number){
        //Isi gambar ke dalam screen
        iv_flag.setImageResource(list.get(number - 1).getImage());
        //Menentukan posisi jawaban yang benar
        int correct_answer = r.nextInt(4)+1;

        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int fourthButton;

        switch(correct_answer){
            case 1:
                b_answer1.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }while(thirdButton == firstButton ||thirdButton == secondButton);

                do{
                    fourthButton = r.nextInt(list.size());
                }while(fourthButton == firstButton ||fourthButton == secondButton ||fourthButton == thirdButton );

                b_answer2.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());
                break;
            case 2:
                b_answer2.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }while(thirdButton == firstButton ||thirdButton == secondButton);

                do{
                    fourthButton = r.nextInt(list.size());
                }while(fourthButton == firstButton ||fourthButton == secondButton ||fourthButton == thirdButton );

                b_answer1.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());
                break;
            case 3:
                b_answer3.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }while(thirdButton == firstButton ||thirdButton == secondButton);

                do{
                    fourthButton = r.nextInt(list.size());
                }while(fourthButton == firstButton ||fourthButton == secondButton ||fourthButton == thirdButton );

                b_answer2.setText(list.get(secondButton).getName());
                b_answer1.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());
                break;
            case 4:
                b_answer4.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }while(thirdButton == firstButton ||thirdButton == secondButton);

                do{
                    fourthButton = r.nextInt(list.size());
                }while(fourthButton == firstButton ||fourthButton == secondButton ||fourthButton == thirdButton );

                b_answer2.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer1.setText(list.get(fourthButton).getName());
                break;
        }
    }
}