package demo.com.mockitodemos;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RobolectricTests {

    @Test(expected = IllegalArgumentException.class)
    public void testAccount() throws Exception {
        Database mockDatabase = Mockito.mock(Database.class);
        Mockito.when(mockDatabase.isAccountExist(anyString())).thenReturn(true);
        Validator validator = new Validator(mockDatabase);
        validator.testAccount("Sample Account");
    }

    @Test
    public void testAccountCount() {
        Database mockDatabase = Mockito.mock(Database.class);
        Validator validator = new Validator(mockDatabase);
        validator.testAccount("Sample Account 1");
        validator.testAccount("Sample Account 2");
        validator.testAccount("Sample Account 3");

        assertEquals(3, validator.getAccounts().size());
    }

    @Test
    public void testActivityTitle() {

        // Arrange
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        assertEquals(activity.getTitle(), RuntimeEnvironment.application.getString(R.string.app_name));

        Button clickMe = (Button) activity.findViewById(R.id.clickMe);
        TextView textView = (TextView) activity.findViewById(R.id.textView);

        assertTrue(textView.getVisibility() == View.GONE);

        // Act
        clickMe.performClick();

        assertTrue(textView.getVisibility() == View.GONE);

        // Verify
        assertEquals(RuntimeEnvironment.application.getString(R.string.app_name), textView.getText().toString());

        // app/build/reports/tests/debug/index.html
    }

    @Test
    public void testNavigationFlow() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Button clickMe = (Button) activity.findViewById(R.id.clickMe);
        clickMe.performClick();

        Intent expectedIntent = new Intent(activity, SecondActivity.class);

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);

        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}