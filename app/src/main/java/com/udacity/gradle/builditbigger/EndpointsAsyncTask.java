package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.MyJoke;
import com.example.dell.jokedisplay.MyJokeDisplay;
import com.example.dell.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * Created by DeLL on 4/8/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context= MyApplication.getAppContext();
 ProgressBar spinner;
    public EndpointsAsyncTask(ProgressBar spinner) {
        this.spinner=spinner;
    }

    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
       if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }


                    });
            // end options for devappserver

            myApiService = builder.build();
        }

     //   context = params[0].first;
      //  String name = params[0].second;

        MyJoke jokes=new MyJoke();
        String name=jokes.getString();
       Log.v("Name",name);
        try {

            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    CountDownLatch signal=new CountDownLatch(1);
    @Override
    protected void onPostExecute(String result) {
        String z=new MyJoke().getString();
        Intent i=new Intent(context, MyJokeDisplay.class);
        i.putExtra("joke",z);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       context.startActivity(i);
        spinner.setVisibility(View.GONE);
               signal.countDown();
        // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }


}
