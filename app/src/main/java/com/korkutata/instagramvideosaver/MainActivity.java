package com.korkutata.instagramvideosaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.korkutata.instagramvideosaver.adapter.PagerAdapter;
import com.korkutata.instagramvideosaver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    PagerAdapter pagerAdapter;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);
        setSupportActionBar(binding.toolbar);




        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),5);
        binding.fragmentcontainer.setAdapter(pagerAdapter);


        binding.include.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.fragmentcontainer.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1 ||tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4) {

                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.fragmentcontainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.include));

    }
}