package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    Button  add_joke;
    private ProgressBar spinner;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
   add_joke= (Button) root.findViewById(R.id.Bjoke);
        spinner= (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        add_joke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new EndpointsAsyncTask(spinner).execute(new Pair<>(MyApplication.getAppContext(), "/n"));

            }
        });
        return root;
    }
}
