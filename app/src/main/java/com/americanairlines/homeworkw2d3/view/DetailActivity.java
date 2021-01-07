package com.americanairlines.homeworkw2d3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.americanairlines.homeworkw2d3.R;
import com.americanairlines.homeworkw2d3.model.data.Equipment;
import com.americanairlines.homeworkw2d3.util.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgUrl;
    private TextView tvName, tvPrice, tvDescription;
    private Button btnReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgUrl = findViewById(R.id.im_detail_url);
        tvName = findViewById(R.id.tv_detail_name);
        tvPrice = findViewById(R.id.tv_detail_price);
        tvDescription = findViewById(R.id.tv_detail_description);
        btnReturn = findViewById(R.id.btn_detail_go_back);

        Intent intent = getIntent();
        Equipment equipment = intent.getParcelableExtra(Constants.SELECT_EQIUPMENT);

        Glide.with(this).load(equipment.getImgUrl()).into(imgUrl);

        tvName.setText(equipment.getName());
        tvPrice.setText(String.format("$%.2f", equipment.getPrice()));
        tvDescription.setText(equipment.getDescription());

        btnReturn.setOnClickListener(v -> finish());



    }
}