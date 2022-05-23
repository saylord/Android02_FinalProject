package com.example.login;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsActivityTest extends TestCase {

    @Rule
    public ActivityScenarioRule<NewsActivity> activityRule = new ActivityScenarioRule<NewsActivity>(NewsActivity.class);

    @Test
    public void scrollAndClick(){
        onView(withId(R.id.recycler_main))
                .perform(actionOnItemAtPosition(3, click()));
    }

    @Test
    public void testRecyclerView() throws InterruptedException {
        // RecyclerView Scroll to Position is 1 position, here the scrollto () method is defined within the viewactioin of Espresso-Core
        onView(withId(R.id.recycler_main)).perform(actionOnItemAtPosition(1, scrollTo()));
        Thread.sleep(200);
        // In order to perform the speed slow, THREAD SLEP is set
        // RecyClerView scrolls to position is 2 position, here the scrolltoposition () method is defined within RecyclerViewActions
        onView(withId(R.id.recycler_main)).perform(scrollToPosition(2));
        Thread.sleep(500);
        // RecyClerView scrolls to Positioin is the position of 3 and performs Click operations.
        onView(withId(R.id.recycler_main)).perform(scrollToPosition(3), actionOnItemAtPosition(3, click()));
    }

    @Test
    public void testOnClick() {
        onView((withId(R.id.btn_1))).check(matches(isDisplayed()));
        onView((withId(R.id.btn_1))).perform(click());
    }

    @Test
    public void messageTest(){
        onView(ViewMatchers.withId(R.id.search_view)).perform(ViewActions.typeText("Today"));
    }
}