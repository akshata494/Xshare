package com.example.akshata.hanchu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SelectItemsActivity extends AppCompatActivity implements AppFragment
        .OnFragmentInteractionListener,PhotoFragment.OnFragmentInteractionListener, MusicFragment
        .OnFragmentInteractionListener, FileFragment.OnFragmentInteractionListener, VideoFragment
        .OnFragmentInteractionListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabApps;
    TabItem tabPhotos;
    TabItem tabMusic;
    TabItem tabVideo;
    TabItem tabFile;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    Button appFragmentButton;

    public static final String tag = "abcdselectitem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items);

        doInitialWork();
        addListeners();
    }

    private void doInitialWork(){
        toolbar = findViewById(R.id.select_items_toolbar);
        tabLayout = findViewById(R.id.tablayout);
        tabApps = findViewById(R.id.tabApps);
        tabMusic = findViewById(R.id.tabMusic);
        tabPhotos =  findViewById(R.id.tabPhotos);
        tabFile = findViewById(R.id.tabFile);
        tabVideo = findViewById(R.id.tabVideo);
        viewPager = findViewById(R.id.sendViewPager);

        appFragmentButton = findViewById(R.id.sendapp);

        toolbar.setTitle(R.string.title_select_items);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public void addListeners(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    toolbar.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        getWindow().setStatusBarColor(ContextCompat.getColor(SelectItemsActivity.this,
                                R.color.colorPrimaryLight));
                }
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialGreen));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialGreen));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        getWindow().setStatusBarColor(ContextCompat.getColor(SelectItemsActivity.this,
                                R.color.materialGreenLight));
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialBlue));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialBlue));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        getWindow().setStatusBarColor(ContextCompat.getColor(SelectItemsActivity.this,
                                R.color.materialBlueLight));
                } else if(tab.getPosition() == 3){
                    toolbar.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialPink));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialPink));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        getWindow().setStatusBarColor(ContextCompat.getColor(SelectItemsActivity.this,
                                R.color.materialPinkLight));
                }
                else if(tab.getPosition() == 4){
                    toolbar.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialPurple));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(SelectItemsActivity.this,
                            R.color.materialPurple));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        getWindow().setStatusBarColor(ContextCompat.getColor(SelectItemsActivity.this,
                                R.color.materialPurpleLight));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onButtonPressed(View view){
        onFragmentInteraction("song");
    }

    @Override
    public void onFragmentInteraction(String whichFragment){
        switch(whichFragment){
            case "song":
                Intent i = new Intent(this.getApplicationContext(),SendActivity.class);
                startActivity(i);
                break;

            default: break;
        }
    }

}
