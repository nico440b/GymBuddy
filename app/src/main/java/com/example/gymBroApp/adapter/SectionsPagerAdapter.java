package com.example.gymBroApp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gymBroApp.view.fragment.FindViewFragment;
import com.example.gymBroApp.view.fragment.MatchesViewFragment;
import com.example.gymBroApp.R;
import com.example.gymBroApp.view.fragment.ProfileViewFragment;
import com.example.gymBroApp.view.fragment.WeatherViewFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes

    private int [] imageId = {R.drawable.profile_icon_2,R.drawable.search_icon,R.drawable.msg_icon,R.drawable.cloudy_icon};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch(position){
            case 0:
                frag = new ProfileViewFragment();
                break;
            case 1:
                frag = new FindViewFragment();
                break;

            case 2:
                frag = new MatchesViewFragment();
                break;

            case 3:
                frag = new WeatherViewFragment();
                break;
        }
        return frag;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = mContext.getDrawable(imageId[position]);
        image.setBounds(0, 0, 80, 80);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    @Override
    public int getCount() {

        return 4;
    }
}