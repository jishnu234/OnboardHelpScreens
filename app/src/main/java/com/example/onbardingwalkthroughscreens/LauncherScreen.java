package com.example.onbardingwalkthroughscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LauncherScreen extends AppCompatActivity {


    SharedPreferences onBoardScreen;
    boolean isFirst;
    ImageView image_logo;
    Animation animation1,animation2;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher_screen);


        image_logo=findViewById(R.id.launcher_logo);
        slogan=findViewById(R.id.launcher_text);
        animation1= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        slogan.setAnimation(animation1);
        animation2=AnimationUtils.loadAnimation(this,R.anim.top_anim);
        image_logo.setAnimation(animation2);

        onBoardScreen=getSharedPreferences("onBoardScreen",MODE_PRIVATE);
        isFirst=onBoardScreen.getBoolean("firstTime",true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(isFirst)
                {
                    SharedPreferences.Editor editor=onBoardScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),UserDashboard.class));

                }
                finish();
            }
        },3000);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
