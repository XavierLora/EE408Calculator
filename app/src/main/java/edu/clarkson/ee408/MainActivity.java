package edu.clarkson.ee408;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private TextView display;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bPlus, bMinus, bMult, bDiv, bClear, bEq, bOpenParen, bCloseParen;

    private String expression = "";
    private boolean errorOccurred = false;

    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bPlus = findViewById(R.id.buttonPlus);
        bMinus = findViewById(R.id.buttonMinus);
        bMult = findViewById(R.id.buttonMult);
        bDiv = findViewById(R.id.buttonDiv);
        bClear = findViewById(R.id.buttonClear);
        bEq = findViewById(R.id.buttonEq);
        bOpenParen = findViewById(R.id.buttonOpenParen);
        bCloseParen = findViewById(R.id.buttonCloseParen);
        statusTextView = findViewById(R.id.statusTextView);
        display = findViewById(R.id.display);

        View.OnClickListener lNum = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                expression += b.getText().toString();
                display.append(b.getText().toString());
            }
        };

        View.OnClickListener lOp = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                expression += b.getText().toString();
                display.append(b.getText().toString());
            }
        };

        View.OnClickListener lOpenParen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "(";
                display.append("(");
            }
        };

        View.OnClickListener lCloseParen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += ")";
                display.append(")");
            }
        };

        View.OnClickListener lDo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int result = ParserII.DO(expression);
                    display.setText(String.valueOf(result));
                    expression = String.valueOf(result);
                } catch (Exception e) {
                    // Handle parsing errors
                    display.setText("Error");
                    statusTextView.setText(e.getMessage());
                    errorOccurred = true;
                }
            }
        };

        b0.setOnClickListener(lNum);
        b1.setOnClickListener(lNum);
        b2.setOnClickListener(lNum);
        b3.setOnClickListener(lNum);
        b4.setOnClickListener(lNum);
        b5.setOnClickListener(lNum);
        b6.setOnClickListener(lNum);
        b7.setOnClickListener(lNum);
        b8.setOnClickListener(lNum);
        b9.setOnClickListener(lNum);
        bPlus.setOnClickListener(lOp);
        bMinus.setOnClickListener(lOp);
        bMult.setOnClickListener(lOp);
        bDiv.setOnClickListener(lOp);
        bOpenParen.setOnClickListener(lOpenParen);
        bCloseParen.setOnClickListener(lCloseParen);
        bEq.setOnClickListener(lDo);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                display.setText("");
                statusTextView.setText("");
                errorOccurred = false;
            }
        });

        // starts the music
        mp = MediaPlayer.create(this, R.raw.ocean_waves);
        mp.start();
    }
}
