package com.example.bakingtutorialapp;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class EspressoIdleTesting implements IdlingResource
{
    @Nullable
    private volatile ResourceCallback mCallback;

    private final AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    public String getName()
    {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow()
    {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback)
    {
        mCallback = callback;
    }

    public void setIdleState(boolean isIdleNow)
    {
        mIsIdleNow.set(isIdleNow);
        ResourceCallback resourceCallback = this.mCallback;
        if (isIdleNow && resourceCallback != null)
        {
            resourceCallback.onTransitionToIdle();
        }
    }
}
