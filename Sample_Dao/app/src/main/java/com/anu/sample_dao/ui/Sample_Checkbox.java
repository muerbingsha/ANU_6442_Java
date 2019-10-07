package com.anu.sample_dao.ui;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anu.sample_dao.R;

public class Sample_Checkbox extends AppCompatActivity {

    private CheckBox chkIos, chkAndroid, chkWindows;
    private Button chkDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_checkbox);

        addListenerOnChkIos();
        addListenerOnButton();
    }


    private void addListenerOnChkIos() {
        chkIos = (CheckBox) findViewById(R.id.chkIos);

        chkIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkIos.isChecked()) {

                    // show float box
                    Toast.makeText(Sample_Checkbox.this,
                            "Bro, try Android :)",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addListenerOnButton() {

        chkIos = (CheckBox) findViewById(R.id.chkIos);
        chkAndroid = (CheckBox) findViewById(R.id.chkAndroid);
        chkWindows = (CheckBox) findViewById(R.id.chkWindows);
        chkDisplay = (Button) findViewById(R.id.chkDisplay);


        chkDisplay.setOnClickListener(new View.OnClickListener() {

            //Run when button is clicked
            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("IPhone check : ").append(chkIos.isChecked());
                result.append("\nAndroid check : ").append(chkAndroid.isChecked());
                result.append("\nWindows Mobile check :").append(chkWindows.isChecked());

                Toast.makeText(Sample_Checkbox.this, result.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }

}
