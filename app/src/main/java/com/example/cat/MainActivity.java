package com.example.cat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView coordinatesOut;
    private float x;
    private float y;
    private String sDown;
    private String sMove;
    private String sUp;

    private final float x_CAT = 500;
    private final float y_CAT = 50;
    private final float delta_CAT = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatesOut = findViewById(R.id.coordinatesOut);
        coordinatesOut.setOnTouchListener(listener);
    }

    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие: координата X = " + x + ", координата y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение: координата X = " + x + ", координата y = " + y;

                    if (x < (x_CAT + delta_CAT) && x > (x_CAT - delta_CAT) && y < (y_CAT + delta_CAT) && y > (y_CAT - delta_CAT)) {
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.successful_search, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.LEFT, (int) x_CAT - 10, (int)  y_CAT - 190);
                        LinearLayout toastContainer = (LinearLayout) toast.getView();
                        ImageView cat = new ImageView(getApplicationContext());
                        cat.setImageResource(R.drawable.cat2);
                        toastContainer.addView(cat, 1);
                        toast.show();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Отпускание: координата X = " + x + ", координата y = " + y;
                    break;
            }

            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);

            return true;
        }
    };
}