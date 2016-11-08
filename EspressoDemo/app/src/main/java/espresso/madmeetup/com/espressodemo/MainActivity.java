package espresso.madmeetup.com.espressodemo;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        final Button validateButton = (Button) findViewById(R.id.validateButton);
        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        final Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        final View container = findViewById(R.id.container);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = usernameEditText.getText().toString();

                if (text.equalsIgnoreCase("vipul")) {
                    resultTextView.setText(R.string.success_message);
                    resultTextView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                } else {
                    resultTextView.setText(R.string.failure_message);
                    resultTextView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                }
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.background_colors));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(spinnerAdapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        container.setBackgroundColor(getColorFromRes(R.color.color1));
                        break;
                    case 1:
                        container.setBackgroundColor(getColorFromRes(R.color.color2));
                        break;
                    case 2:
                        container.setBackgroundColor(getColorFromRes(R.color.color3));
                        break;
                    default:
                        container.setBackgroundColor(getColorFromRes(R.color.color4));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });
    }

    private int getColorFromRes(@ColorRes int colorRes) {
        return ContextCompat.getColor(MainActivity.this, colorRes);
    }
}
