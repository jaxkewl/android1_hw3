package com.marshong.martinhonghw3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    // this contains the main text
    TextView mMainTextView;

    // our shared preferences
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_hardware_phone);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // using the preference manager, get the default values
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        //get the user selected input, default to 18sp, if nothing was entered
        String textSize = mPrefs.getString("textSize", "18");

        mMainTextView = (TextView) findViewById(R.id.textViewMainText);

        // call this routine to the text size when this activity is created.
        setTextSize(textSize);
    }


    // this routine changes the text size of the main text
    private void setTextSize(String textSize) {
        //Toast.makeText(this, "Text Size: " + textSize, Toast.LENGTH_SHORT).show();
        float textSizeFloat = Float.parseFloat(textSize);
        mMainTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeFloat);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            // start this activity
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onPostResume();
        Log.d(TAG, "onResume");

        // if this activity was resumed, then call to set the text size.
        // i.e. if user went to the settings screen, but did not return to the main
        // screen using the "Up" icon in the settings menu
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String textSize = mPrefs.getString("textSize", "18");
        setTextSize(textSize);
    }
}
