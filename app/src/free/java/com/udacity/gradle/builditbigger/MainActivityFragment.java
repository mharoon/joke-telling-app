package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jokedisplay.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment implements OnJokeResultListener {

    private ProgressBar mLoadingSpinner;
    private InterstitialAd mInterstitialAd;
    private String mJoke;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);

        mLoadingSpinner = (ProgressBar) root.findViewById(R.id.progressBar);

        Button btnTellJoke = (Button) root.findViewById(R.id.btn_tell_Joke);
        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
                loadInterstitialAd();
            }
        });


        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                launchJokeDisplayActivity(mJoke);
            }
        });
        //loadInterstitialAd();

        return  root;
    }

    @Override
    public void onJokeReceived(String joke) {
        mJoke = joke;
        mLoadingSpinner.setVisibility(View.INVISIBLE);
        //Toast.makeText(getContext(), joke, Toast.LENGTH_SHORT).show();

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            launchJokeDisplayActivity(joke);
        }
    }

    private void getJoke(){
        mLoadingSpinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    private void launchJokeDisplayActivity(String joke){
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY_EXTRA, joke);
        startActivity(intent);
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

}
