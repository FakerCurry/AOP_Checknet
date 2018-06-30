package com.sjw.mychecknet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //面向切面检测网络
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void checknet(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        check();
    }

    @CheckNet
    private void check() {
        Toast.makeText(this, "有网络", Toast.LENGTH_SHORT).show();
    }
}
