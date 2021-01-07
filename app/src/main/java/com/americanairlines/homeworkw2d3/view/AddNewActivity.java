package com.americanairlines.homeworkw2d3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.americanairlines.homeworkw2d3.R;
import com.americanairlines.homeworkw2d3.model.data.Equipment;
import com.americanairlines.homeworkw2d3.model.db.EquipmentDatabaseHelper;

public class AddNewActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPrice;
    private EditText etDescription, etImgUrl;
    private Button btnSubmit;


    private EquipmentDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        etName = findViewById(R.id.et_item_name);
        etPrice = findViewById(R.id.et_item_price);
        etDescription = findViewById(R.id.et_description);
        etImgUrl = findViewById(R.id.et_image_url);

        btnSubmit = findViewById(R.id.btn_submit);

        databaseHelper = new EquipmentDatabaseHelper(this);

        btnSubmit.setOnClickListener(v -> {
            if(checkEmpty()){
                String name = etName.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String imgUrl = etImgUrl.getText().toString().trim();

                if(etPrice.getText().toString().trim().isEmpty()){
                    String price = "";
                }
                else {
                    double price = Double.parseDouble(etPrice.getText().toString().trim());
                    databaseHelper.insertEquipment(new Equipment(name, price, description, imgUrl));
                    Toast.makeText(this, "Entry Created", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }



        });
    }

    private boolean checkEmpty() {
        if(etName.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        else if (etPrice.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please enter a price", Toast.LENGTH_SHORT).show();
        else if (etDescription.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
        else if (etImgUrl.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please enter a image URL", Toast.LENGTH_SHORT).show();
        else
            return true;

        return false;
    }
}