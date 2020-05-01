package com.thiago.fipp.appemprestimo;

import android.content.Intent;

public class Util {

    public static double calcInstallment(double value, double interest, int months) {
        return value * (interest / 100 / (1 - Math.pow(1 + interest / 100, months * -1)));
    }

    public static void putData(Intent intent, double cash, double interest, int months) {
        intent.putExtra("cash", cash);
        intent.putExtra("interest", interest);
        intent.putExtra("months", months);
    }
}
