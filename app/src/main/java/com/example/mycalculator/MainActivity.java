package com.example.mycalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /** Всички бутони*/
    //числа
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
    //аритметични оператори и равно
    buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEquals,
    // бутони за изтриване на съдържание и за изобразяване на правоъгълник във второ Activity
    buttonCA, buttonRectangle;

    /** Текстови полета*/
    private TextView resultTV;
    private TextView textFieldTV;
    private String currentInput = "";  // Holds current number input
    private String operator = "";  // Holds operator
    private double firstNum = 0;  // First number in the operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /** Инициализация на бутони и текстови полета*/
        resultTV = findViewById(R.id.result_tv);
        textFieldTV = findViewById(R.id.textfield_view);

        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);

        assignID(buttonPlus, R.id.button_plus);
        assignID(buttonMinus, R.id.button_minus);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMultiply, R.id.button_multiply);
        assignID(buttonEquals, R.id.button_equals);

        assignID(buttonCA, R.id.button_ca);
        assignID(buttonRectangle, R.id.button_rectangle);
    }

    /**Метод за инициализация на бутони*/
    void assignID(Button button, int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        // Handle digit button clicks (0-9)
        if (buttonText.matches("[0-9]")) {
            currentInput += buttonText;
            resultTV.setText(currentInput);
        }

        // Handle operator button clicks (+, -, *, /)
        else if (buttonText.equals("+") || buttonText.equals("-") ||
                buttonText.equals("*") || buttonText.equals("/")) {
            if (!currentInput.isEmpty()) {
                firstNum = Double.parseDouble(currentInput);
                textFieldTV.setText(currentInput);
                currentInput = "";
                operator = buttonText;
                resultTV.setText(operator);

            }
        }

        // Handle "CA" (Clear All) button
        else if (buttonText.equals("CA")) {
            currentInput = "";
            operator = "";
            firstNum = 0;
            resultTV.setText("");
            textFieldTV.setText("");
        }

        // Handle "=" (equal) button - Perform calculation
        else if (buttonText.equals("=")) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondNum = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstNum + secondNum;
                        break;
                    case "-":
                        result = firstNum - secondNum;
                        break;
                    case "*":
                        result = firstNum * secondNum;
                        break;
                    case "/":
                        if (secondNum != 0) {
                            result = firstNum / secondNum;
                        } else {
                            resultTV.setText("Error");
                            return;
                        }
                        break;
                }

                // Set the result as the current input
                currentInput = String.valueOf(result);
                operator = "";
                resultTV.setText(currentInput);

            }
        }

        else if (buttonText.equals("Rectangle")) {
            // Създаване на Intent за стартиране на RectangleActivity
            Intent intent = new Intent(MainActivity.this, RectangleActivity.class);
            startActivity(intent);
        }
    }
    
}