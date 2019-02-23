package com.ttomovcik.flow;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_timeline:
                    Fragment flowFragment = new Flow();
                    FragmentTransaction fragmentTransaction_flow = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_flow.replace(R.id.fragment_container, flowFragment);
                    fragmentTransaction_flow.addToBackStack(null);
                    fragmentTransaction_flow.commit();
                    return true;
                case R.id.menu_time_machine:
                    Fragment timeMachineFragment = new TimeMachine();
                    FragmentTransaction fragmentTransaction_timeMachine = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_timeMachine.replace(R.id.fragment_container, timeMachineFragment);
                    fragmentTransaction_timeMachine.addToBackStack(null);
                    fragmentTransaction_timeMachine.commit();
                    return true;
                case R.id.menu_tools:
                    Fragment toolsFragment = new Tools();
                    FragmentTransaction fragmentTransaction_tools = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_tools.replace(R.id.fragment_container, toolsFragment);
                    fragmentTransaction_tools.addToBackStack(null);
                    fragmentTransaction_tools.commit();
                    return true;
                case R.id.menu_profile:
                    Fragment profileFragment = new Profile();
                    FragmentTransaction fragmentTransaction_profile = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_profile.replace(R.id.fragment_container, profileFragment);
                    fragmentTransaction_profile.addToBackStack(null);
                    fragmentTransaction_profile.commit();
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
        Fragment flowFragment = new Flow();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, flowFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
