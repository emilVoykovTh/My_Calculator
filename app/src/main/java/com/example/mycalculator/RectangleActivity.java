package com.example.mycalculator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RectangleActivity extends AppCompatActivity {

    private EditText firstOperandEditText;
    private EditText secondOperandEditText;
    private Button drawButton;
    private FrameLayout drawArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);

        // Initialize UI components
        firstOperandEditText = findViewById(R.id.width_operand);
        secondOperandEditText = findViewById(R.id.length_operand);
        drawButton = findViewById(R.id.draw_button);
        drawArea = findViewById(R.id.draw_area);

        drawButton.setOnClickListener(v -> {
            String widthStr = firstOperandEditText.getText().toString();
            String heightStr = secondOperandEditText.getText().toString();

            if (!widthStr.isEmpty() && !heightStr.isEmpty()) {
                try {
                    int width = Integer.parseInt(widthStr);
                    int height = Integer.parseInt(heightStr);

                    // Clear previous drawing
                    drawArea.removeAllViews();

                    // Add the new RectangleView
                    RectangleView rectangleView = new RectangleView(this, width, height);
                    drawArea.addView(rectangleView);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter both width and height", Toast.LENGTH_SHORT).show();
            }
        });
        Button backToCalculatorButton = findViewById(R.id.back_to_calculator_button);
        backToCalculatorButton.setOnClickListener(v -> {
            finish(); // Closes this activity and returns to MainActivity
        });
    }


    // Custom View за рисуване на правоъгълника
    public class RectangleView extends View {
        private int firstOperand;
        private int secondOperand;

        public RectangleView(RectangleActivity context, int firstOperand, int secondOperand) {
            super(context);
            this.firstOperand = firstOperand;
            this.secondOperand = secondOperand;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Създаване на Paint за запълване на правоъгълника
            Paint fillPaint = new Paint();
            fillPaint.setColor(Color.GREEN);  // Зелен цвят за запълване

            // Създаване на Paint за контур на правоъгълника
            Paint borderPaint = new Paint();
            borderPaint.setColor(Color.RED);  // Червен цвят за контур
            borderPaint.setStrokeWidth(5);    // Ширина на контура
            borderPaint.setStyle(Paint.Style.STROKE);  // Само контур, без запълване

            // Изчисляване на размерите на правоъгълника (първия и втория операнд * 11)
            int width = firstOperand * 11;
            int height = secondOperand * 11;

            // Центриране на правоъгълника
            int left = (getWidth() - width) / 2;
            int top = (getHeight() - height) / 2;

            // Рисуване на правоъгълника
            canvas.drawRect(left, top, left + width, top + height, fillPaint);  // Запълнен правоъгълник
            canvas.drawRect(left, top, left + width, top + height, borderPaint);  // Червен контур
        }
    }
}
