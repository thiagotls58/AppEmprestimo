package com.thiago.fipp.appemprestimo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etCash, etInterest;
    private SeekBar sbMonth;
    private TextView tvQtdMonth, tvInstallmentValue;
    private Button btMonthlyInstallments;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itFinancing:
                listInstallments();
                break;
            case R.id.itHelp:
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                callPutData(intent);
                startActivity(intent);
                break;
            case R.id.itClose:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cash", etCash.getText().toString());
        editor.putString("interest", etInterest.getText().toString());
        editor.putInt("months", sbMonth.getProgress());
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCash = findViewById(R.id.etCash);
        etInterest = findViewById(R.id.etInterest);
        sbMonth = findViewById(R.id.sbMonth);
        tvQtdMonth = findViewById(R.id.tvQtdMonth);
        tvInstallmentValue = findViewById(R.id.tvInstallmentValue);
        btMonthlyInstallments = findViewById(R.id.btMonthlyInstallments);

        //recuperando os últimos valores da simulação
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        etCash.setText(prefs.getString("cash", ""));
        etInterest.setText(prefs.getString("interest", ""));
        sbMonth.setProgress(prefs.getInt("months", 0));
        tvQtdMonth.setText("" + (prefs.getInt("months", 0) + 1));
        setTextInstallmentValue(prefs.getInt("months", 0) + 1);

        sbMonth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvQtdMonth.setText("" + (progress + 1));
                setTextInstallmentValue(progress + 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btMonthlyInstallments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listInstallments();
            }
        });
    }

    private void listInstallments() {
        Intent intent = new Intent(MainActivity.this, InstallmentsActivity.class);
        callPutData(intent);
        startActivity(intent);
    }

    private void callPutData(Intent intent) {
        double cash = Double.parseDouble(etCash.getText().toString());
        double interest = Double.parseDouble(etInterest.getText().toString());
        int months = Integer.parseInt("" + (sbMonth.getProgress() + 1));
        Util.putData(intent, cash, interest, months);
    }

    private void setTextInstallmentValue(int months) {
        String strCash = etCash.getText().toString();
        String strInterest = etInterest.getText().toString();
        double value = 0;
        double interest = 0;
        if (!strCash.isEmpty()) {
            value = Double.parseDouble(strCash);
        }
        if (!strInterest.isEmpty()) {
            interest = Double.parseDouble(strInterest);
        }
        double installmentValue = Util.calcInstallment(value, interest, months);
        if (Double.isNaN(installmentValue)){
            tvInstallmentValue.setText("0");
        } else {
            tvInstallmentValue.setText(String.format("%10.2f", installmentValue));
        }
    }
}
