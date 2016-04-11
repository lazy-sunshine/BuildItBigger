package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    InterstitialAd mInterstitialAd;
    AdView mAdView;
    Button add_joke;
    private ProgressBar spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mAdView = (AdView) root.findViewById(R.id.adView);
        spinner= (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-5591610367876233/1965460907");
   add_joke= (Button) root.findViewById(R.id.Bjoke);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginPlaying();
            }
        });
        requestNewInterstitial();
        add_joke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();

                } else {
                    spinner.setVisibility(View.VISIBLE);
                    beginPlaying();
                }
            }
        });
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("8297C25D52ED7EFEAEE78FA6D5873786")
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void beginPlaying() {
        new EndpointsAsyncTask(spinner).execute(new Pair<>(MyApplication.getAppContext(), "/n"));
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("8297C25D52ED7EFEAEE78FA6D5873786")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
}
