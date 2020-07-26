package com.pe.patient.followup.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.PatientRecipient;

import java.util.ArrayList;

public class RecipientsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PatientRecipient> listItems;
    //private PatientFileActivityPresenter presenter;


    public RecipientsAdapter(Context context, ArrayList<PatientRecipient> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    public void setListItems(ArrayList<PatientRecipient> listItems){
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        if(listItems!=null){
            return listItems.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PatientRecipient item = (PatientRecipient) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_patient_recipient_item, null);
        TextView tevPatientRecipientName = (TextView) convertView.findViewById(R.id.tevPatientRecipientName);
        TextView tevPatientRecipientCif = (TextView) convertView.findViewById(R.id.tevPatientRecipientCif);
        TextView tevPatientRecipientListPrice = (TextView) convertView.findViewById(R.id.tevPatientRecipientListPrice);
        TextView tevPatientRecipientIafas = (TextView) convertView.findViewById(R.id.tevPatientRecipientIafas);

        tevPatientRecipientName.setText(item.getName());
        tevPatientRecipientCif.setText(item.getCif());
        tevPatientRecipientListPrice.setText(item.getPriceList());
        tevPatientRecipientIafas.setText(item.getIafas());
        return convertView;
    }
}
