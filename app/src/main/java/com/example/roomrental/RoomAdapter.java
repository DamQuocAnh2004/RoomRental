package com.example.roomrental;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private List<Room> roomList;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.tvTenPhong.setText(room.getTenPhong());
        holder.tvGiaThue.setText("Giá: " + (long)room.getGiaThue() + " VNĐ/tháng");
        holder.tvTinhTrang.setText(room.isDaThue() ? "Đã thuê" : "Còn trống");

        if (room.isDaThue()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFCDD2")); // đỏ nhạt
            holder.tvTinhTrang.setTextColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#C8E6C9")); // xanh nhạt
            holder.tvTinhTrang.setTextColor(Color.parseColor("#2E7D32"));
        }

        // Click để sửa
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditRoomActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("maPhong", room.getMaPhong());
            intent.putExtra("tenPhong", room.getTenPhong());
            intent.putExtra("giaThue", room.getGiaThue());
            intent.putExtra("daThue", room.isDaThue());
            intent.putExtra("tenNguoiThue", room.getTenNguoiThue());
            intent.putExtra("soDienThoai", room.getSoDienThoai());
            ((MainActivity) context).startActivityForResult(intent, 1);
        });

        // Nhấn nút xóa
        holder.btnXoa.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc muốn xóa phòng " + room.getTenPhong() + "?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        roomList.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() { return roomList.size(); }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhong, tvGiaThue, tvTinhTrang;
        Button btnXoa;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhong = itemView.findViewById(R.id.tvTenPhong);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}
