package com.example.homework2;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    TextView listText;
    ArrayList<String> myList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listText = view.findViewById(R.id.textView2);

        if (savedInstanceState != null) {
            myList = savedInstanceState.getStringArrayList("timeList");
            updateDisplay(myList);
        } else {
            myList = new ArrayList<String>();
        }

        return view;
        // Display list here
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("timeList", myList);
    }

    public void updateDisplay(ArrayList<String> list) {
        myList = list;
        String total = "";
        for (int i = 0; i < list.size(); i++) {
            total += (i + 1) + ". " + list.get(i) + "\n";
        }
        listText.setText(total);
    }
}