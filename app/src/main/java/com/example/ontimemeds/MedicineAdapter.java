package com.example.ontimemeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<Medicine> medicineList = new ArrayList<>();

    public void setMedicineList(List<Medicine> medicines) {
        this.medicineList = medicines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicine, parent, false);
        return new MedicineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine current = medicineList.get(position);
        holder.nameText.setText(current.getName());
        holder.timeText.setText(current.getTime());
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, timeText;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textViewMedicineName);
            timeText = itemView.findViewById(R.id.textViewMedicineTime);
        }
    }
}
//public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
//
//    private List<Medicine> medicines;
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(Medicine medicine);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    // Constructor
//    public MedicineAdapter(List<Medicine> medicines) {
//        this.medicines = medicines;
//    }
//
//    @NonNull
//    @Override
//    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
//        return new MedicineViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
//        Medicine medicine = medicines.get(position);
//        holder.bind(medicine);
//    }
//
//    @Override
//    public int getItemCount() {
//        return medicines.size();
//    }
//
//    public class MedicineViewHolder extends RecyclerView.ViewHolder {
//        TextView name, time;
//
//        public MedicineViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = itemView.findViewById(R.id.medicineName);
//            time = itemView.findViewById(R.id.medicineTime);
//
//            itemView.setOnClickListener(v -> {
//                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(medicines.get(getAdapterPosition()));
//                }
//            });
//        }
//
//        public void bind(Medicine medicine) {
//            name.setText(medicine.getName());
//            time.setText(medicine.getTime());
//        }
//    }
//}
//public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
//
//    private List<Medicine> medicines = new ArrayList<>(); // ✅ initialize
//
//    // Optional constructor if needed in future
//    public MedicineAdapter() {
//        // No need to pass list, already initialized
//    }
//
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(Medicine medicine);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    public void setMedicineList(List<Medicine> medicines) {
//        this.medicines = medicines;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
//        return new MedicineViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
//        Medicine medicine = medicines.get(position);
//        holder.bind(medicine);
//    }
//
//    @Override
//    public int getItemCount() {
//        return medicines.size();
//    }
//
//    public class MedicineViewHolder extends RecyclerView.ViewHolder {
//        TextView name, time;
//
//        public MedicineViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = itemView.findViewById(R.id.medicineName);
//            time = itemView.findViewById(R.id.medicineTime);
//
//            itemView.setOnClickListener(v -> {
//                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(medicines.get(getAdapterPosition()));
//                }
//            });
//        }
//
//        public void bind(Medicine medicine) {
//            name.setText(medicine.getName());
//            time.setText(medicine.getTime());
//        }
//    }
//}
//
