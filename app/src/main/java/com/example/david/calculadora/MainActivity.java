package com.example.david.calculadora;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private TextView txtResult; // Reference to EditText of result
    private int result = 0;     // Result of computation
    private String inStr = "0"; // Current input string
    // Previous operator: '+', '-', '*', '/', '=' or ' ' (no operator)
    private char lastOperator = ' ';////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperar una referencia al campo EditarTexto para visualizar el resultado.

        txtResult = (TextView) findViewById(R.id.txtResultId);
        txtResult.setText("0");


        //le paso escuchador (this class)  para todos los botones dependiendo de cada boton
        BtnListener listener = new BtnListener();
        ((Button) findViewById(R.id.btnNum0Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum1Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum2Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum3Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum4Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum5Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum6Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum7Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum8Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnNum9Id)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnAddId)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnSubId)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMulId)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnDivId)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnClearId)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnEqualId)).setOnClickListener(listener);


    }

    private class BtnListener implements OnClickListener {
        // On-click event handler for all the buttons
        @Override
        public void onClick(View view) {
            //le paso el id que recojo en el caso de determinado boton  pulsado
            switch (view.getId()) {
                // Number buttons: '0' to '9'
                case R.id.btnNum0Id:
                case R.id.btnNum1Id:
                case R.id.btnNum2Id:
                case R.id.btnNum3Id:
                case R.id.btnNum4Id:
                case R.id.btnNum5Id:
                case R.id.btnNum6Id:
                case R.id.btnNum7Id:
                case R.id.btnNum8Id:
                case R.id.btnNum9Id:
                    //casteo el entero que viene del boton y lo paso a string
                    String inDigit = ((Button) view).getText().toString();
                    if (inStr.equals("0")) {
                        inStr = inDigit; // no leading zero
                    } else {
                        inStr += inDigit; // concatena el digito y lo guarda en variable
                    }
                    txtResult.setText(inStr);//muestra el string del resultado
                    // Clear buffer if last operator is '='
                    if (lastOperator == '=') {
                        result = 0;
                        lastOperator = ' ';
                    }
                    break;

                // Operacion con los botones: '+', '-', '*', '/' and '='
                case R.id.btnAddId://recoge  el valor del boton +
                    compute();
                    lastOperator = '+';
                    break;
                case R.id.btnSubId://recoge el valor del boton -
                    compute();
                    lastOperator = '-';
                    break;
                case R.id.btnMulId://recoge el valor del boton *
                    compute();
                    lastOperator = '*';
                    break;
                case R.id.btnDivId://recoge el valor del boton /
                    compute();
                    lastOperator = '/';
                    break;
                case R.id.btnEqualId://recoge el valor del boton =
                    compute();
                    lastOperator = '=';
                    break;

                // Clear button
                case R.id.btnClearId://recoge el valor del boton C
                    result = 0;
                    inStr = "0";
                    lastOperator = ' ';
                    txtResult.setText("0");
                    break;
            }
        }

        // User pushes '+', '-', '*', '/' or '=' button.
        // Perform computation on the previous result and the current input number,
        // based on the previous operator.
        private void compute() {
            int inNum = Integer.parseInt(inStr);//convierte el string que recogemos del boton a entero
            inStr = "0";                        //inicia el valor que recoge de nuevo a 0
            if (lastOperator == ' ') {          //si el utimo boton no es ninguno es valor es el que tiene numero
                result = inNum;
            } else if (lastOperator == '+') {   //si el ultimo boton es + suma
                result += inNum;
            } else if (lastOperator == '-') {   //si el ultimo boton es  - resta
                result -= inNum;
            } else if (lastOperator == '*') {   //si el ultimo boton es * multiplica
                result *= inNum;
            } else if (lastOperator == '/') {   //si el ultimo boton es  / divide
                result /= inNum;
            } else if (lastOperator == '=') {   ////si el ultimo boton es = muestra el resultado
                // Keep the result for the next operation
            }
            txtResult.setText(String.valueOf(result));
        }
    }
}