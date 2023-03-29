package com.example.custom_camera.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import com.example.custom_camera.R;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class FragmentScreenTwo extends Fragment {
    public FragmentScreenTwo() {
        // Required empty public constructor
    }

    boolean showTimer = false;
    ProgressBar barTimer;
    TextView textTimer;
    CountDownTimer countDownTimer;
    private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_screen_two, container, false);
        Button gotoCamera = rootView.findViewById(R.id.gotoCameraBtn);
        barTimer = rootView.findViewById(R.id.progressBarCircle);
        textTimer = rootView.findViewById(R.id.textViewTime);
        barTimer.setVisibility(View.GONE);
        textTimer.setVisibility(View.GONE);
        gotoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimer = true;
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.cameraActivityTwo);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        if (showTimer) {
            barTimer.setVisibility(View.VISIBLE);
            textTimer.setVisibility(View.VISIBLE);
            startTimer(5);
            showTimer = false;
        }
        super.onResume();
    }

    private void startTimer(final int minuti) {

        int timeCounter = minuti * 60 *1000;
        barTimer.setMax(timeCounter);

        countDownTimer = new CountDownTimer(60 * minuti * 1000, 5000) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                barTimer.setProgress((int)seconds);
                textTimer.setText(String.format("%02d", seconds/60) + ":" + String.format("%02d", seconds%60));
                // format the textview to show the easily readable format

            }
            @Override
            public void onFinish() {
                navController.navigate(R.id.fragmentScreenThree);
            }
        }.start();

    }
}