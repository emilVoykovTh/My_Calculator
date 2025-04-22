package com.example.mycalculator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RectangleActivity extends AppCompatActivity {

    private FrameLayout drawArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);

        drawArea = findViewById(R.id.draw_area);

        // Вземаме параметрите от RectangleRepository
        int width = RectangleRepository.getInstance().getWidth();
        int height = RectangleRepository.getInstance().getHeight();

        // Създаваме и добавяме RectangleView
        RectangleView rectangleView = new RectangleView(this, width, height);
        drawArea.addView(rectangleView);

        // Бутон за връщане към калкулатора
        Button backToCalculatorButton = findViewById(R.id.back_to_calculator_button);
        backToCalculatorButton.setOnClickListener(v -> finish());
    }

    // Custom View за рисуване на правоъгълника
    public class RectangleView extends View {
        private final int width;
        private final int height;

        public RectangleView(RectangleActivity context, int width, int height) {
            super(context);
            this.width = width;
            this.height = height;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint fillPaint = new Paint();
            fillPaint.setColor(Color.GREEN);

            Paint borderPaint = new Paint();
            borderPaint.setColor(Color.RED);
            borderPaint.setStrokeWidth(5);
            borderPaint.setStyle(Paint.Style.STROKE);

            int scaledWidth = width * 11;
            int scaledHeight = height * 11;

            int left = (getWidth() - scaledWidth) / 2;
            int top = (getHeight() - scaledHeight) / 2;

            canvas.drawRect(left, top, left + scaledWidth, top + scaledHeight, fillPaint);
            canvas.drawRect(left, top, left + scaledWidth, top + scaledHeight, borderPaint);
        }
    }
}
