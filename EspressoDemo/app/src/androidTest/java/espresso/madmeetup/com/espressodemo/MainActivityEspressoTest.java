package espresso.madmeetup.com.espressodemo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    private final Context context = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testWrongCredentialsValidation() {
        Espresso.onView(withId(R.id.usernameEditText)).perform(typeText("Akshay"));
        Espresso.onView(withId(R.id.validateButton)).perform(click());
        Espresso.onView(withId(R.id.resultTextView)).check(matches(withText(R.string.failure_message)));

        Espresso.onView(withId(R.id.resultTextView)).check(matches(new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {
                // do nothing
            }

            @Override
            protected boolean matchesSafely(TextView textView) {
                int color = textView.getTextColors().getDefaultColor();
                int colorToMatch = ContextCompat.getColor(textView.getContext(), R.color.colorAccent);
                return color == colorToMatch;
            }
        }));
    }

    @Test
    public void testValidCredentialsValidation() {
        Espresso.onView(withId(R.id.usernameEditText)).perform(typeText("vipul"));
        Espresso.onView(withId(R.id.validateButton)).perform(click());
        Espresso.onView(withId(R.id.resultTextView)).check(matches(withText(R.string.success_message)));
        Espresso.onView(withId(R.id.resultTextView)).check(matches(new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {
                // do nothing
            }

            @Override
            protected boolean matchesSafely(TextView textView) {
                int color = textView.getTextColors().getDefaultColor();
                int colorToMatch = ContextCompat.getColor(textView.getContext(), R.color.colorPrimary);
                return color == colorToMatch;
            }
        }));
    }

    @Test
    public void testSpinnerBackgroundColor() {
        Espresso.onView(withId(R.id.colorSpinner)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is("Color 1"))).perform(click());
        Espresso.onView(withId(R.id.container)).check(matches(new BoundedMatcher<View, ViewGroup>(ViewGroup.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(ViewGroup viewGroup) {
                int color = ((ColorDrawable) viewGroup.getBackground()).getColor();
                return color == ContextCompat.getColor(viewGroup.getContext(), R.color.color1);
            }
        }));
    }
}
