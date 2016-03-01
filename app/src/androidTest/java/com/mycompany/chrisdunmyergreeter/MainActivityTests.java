package com.mycompany.chrisdunmyergreeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testGreet() {
        MainActivity activity = this.passStringAndPressGreet(getActivity());

        // validate name shows up in TextView
        TextView greetMessage =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }

    public void testReverseButtonIsDisabledByDefault() {
        MainActivity activity = getActivity();
        Button reverseButton =
                (Button) activity.findViewById(R.id.reverse_button);
        assertEquals(false, reverseButton.isEnabled());
    }

    public void testReverseButtonIsEnabledWhenGreetButtonIsClicked() {
        MainActivity activity = getActivity();
        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
        Button reverseButton =
                (Button) activity.findViewById(R.id.reverse_button);
        assertEquals(true, reverseButton.isEnabled());
    }

    public void testReverseAfterGreetButtonPressed() {
        MainActivity activity = this.passStringAndPressGreet(getActivity());

        //press Reverse
        Button reverseButton = (Button) activity.findViewById(R.id.reverse_button);
        TouchUtils.clickView(this, reverseButton);

        // validate reverse name shows up in TextView
        TextView greetMessage =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = greetMessage.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);
    }

    private MainActivity passStringAndPressGreet(MainActivity activity) {
        // type name in text input
        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        // press Greet
        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
        return activity;
    }
}
