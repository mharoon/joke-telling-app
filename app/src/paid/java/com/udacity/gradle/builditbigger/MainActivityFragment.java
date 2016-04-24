package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jokedisplay.JokeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment implements OnJokeResultListener {

    private ProgressBar mLoadingSpinner;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingSpinner = (ProgressBar) root.findViewById(R.id.progressBar);

        Button btnTellJoke = (Button) root.findViewById(R.id.btn_tell_Joke);
        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });

        return root;
    }

    @Override
    public void onJokeReceived(String joke) {
        mLoadingSpinner.setVisibility(View.INVISIBLE);
        //Toast.makeText(getContext(), joke, Toast.LENGTH_SHORT).show();
        launchJokeDisplayActivity(joke);

    }

    private void getJoke() {
        mLoadingSpinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    private void launchJokeDisplayActivity(String joke) {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY_EXTRA, joke);
        startActivity(intent);
    }

}
