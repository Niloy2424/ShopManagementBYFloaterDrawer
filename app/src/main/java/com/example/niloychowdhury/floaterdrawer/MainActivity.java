package com.example.niloychowdhury.floaterdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.niloychowdhury.floaterdrawer.Fragments.CategoryFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.DailyReportFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.MounthlyReportFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.POSFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.AllReportFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.StackFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.StartFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.SubCategoryFragment;
import com.example.niloychowdhury.floaterdrawer.Fragments.WeeklyReportFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public Fragment currentFragment;
    public FragmentManager manager;
    public FragmentTransaction transaction;

    LinearLayout mylayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mylayout= (LinearLayout) findViewById(R.id.mylayout);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        currentFragment = new StartFragment();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        transaction.add(R.id.myFragment,currentFragment);
        transaction.commit();
        changeFragment(currentFragment);

    }

    private void changeFragment(Fragment currentFragment) {

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        transaction.replace(R.id.myFragment,currentFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            // Handle the camera action
            currentFragment=new CategoryFragment();
            changeFragment(currentFragment);


        } else if (id == R.id.nav_subcategory) {
          //  Toast.makeText(this, "Dukheche", Toast.LENGTH_SHORT).show();
            currentFragment=new SubCategoryFragment();
            changeFragment(currentFragment);

        } else if (id == R.id.nav_pos) {
            currentFragment=new POSFragment();
            changeFragment(currentFragment);

        } else if (id == R.id.nav_allReport) {
            currentFragment=new AllReportFragment();
            changeFragment(currentFragment);

        }else if (id == R.id.nav_allReport) {
            currentFragment=new AllReportFragment();
            changeFragment(currentFragment);

        } else if (id == R.id.nav_stack) {
            currentFragment=new StackFragment();
            changeFragment(currentFragment);

        }else if (id == R.id.nav_dailyReport) {
            currentFragment=new DailyReportFragment();
            changeFragment(currentFragment);

        }else if (id == R.id.nav_weeklyReport) {
            currentFragment=new WeeklyReportFragment();
            changeFragment(currentFragment);

        } else if (id == R.id.nav_mounthlyReport) {
            currentFragment=new MounthlyReportFragment();
            changeFragment(currentFragment);

        }else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
