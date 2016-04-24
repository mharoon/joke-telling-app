package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JokeTellingAppTest extends ApplicationTestCase<Application> implements OnJokeResultListener {

    CountDownLatch signal;
    String joke;

    public JokeTellingAppTest() {
        super(Application.class);
    }

    public void testJokeAsync() {
        try {
            signal = new CountDownLatch(1);
            new EndpointsAsyncTask().execute(this);
            signal.await(15, TimeUnit.SECONDS);
            assertFalse("empty", joke.isEmpty());
            assertNotNull("null", joke);

        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void onJokeReceived(String joke) {
        this.joke = joke;
        signal.countDown();
    }
}
