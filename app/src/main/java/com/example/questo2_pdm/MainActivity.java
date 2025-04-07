package com.example.questo2_pdm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText currentSalaryEditText;
    private RadioGroup percentageRadioGroup;
    private RadioButton radio40Percent;
    private RadioButton radio45Percent;
    private RadioButton radio50Percent;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentSalaryEditText = findViewById(R.id.currentSalaryEditText);
        percentageRadioGroup = findViewById(R.id.percentageRadioGroup);
        radio40Percent = findViewById(R.id.radio40Percent);
        radio45Percent = findViewById(R.id.radio45Percent);
        radio50Percent = findViewById(R.id.radio50Percent);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSalaryAdjustment();
            }
        });
    }

    private void calculateSalaryAdjustment() {
        String currentSalaryText = currentSalaryEditText.getText().toString();

        if (currentSalaryText.isEmpty()) {
            Toast.makeText(this, "Por favor, informe o salário atual", Toast.LENGTH_SHORT).show();
            return;
        }

        double currentSalary;
        try {
            currentSalary = Double.parseDouble(currentSalaryText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Salário inválido. Por favor, informe um valor numérico", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentSalary <= 0) {
            Toast.makeText(this, "Salário inválido. Por favor, informe um valor positivo", Toast.LENGTH_SHORT).show();
            return;
        }

        double percentage;
        int checkedId = percentageRadioGroup.getCheckedRadioButtonId();

        if (checkedId == radio40Percent.getId()) {
            percentage = 0.40;
        } else if (checkedId == radio45Percent.getId()) {
            percentage = 0.45;
        } else if (checkedId == radio50Percent.getId()) {
            percentage = 0.50;
        } else {
            Toast.makeText(this, "Por favor, selecione um percentual de aumento", Toast.LENGTH_SHORT).show();
            return;
        }

        double increase = currentSalary * percentage;
        double newSalary = currentSalary + increase;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        String resultText = "Salário atual: " + currencyFormat.format(currentSalary) + "\n";
        resultText += "Percentual de aumento: " + (int)(percentage * 100) + "%\n";
        resultText += "Valor do aumento: " + currencyFormat.format(increase) + "\n";
        resultText += "Novo salário: " + currencyFormat.format(newSalary);

        resultTextView.setText(resultText);
    }
}