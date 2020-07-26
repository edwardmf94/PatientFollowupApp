package com.pe.patient.followup.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.DrugstoreOrder;

import java.util.ArrayList;

public class DrugstoreOrdersAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DrugstoreOrder> listItems;

    public DrugstoreOrdersAdapter(Context context, ArrayList<DrugstoreOrder> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    public void setListItems(ArrayList<DrugstoreOrder> listItems){
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
        DrugstoreOrder item = (DrugstoreOrder) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_drugstore_order_item, null);
        TextView tevOrderDetails = (TextView) convertView.findViewById(R.id.tevOrderDetails);
        TextView tevOrderDateStart = (TextView) convertView.findViewById(R.id.tevOrderDateStart);
        TextView tevOrderDatePending = (TextView) convertView.findViewById(R.id.tevOrderDatePending);
        TextView tevOrderDateProgress = (TextView) convertView.findViewById(R.id.tevOrderDateProgress);

        tevOrderDetails.setText(item.getDetails());
        tevOrderDateStart.setText(item.getDateStart());
        tevOrderDatePending.setText(item.getDatePending());
        tevOrderDateProgress.setText(item.getDateProgress());
        return convertView;
    }
}
