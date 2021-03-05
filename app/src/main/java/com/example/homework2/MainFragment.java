package com.example.homework2;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements View.OnClickListener {

    Button start;
    Button lap;
    Button reset;

    private TimerAsyncTask asyncTask;
    boolean running;
    Timer timer;
    TextView timerText;

    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        asyncTask = new TimerAsyncTask();
        timer = new Timer();

        timerText = (TextView) view.findViewById(R.id.time);
        start = (Button) view.findViewById(R.id.startButton);
        lap = (Button) view.findViewById(R.id.lapButton);
        reset = (Button) view.findViewById(R.id.resetButton);

        start.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);

        if (savedInstanceState != null) { // load
            running = savedInstanceState.getBoolean("running");
            timer.setTimeList(savedInstanceState.getStringArrayList("timeList"));
            timer.setTime(savedInstanceState.getInt("hour"),
                    savedInstanceState.getInt("minute"), savedInstanceState.getInt("second"));
            timerText.setText(savedInstanceState.getCharSequence("time"));
        } else {
            running = false;
            start.setBackgroundColor(Color.GREEN);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("hour", timer.getHr());
        outState.putInt("minute", timer.getMin());
        outState.putInt("second", timer.getSec());
        outState.putStringArrayList("timeList", timer.getTimeList());
        //outState.putString("time", (String) timerText.getText());
        outState.putCharSequence("time", timerText.getText());
        outState.putBoolean("running", running);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Cancel the task if it's running
        //if (asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
        //    asyncTask.cancel(true);
        //}
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == start.getId()) { // start/stop
            if (!running) {
                start.setText("Stop");
                start.setBackgroundColor(Color.RED);
                running = true;
                if (asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
                    asyncTask = new TimerAsyncTask();
                    asyncTask.execute();
                }
            } else {
                start.setText("Start");
                start.setBackgroundColor(Color.GREEN);
                running = false;
            }
        } else if (view.getId() == lap.getId()) { // lap
            String curr_time = String.format("%02d:%02d:%02d", timer.getHr(), timer.getMin(), timer.getSec());
            timer.addTime(curr_time);

        } else if (view.getId() == reset.getId()) { // reset
            timer.reset();
            String curr_time = String.format("%02d:%02d:%02d", timer.getHr(), timer.getMin(), timer.getSec());
            timerText.setText(curr_time);
        }
        ((MainActivity) getActivity()).addTimeList(timer.getTimeList());
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private class TimerAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            String curr_time = String.format("%02d:%02d:%02d", values[0], values[1], values[2]); // used to properly format string.
            timerText.setText(curr_time); // update UI.
        }

        @Override
        protected Void doInBackground(Integer... times) {
            while (running) { // running boolean must be updated in controller.
                timer.calc(); // calculate time.
                publishProgress(timer.getHr(), timer.getMin(), timer.getSec()); // publish progress fomr onProgressUpdate method to be triggered.
                try {
                    Thread.sleep(1000); // sleep for 1 second (1000 milliseconds).
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return null;
        }
    }
}