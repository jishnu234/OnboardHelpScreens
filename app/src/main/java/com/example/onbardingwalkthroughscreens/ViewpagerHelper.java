package com.example.onbardingwalkthroughscreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewpagerHelper extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public ViewpagerHelper(Context context) {
        this.context = context;
    }

    int images[] = {R.drawable.firstim,
            R.drawable.secondim,
            R.drawable.thirdim};

    int heading[] = {R.string.slider_heading1,
            R.string.slider_heading2,
            R.string.slider_heading3};

    int desc[] = {R.string.slider_desc1,
            R.string.slider_desc2,
            R.string.slider_desc3};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


        return view==(ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

       View view=inflater.inflate(R.layout.slider_layout,container,false);

        ImageView slider_image;
        TextView slider_heading;
        TextView slider_desc;

        slider_image=view.findViewById(R.id.slider_image);
        slider_heading=view.findViewById(R.id.slider_heading_text);
        slider_desc=view.findViewById(R.id.slider_desc_text);

        slider_image.setImageResource(images[position]);
        slider_heading.setText(heading[position]);
        slider_desc.setText(desc[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
