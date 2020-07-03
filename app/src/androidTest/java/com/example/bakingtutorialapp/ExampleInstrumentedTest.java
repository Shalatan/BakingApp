package com.example.bakingtutorialapp;

import android.content.Context;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest
{
    EspressoIdleTesting espressoIdleTesting;
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        // we have idling resources since we have data that will be downloaded to populate our recipes recycler view
        espressoIdleTesting = (EspressoIdleTesting) mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(espressoIdleTesting);
    }

    @Test
    public void useAppContext()
    {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.bakingtutorialapp", appContext.getPackageName());
    }
    @Test
    public void clickRecipe_OpenRecipeInfoActivity(){
        // when the data is downloaded, click on the first element of the recycler view
        onView(withId(R.id.mainRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
    @After
    public void unregisterIdlingResource() {
        if (espressoIdleTesting != null) {
            IdlingRegistry.getInstance().unregister(espressoIdleTesting);
        }
    }


}
