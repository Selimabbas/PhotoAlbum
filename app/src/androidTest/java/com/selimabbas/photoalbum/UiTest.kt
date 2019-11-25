package com.selimabbas.photoalbum


import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep


class UiTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testApplicationUi() {
        val view = onView(allOf(withId(R.id.photoRecyclerView)))

        // Fake action because there isn't much to do in this app...
        view.perform(click())
        sleep(1000)
        onView(withId(R.id.swipeRefresh))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
    }

    /**
     * Set custom [constraints] for an [action] to avoid it from not working because not enough of the
     * view is shown.
     */
    private fun withCustomConstraints(
        action: ViewAction,
        constraints: Matcher<View>
    ): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController?, view: View?) {
                action.perform(uiController, view)
            }
        }
    }
}
