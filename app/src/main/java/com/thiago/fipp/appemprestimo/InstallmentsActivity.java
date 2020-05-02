package com.thiago.fipp.appemprestimo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class InstallmentsActivity extends AppCompatActivity {

    private ListView lvInstallments;
    private List<Installment> installments;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.installments_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itInstallmentsHelp:

                Intent intent = getIntent();
                double cash = intent.getDoubleExtra("cash", 0);
                double interest = intent.getDoubleExtra("interest", 0);
                int months = intent.getIntExtra("months", 0);

                intent = new Intent(InstallmentsActivity.this, HelpActivity.class);
                Util.putData(intent, cash, interest, months);
                startActivity(intent);
                break;
            case R.id.itInstallmentsClose:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installments);

        lvInstallments = findViewById(R.id.lvInstallments);
        Intent intent = getIntent();
        double cash = intent.getDoubleExtra("cash", 0);
        double interest = intent.getDoubleExtra("interest", 0);
        int months = intent.getIntExtra("months", 0);
        createListInstallments(cash, interest, months);
    }

    private void createListInstallments(double cash, double interest, int months) {
        installments = new ArrayList();
        double valueInterest;
        double installment = Util.calcInstallment(cash, interest, months);
        for (int i = 0; i < months; i++) {
            valueInterest = cash * interest / 100;
            cash = cash - (installment - valueInterest); // saldo devedor
            installments.add(new Installment(i + 1, installment, cash));
        }
        lvInstallments.setAdapter(new InstallmentAdapter(this, R.layout.block_list, installments));
    }
}
