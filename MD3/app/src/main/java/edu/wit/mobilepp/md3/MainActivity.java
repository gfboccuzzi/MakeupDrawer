package edu.wit.mobilepp.md3;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"),
                CollectionFragment_Makeup.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"),
                CollectionFragment_Skincare.class, null);

        //loadFragment(new CollectionFragment_Makeup());
    }
    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){

            getSupportFragmentManager().beginTransaction().replace(R.id.realtabcontent, fragment).commit();

            return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        switch (item.getItemId()){
            case R.id.navigation_collection:
            fragment = new CollectionFragment_Makeup();
                mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"),
                        CollectionFragment_Makeup.class, null);
                mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"),
                        CollectionFragment_Skincare.class, null);
            break;

            case R.id.navigation_expiring:
            fragment = new ExpiringFragment_Makeup();
            break;

            case R.id.navigation_wishlist:
            fragment = new WishListFragment_Makeup();
            break;

        }
        return loadFragment(fragment);
    }
}
