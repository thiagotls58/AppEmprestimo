package com.thiago.fipp.appemprestimo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InstallmentAdapter extends ArrayAdapter<Installment> {

    private int layout;

    public InstallmentAdapter(Context context, int resource, List<Installment> installments) {
        super(context, resource, installments);
        layout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView tvNumberInstallment = convertView.findViewById(R.id.tvNumberInstallment);
        TextView tvValueInstallment = convertView.findViewById(R.id.tvValueInstallment);
        TextView tvOutstandingBal = convertView.findViewById(R.id.tvOutstandingBal);
        Installment installment = this.getItem(position);

        tvNumberInstallment.setText(String.format("%02d", installment.getNumber()));
        tvValueInstallment.setText(String.format("%10.2f", installment.getValue()));
        tvOutstandingBal.setText(String.format("%10.2f", installment.getOutstandingBalance()));

        if (installment.getNumber() % 2 == 0)
            convertView.setBackgroundColor(Color.parseColor("#EEEEEE"));
        else
            convertView.setBackgroundColor(0xFFDDDDDD);

        return convertView;
    }
}
