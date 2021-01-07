package com.americanairlines.homeworkw2d3.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.americanairlines.homeworkw2d3.R;
import com.americanairlines.homeworkw2d3.model.data.Equipment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class EquipmentItemAdapter extends BaseAdapter {

    private EquipmentHandler equipmentHandler;
    private List<Equipment> equipmentList;

    public interface EquipmentHandler{
        void selectedEquipment(Equipment selectedEquipment);
    }

    public EquipmentItemAdapter(EquipmentHandler equipmentHandler, List<Equipment> equipmentList) {
        this.equipmentHandler = equipmentHandler;
        this.equipmentList = equipmentList;
    }

    @Override
    public int getCount() {
        return equipmentList.size();
    }

    @Override
    public Object getItem(int position) {
        return equipmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Equipment equipment = equipmentList.get(position);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipment_list, parent, false);

        v.setOnClickListener(v1 -> {
            equipmentHandler.selectedEquipment(equipment);
        });

        ImageView imUrl = v.findViewById(R.id.im_url);
        TextView tvName = v.findViewById(R.id.tv_item_name);
        TextView tvPrice = v.findViewById(R.id.tv_item_price);

        Glide.with(v.getContext())
                .load(equipmentList.get(position).getImgUrl())
                .into(imUrl);

        tvName.setText(equipmentList.get(position).getName());
        tvPrice.setText(String.format("$%.2f",equipmentList.get(position).getPrice()));

        return v;
    }
}
