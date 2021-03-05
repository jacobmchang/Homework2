package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

// implements ListFragment.OnFragmentInteractionListener
public class MainActivity extends AppCompatActivity {

    //MyAsyncTask myAsyncTask;
    FragmentManager fragmentManager;

    ArrayList<String> mainTimeList;

    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTimeList = new ArrayList<String>();

        mainFragment = MainFragment.newInstance();

        //fragmentManager = getSupportFragmentManager(); // Fragment Manager
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //fragmentTransaction.add(R.id.fragment, mainFragment);
        //fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();
    }

    public void addTimeList(ArrayList<String> al) {
        this.mainTimeList = al;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment7);
            listFragment.updateDisplay(this.mainTimeList);
        }
    }

    // Saves the activity state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // Restores the activity state
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Changes the view of the main display
     * Switches from main page to list view
     * @param view
     */
    public void changeFragment(View view) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Bundle bundle = new Bundle();
            //System.out.println(mainTimeList.size());
            //bundle.putStringArrayList("Timer", mainTimeList);
            //bundle.putInt("num", 5);
            //listFragment = new ListFragment();
            //listFragment.setArguments(bundle);
            Intent intent = new Intent(this, ListActivity.class);
            intent.putExtra("Timer", mainTimeList);
            startActivity(intent);
        }
        /**
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TextView textView = (TextView) findViewById(R.id.button);
        if (mainView) {
            fragmentTransaction.replace(R.id.fragment, listFragment);
            //fragmentTransaction.addToBackStack("mainFragment");
            mainView = false;
            textView.setText("Timer");
        } else {
            fragmentTransaction.replace(R.id.fragment, mainFragment);
            //fragmentTransaction.addToBackStack("listFragment");
            mainView = true;
            textView.setText("List");
        }
        fragmentTransaction.commit();
         */
    }
}