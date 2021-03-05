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
    ListView lv;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            displayList = bundle.getStringArrayList("Timer");
        }
        if (displayList != null) {
            for (int i = 0; i < displayList.size(); i++) {
                System.out.println(displayList.get(i));
            }
        }
         */

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        lv = view.findViewById(R.id.lapButton);
        listText = view.findViewById(R.id.textView2);
        listText.setText("");

        //if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        //    updateDisplay(((MainActivity) getActivity()).getTimeList());
        //}

        return view;
        // Display list here
    }

    public void updateDisplay(ArrayList<String> list) {
        String total = "";
        for (int i = 0; i < list.size(); i++) {
            total += (i + 1) + ". " + list.get(i) + "\n";
        }
        listText.setText(total);
    }
}