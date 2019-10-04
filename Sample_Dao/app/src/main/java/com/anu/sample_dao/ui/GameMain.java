package com.anu.sample_dao.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.anu.sample_dao.R;

public class GameMain extends AppCompatActivity {


    ImageButton btn;
    boolean btnClicked = false;

    ImageButton btn1, btn2, btn3;
    boolean btnClicked1, btnClicked2, btnClicked3 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);


        // 2
        addListenerOnButton();



        // 3 - reduce image sample size
        // provides several decoding methods
        BitmapFactory.Options options = new BitmapFactory.Options();

        // decoding avoids memory allocation
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.id.main_imgView, options);

        // read the dimensions and type of the image data prior to construction (and memory allocation) of the bitmap
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
    }


    // click button to change color
    public void addListenerOnButton() {

        // button 1

        btn1 = findViewById(R.id.imageButton1);
        btn1.setBackground(null);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btnClicked1 = !btnClicked1;
                if (!btnClicked1) {
                    btn1.setImageResource(R.drawable.btn_clicked);
                } else{
                    btn1.setImageResource(R.drawable.btn_normal);

                    startActivity(new Intent(GameMain.this, GameUserActivity.class));
                }
            }
        });


        btn2 = findViewById(R.id.imageButton2);
        btn2.setBackground(null);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btnClicked2 = !btnClicked2;
                if (!btnClicked2) {
                    btn2.setImageResource(R.drawable.btn_clicked);
                } else{
                    btn2.setImageResource(R.drawable.btn_normal);

                    startActivity(new Intent(GameMain.this, GamePlayActivity.class));
                }
            }
        });


        btn3 = findViewById(R.id.imageButton3);
        btn3.setBackground(null);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btnClicked3 = !btnClicked3;
                if (!btnClicked3) {
                    btn3.setImageResource(R.drawable.btn_clicked);
                } else{
                    btn3.setImageResource(R.drawable.btn_normal);

                    startActivity(new Intent(GameMain.this, GameAboutActivity.class));
                }
            }
        });


        // some error happens
//        int[] btnIds = new int[] {R.id.imageButton1,R.id.imageButton2, R.id.imageButton3};
//
//        for (int i=0; i<btnIds.length; i++) {
//
//            btn = findViewById(btnIds[i]);
//            btn.setBackground(null);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View arg0) {
//                    btnClicked = !btnClicked;
//                    System.out.println("Gooood");
//                    if (!btnClicked) {
//                        btn.setImageResource(R.drawable.btn_clicked);
//                    } else{
//                        btn.setImageResource(R.drawable.btn_normal);
//                    }
//
//                }
//            });
//
//            btn.setOnHoverListener(new View.OnHoverListener() {
//                @Override
//                public boolean onHover(View view, MotionEvent motionEvent) {
//                    btn.setImageResource(R.drawable.btn_hover);
//                    return true;
//                }
//            });
//
//            btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    System.out.println("Good");
//                }
//            });
//
//        }


    }



}
