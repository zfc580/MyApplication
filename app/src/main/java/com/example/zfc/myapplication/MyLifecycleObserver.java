package com.example.zfc.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class MyLifecycleObserver implements LifecycleObserver {

    public static final String TAG = MyLifecycleObserver.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        Log.i(TAG, "onAny: event = " + event.name() + ", owner = " + owner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onInit() {
        Log.i(TAG, "onInit");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onRunning() {
        Log.i(TAG, "onRunning");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPausing() {
        Log.i(TAG, "onPausing");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onRelease() {
        Log.i(TAG, "onRelease");
    }

}