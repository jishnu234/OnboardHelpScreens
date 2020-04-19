package com.example.onbardingwalkthroughscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.onbardingwalkthroughscreens.R.*;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewpagerHelper helper;
    LinearLayout dots_layout;
    TextView[] dots;//This array used for creating dots
    Button get_started,skip_btn,next_btn;
    Animation animation;
    int mCurrentPosition;
    SharedPreferences onBoardScreen;
    boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(layout.activity_main);

        viewPager=findViewById(id.viewpager);
        dots_layout=findViewById(id.slider_dot_layout);
        get_started=findViewById(id.slider_start_btn);
        skip_btn=findViewById(id.skip_btn);
        next_btn=findViewById(id.nxt_btn);

        onBoardScreen=getSharedPreferences("onBoardScreen",MODE_PRIVATE);
        isFirstTime=onBoardScreen.getBoolean("firsTime",true);

        helper=new ViewpagerHelper(this);

        viewPager.setAdapter(helper);

        get_started.setVisibility(View.GONE);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private void addDots(int position)
    {
        dots=new TextView[3];
        dots_layout.removeAllViews();

        for(int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dots_layout.addView(dots[i]);
        }

        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(color.dots_color));
        }
    }

    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
            mCurrentPosition=position;

            if(position==0)
            {
                get_started.setVisibility(View.GONE);
            }
            else if (position==1)
            {
                get_started.setVisibility(View.GONE);
            }
            else
            {
                animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_anim);
                get_started.startAnimation(animation);
                get_started.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void next(View view) {

        viewPager.setCurrentItem(mCurrentPosition+1);

    }

    public void skip(View view) {

        startActivity(new Intent(getApplicationContext(),UserDashboard.class));
        finish();

    }

    public void get_started(View view) {

        startActivity(new Intent(getApplicationContext(),UserDashboard.class));
        finish();

    }
}
