package com.example.roomrental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RoomAdapter adapter;
    List<Room> roomList = new ArrayList<>();
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnThem = findViewById(R.id.btnThem);

        // Dữ liệu mẫu
        roomList.add(new Room("P01", "Phòng 101", 2500000, false, "", ""));
        roomList.add(new Room("P02", "Phòng 102", 3000000, true, "Nguyễn Văn A", "0901234567"));
        roomList.add(new Room("P03", "Phòng 103", 2800000, false, "", ""));

        adapter = new RoomAdapter(this, roomList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnThem.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddRoomActivity.class);
            startActivityForResult(intent, 2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 2) {
                // Thêm phòng mới
                String ma = data.getStringExtra("maPhong");
                String ten = data.getStringExtra("tenPhong");
                double gia = data.getDoubleExtra("giaThue", 0);
                boolean daThue = data.getBooleanExtra("daThue", false);
                String nguoiThue = data.getStringExtra("tenNguoiThue");
                String sdt = data.getStringExtra("soDienThoai");
                roomList.add(new Room(ma, ten, gia, daThue, nguoiThue, sdt));
            } else if (requestCode == 1) {
                // Sửa phòng
                int pos = data.getIntExtra("position", -1);
                if (pos >= 0) {
                    roomList.get(pos).setMaPhong(data.getStringExtra("maPhong"));
                    roomList.get(pos).setTenPhong(data.getStringExtra("tenPhong"));
                    roomList.get(pos).setGiaThue(data.getDoubleExtra("giaThue", 0));
                    roomList.get(pos).setDaThue(data.getBooleanExtra("daThue", false));
                    roomList.get(pos).setTenNguoiThue(data.getStringExtra("tenNguoiThue"));
                    roomList.get(pos).setSoDienThoai(data.getStringExtra("soDienThoai"));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}