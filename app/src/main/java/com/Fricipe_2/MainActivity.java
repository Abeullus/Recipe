package com.Fricipe_2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.media.MediaPlayer;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MediaPlayer player;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified Coding";
    private static final String CHANNEL_DESC = "Simplified Coding Notifications";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(this, R.raw.musik);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        findViewById(R.id.btn_notify_on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });




        drawer.addDrawerListener(toggle);

        toggle.syncState();

    }


    private void displayNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.fricipe_logo)
                        .setContentTitle("Hey! Bringe neuen Schwung in deine Radtour!")
                        .setContentText("Lass dir jetzt eine individuelle Route von Velotour erzeugen.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(this);

        mNotificationMgr.notify(1, mBuilder.build());

    }


    @Override
    protected void onStart() {
        super.onStart();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        EmptyFragment Fragment = new EmptyFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.root_layout, Fragment, Fragment.getTag()).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentManager manager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            Recipes recipes = new Recipes();
            manager.beginTransaction().replace(R.id.root_layout, recipes, recipes.getTag()).addToBackStack(null).commit();

        } else if (id == R.id.favoriteRecipe) {

            FavoriteRecipe favoriteRecipe = new FavoriteRecipe();
            manager.beginTransaction().replace(R.id.root_layout, favoriteRecipe, favoriteRecipe.getTag()).addToBackStack(null).commit();

        } else if (id == R.id.nav_category)
        {
            CategoryFragment categoryFragment = new CategoryFragment();
            manager.beginTransaction().replace(R.id.root_layout, categoryFragment, categoryFragment.getTag()).addToBackStack(null).commit();

        } else if (id == R.id.nav_information)
        {
            InformationFragment informationFragment = new InformationFragment();
            manager.beginTransaction().replace(R.id.root_layout, informationFragment, informationFragment.getTag()).addToBackStack(null).commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.musik);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }


    public void stop(View v) {
        stopPlayer();
   }
    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
       }
    }
    @Override
    protected void onStop() {
        super.onStop();
       stopPlayer();
    }
    /*
        Nicht löschen, gehört irgendwie zum OnClick-Interface!
         */
    public void onClick(View v) {
    }


}


