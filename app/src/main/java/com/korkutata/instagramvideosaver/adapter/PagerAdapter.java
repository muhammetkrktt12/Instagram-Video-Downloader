package com.korkutata.instagramvideosaver.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.korkutata.instagramvideosaver.fragment.IgtvFragment;
import com.korkutata.instagramvideosaver.fragment.PhotoFragment;
import com.korkutata.instagramvideosaver.fragment.ProfilePicFragment;
import com.korkutata.instagramvideosaver.fragment.ReelFragment;
import com.korkutata.instagramvideosaver.fragment.VideoFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabcount;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;

        //behavior  = davranış
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new PhotoFragment();
            case 1:
                return new VideoFragment();
            case 2:
                return new ReelFragment();
            case 3:
                return new IgtvFragment();
            case 4:
                return new ProfilePicFragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
