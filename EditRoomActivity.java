package com.example.roomrental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class EditRoomActivity extends AppCompatActivity {

    EditText etMa, etTen, etGia, etNguoiThue, etSdt;
    CheckBox cbDaThue;
    Button btnLuu, btnHuy;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room); // dùng chung layout với Add

        etMa = findViewById(R.id.etMa);
        etTen = findViewById(R.id.etTen);
        etGia = findViewById(R.id.etGia);
        etNguoiThue = findViewById(R.id.etNguoiThue);
        etSdt = findViewById(R.id.etSdt);
        cbDaThue = findViewById(R.id.cbDaThue);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        // Nhận dữ liệu từ MainActivity
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        etMa.setText(intent.getStringExtra("maPhong"));
        etTen.setText(intent.getStringExtra("tenPhong"));
        etGia.setText(String.valueOf(intent.getDoubleExtra("giaThue", 0)));
        cbDaThue.setChecked(intent.getBooleanExtra("daThue", false));
        etNguoiThue.setText(intent.getStringExtra("tenNguoiThue"));
        etSdt.setText(intent.getStringExtra("soDienThoai"));

        btnLuu.setOnClickListener(v -> {
            String ma = etMa.getText().toString().trim();
            String ten = etTen.getText().toString().trim();
            String giaStr = etGia.getText().toString().trim();

            if (ma.isEmpty() || ten.isEmpty() || giaStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent result = new Intent();
            result.putExtra("position", position);
            result.putExtra("maPhong", ma);
            result.putExtra("tenPhong", ten);
            result.putExtra("giaThue", Double.parseDouble(giaStr));
            result.putExtra("daThue", cbDaThue.isChecked());
            result.putExtra("tenNguoiThue", etNguoiThue.getText().toString().trim());
            result.putExtra("soDienThoai", etSdt.getText().toString().trim());
            setResult(RESULT_OK, result);
            finish();
        });

        btnHuy.setOnClickListener(v -> finish());
    }
}