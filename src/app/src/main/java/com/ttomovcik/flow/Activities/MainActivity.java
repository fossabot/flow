package com.ttomovcik.flow.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ttomovcik.flow.Fragments.Flow;
import com.ttomovcik.flow.Fragments.Profile;
import com.ttomovcik.flow.Fragments.TimeMachine;
import com.ttomovcik.flow.Fragments.Tools;
import com.ttomovcik.flow.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.BuildConfig;

import static com.ttomovcik.flow.R.id.fragment_container;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity
{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.menu_timeline:
                    switchFragment(new Flow());
                    return true;
                case R.id.menu_time_machine:
                    switchFragment(new TimeMachine());
                    return true;
                case R.id.menu_tools:
                    switchFragment(new Tools());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        checkFirstTimeRun();
        // TODO (5): Update onCreateView in fragments with 'return view;'
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void switchFragment(Fragment fragmentName)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragment_container, fragmentName);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void checkFirstTimeRun()
    {
        final String PREFS_NAME = "flowConfig";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOES_NOT_EXIST = -1;
        int currentVersionCode = BuildConfig.VERSION_CODE;

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = sharedPreferences.getInt(PREF_VERSION_CODE_KEY, DOES_NOT_EXIST);

        if (savedVersionCode == DOES_NOT_EXIST)
        {
            // Cleared settings or fresh install
            Log.d("checkFirstTimeRun", "We are running for the first time");
            // TODO: Open intro activity
        }
        else if (currentVersionCode > savedVersionCode)
        {
            // Application upgrade
            return;
        }
        else if (currentVersionCode == savedVersionCode)
        {
            // Normal run
            return;
        }
        sharedPreferences.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }
}
