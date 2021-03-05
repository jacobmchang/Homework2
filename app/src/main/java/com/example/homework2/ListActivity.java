package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> timerList;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            timerList = new ArrayList<String>();
        } else {
            timerList = extras.getStringArrayList("Timer");
        }
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        listFragment.updateDisplay(timerList);
    }

    public void finishFunc(View view) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            finish();
            //return;
        }
    }
}