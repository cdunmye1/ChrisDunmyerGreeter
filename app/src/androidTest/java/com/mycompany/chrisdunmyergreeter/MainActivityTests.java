package com.mycompany.chrisdunmyergreeter;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Chris on 3/1/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
}
