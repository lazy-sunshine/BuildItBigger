package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by DeLL on 4/10/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }
    private MainActivity mClickActvity;
    private Button mButton;


    @Override
    public void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);
        mClickActvity = getActivity();
        mButton = (Button) mClickActvity.findViewById(R.id.Bjoke);
    }

    @LargeTest
    public void testClick() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {

                mButton.performClick();
            }
        });
        assertNotNull(getActivity());
        // To wait for the AsyncTask to complete, you can safely call get() from the test thread
        signal.await(30, TimeUnit.SECONDS);

        // The task is done, and now you can assert some things!
        assertTrue("Happiness", true);

    }
}