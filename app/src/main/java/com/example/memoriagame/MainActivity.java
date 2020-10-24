package com.example.memoriagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton img1, img2, img3,img4 , tem1 , tem2;

    private String[] fotos = {"android","apple","apple","android"};
    private List<String> fotosA = Arrays.asList(fotos);
    public List<Integer> lista;
    public TextView tv;
    private int image_click , m1, m2, score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image_click = 0;
        img1 = (ImageButton)findViewById(R.id.id_image1);
        img2 = (ImageButton)findViewById(R.id.id_image2);
        img3 = (ImageButton)findViewById(R.id.id_image3);
        img4 = (ImageButton)findViewById(R.id.id_image4);
        tv = (TextView)findViewById(R.id.textView);
        Collections.shuffle(fotosA);

        tem1 = null;
        tem2 = null;

        lista = new ArrayList<Integer>();

        m1 = -1;
        m2 = -1;

        score = 0;

    }


    public void clik(View v){
        if(image_click == 0){
            switch (v.getId()) {
                case R.id.id_image1:
                    tem1 = img1;
                    m1 = 0;
                    break;
                case R.id.id_image2:
                    tem1 = img2;
                    m1 = 1;
                    break;
                case R.id.id_image3:
                    tem1 = img3;
                    m1 = 2;
                    break;
                case R.id.id_image4:
                    tem1 = img4;
                    m1 = 3;
                    break;
            }

            if(lista.contains(m1)){
                tem1 = null;
                image_click = 0;
                m1 = -1;
                return;
            }
            image_click = 1;

        }else if(image_click == 1){
            switch (v.getId()) {
                case R.id.id_image1:
                    tem2 = img1;
                    m2 = 0;
                    break;
                case R.id.id_image2:
                    tem2 = img2;
                    m2 = 1;
                    break;
                case R.id.id_image3:
                    tem2 = img3;
                    m2 = 2;
                    break;
                case R.id.id_image4:
                    tem2 = img4;
                    m2 = 3;
                    break;
            }

            if(lista.contains(m2) || m1 == m2){
                tem2 = null;
                image_click = 1;
                m2 = -1;
                return;
            }

            //Toast.makeText(getApplicationContext(), "Te fue Mal!!!" + m1 + "  " + m2, Toast.LENGTH_LONG).show();


            String uri = "@drawable/"+ fotos[m1];
            String uri2 = "@drawable/"+ fotos[m2];

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());

            tem1.setImageResource(imageResource);
            tem2.setImageResource(imageResource2);

            if(fotos[m1] == fotos[m2]) {
                Toast.makeText(getApplicationContext(), "Bien Hecho !!!", Toast.LENGTH_LONG).show();
                lista.add(m1);
                lista.add(m2);
                score = score + 50;
                Integer lop = score;
                tv.setText(lop.toString());
            }else{
                Toast.makeText(getApplicationContext(), "Te fue Mal!!!", Toast.LENGTH_LONG).show();
            }


            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                int pensando = getResources().getIdentifier("@drawable/pensando2", null, getPackageName());
                ImageButton rr1 = tem1;
                ImageButton rr2 = tem2;
                Boolean s = fotos[m1] != fotos[m2];
                public void run() {
                    //Toast.makeText(getApplicationContext(), "TTTTT" + s, Toast.LENGTH_LONG).show();
                    if(rr1 != null && rr2 != null && s){
                        //Toast.makeText(getApplicationContext(), "ES NO NULtO" + tem1, Toast.LENGTH_LONG).show();
                        rr1.setImageResource(pensando);
                        rr2.setImageResource(pensando);
                    }
                }
            };
            handler.postDelayed(runnable, 1000); //for initial delay..

            tem1 = null;
            image_click = 0;
            m1 = -1;
            tem2 = null;
            m2 = -1;

            if(score == 100){
                Intent siguiente = new Intent(this,MainActivity2.class);
                startActivity(siguiente);
            }

        }else if(image_click == 2){
            Toast.makeText(getApplicationContext(), "ALGO FALLO", Toast.LENGTH_LONG).show();
        }


    }





}