package com.ttomovcik.flow;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.ttomovcik.flow.R.id.fragment_container;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_timeline:
                    switchFragment(new Flow());
                    return true;
                case R.id.menu_time_machine:
                    switchFragment(new TimeMachine());
                    return true;
                case R.id.menu_tools:
                    switchFragment(new Tools());
                    return true;
                case R.id.menu_profile:
                    switchFragment(new Profile());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Load the Flow fragment on startup
        Log.d("onCreate","Initial fragment switch should happen soon");
        switchFragment(new Flow());
    }

    private void switchFragment (Fragment fragmentName) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragment_container, fragmentName);
        fragmentTransaction.addToBackStack(null);
        Log.d("switchFragment","Switching to: "+ fragmentName);
        fragmentTransaction.commit();
    }
}
