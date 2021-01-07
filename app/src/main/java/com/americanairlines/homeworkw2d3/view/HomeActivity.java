package com.americanairlines.homeworkw2d3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.americanairlines.homeworkw2d3.R;
import com.americanairlines.homeworkw2d3.model.data.Equipment;
import com.americanairlines.homeworkw2d3.model.db.EquipmentDatabaseHelper;
import com.americanairlines.homeworkw2d3.util.Constants;
import com.americanairlines.homeworkw2d3.view.adapter.EquipmentItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements EquipmentItemAdapter.EquipmentHandler {

    private Button btnAddNew;
//    private ListView llEquipmentList;
    private GridView gvEquipmentList;
    private TextView tvHint, tvSpent;

    private EquipmentDatabaseHelper databaseHelper;

    private List<Equipment> equipmentList = new ArrayList<>();
    private EquipmentItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNew = findViewById(R.id.btn_add_new);
        //llEquipmentList = findViewById(R.id.lv_items);
        gvEquipmentList = findViewById(R.id.gv_items);
        tvHint = findViewById(R.id.tv_hint_main);
        tvSpent = findViewById(R.id.tv_total_spent);

        databaseHelper = new EquipmentDatabaseHelper(this);

        adapter = new EquipmentItemAdapter(this, equipmentList);

        //llEquipmentList.setAdapter(adapter);

        //gvEquipmentList.setAdapter(adapter);

        btnAddNew.setOnClickListener(v->{
            Intent intent = new Intent(HomeActivity.this, AddNewActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        double  totalSpent = 0.00;


        readDatabase();
        //llEquipmentList.setAdapter(new EquipmentItemAdapter(this, equipmentList));

        for (Equipment e: equipmentList) {
            totalSpent += e.getPrice();
        }

        tvSpent.setText(String.format("Total Spent: $%.2f", totalSpent));

        if(equipmentList.size() > 0){
            tvHint.setVisibility(View.GONE);
            gvEquipmentList.setAdapter(new EquipmentItemAdapter(this, equipmentList));
        } else {
            tvHint.setVisibility(View.VISIBLE);
        }

    }

    private void readDatabase() {
        equipmentList.clear();
        Cursor cursor = databaseHelper.getAllEquipments();
        cursor.move(-1);

        while(cursor.moveToNext()){
            String eName = cursor.getString(cursor.getColumnIndex(EquipmentDatabaseHelper.COLUMN_NAME));
            double ePrice = cursor.getDouble(cursor.getColumnIndex(EquipmentDatabaseHelper.COLUMN_PRICE));
            String eDescription = cursor.getString(cursor.getColumnIndex(EquipmentDatabaseHelper.COLUMN_DESCRIPTION));
            String eImgUrl = cursor.getString(cursor.getColumnIndex(EquipmentDatabaseHelper.COLUMN_IMGURL));

            equipmentList.add(new Equipment(eName, ePrice, eDescription, eImgUrl));
        }
    }

    @Override
    public void selectedEquipment(Equipment selectedEquipment) {

        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
        intent.putExtra(Constants.SELECT_EQIUPMENT, selectedEquipment);
        startActivity(intent);

    }
}