package com.pe.patient.followup.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pe.patient.followup.R;
import com.pe.patient.followup.model.PatientFile;
import com.pe.patient.followup.presenter.PatientFileActivityPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatientFileAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PatientFile> listItems;
    private PatientFileActivityPresenter presenter;

    public PatientFileAdapter(Context context, ArrayList<PatientFile> listItems, PatientFileActivityPresenter presenter) {
        this.context = context;
        this.listItems = listItems;
        this.presenter = presenter;
    }

    public void setListItems(ArrayList<PatientFile> listItems){
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
        return listItems.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PatientFile item = (PatientFile) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.layout_patient_file_item, null);
        TextView tevIdPatientFile = (TextView) convertView.findViewById(R.id.tevIdPatientFile);
        TextView tevDepartment = (TextView) convertView.findViewById(R.id.tevDepartment);
        TextView tevDrugstore = (TextView) convertView.findViewById(R.id.tevDrugstore);
        TextView tevPrimaryDiagnosis = (TextView) convertView.findViewById(R.id.tevPrimaryDiagnosis);
        Button btnDeletePrimaryDiagnosis = (Button) convertView.findViewById(R.id.btnDeletePrimaryDiagnosis);

        btnDeletePrimaryDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.findViewById()
                presenter.setProgressBarVisiblity(View.VISIBLE);
                presenter.deletePatientFile(Long.valueOf(getItemId(position)).intValue(), context);
            }
        });
        tevIdPatientFile.setText(item.getId().toString());
        tevDepartment.setText(item.getDepartment());
        tevDrugstore.setText(item.getDrugstore());
        tevPrimaryDiagnosis.setText(item.getPrimaryDiagnosis());
        return convertView;
    }
}
