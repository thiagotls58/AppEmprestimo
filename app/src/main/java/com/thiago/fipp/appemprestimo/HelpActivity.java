package com.thiago.fipp.appemprestimo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itHelpFinancing:

                Intent intent = getIntent();
                double cash = intent.getDoubleExtra("cash", 0);
                double interest = intent.getDoubleExtra("interest", 0);
                int months = intent.getIntExtra("months", 0);

                intent = new Intent(HelpActivity.this, InstallmentsActivity.class);
                Util.putData(intent, cash, interest, months);
                startActivity(intent);
                break;
            case R.id.itHelpClose:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}
