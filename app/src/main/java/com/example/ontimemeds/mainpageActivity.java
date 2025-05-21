//package com.example.ontimemeds;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class mainpageActivity extends AppCompatActivity {
//    public void TapPills(View view)
//    {
//        Intent intent = new Intent(mainpageActivity.this,AddMedicine.class);
//        startActivity(intent);
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_mainpage);
//
//    }
//}
package com.example.ontimemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mainpageActivity extends AppCompatActivity {

    private MedicineViewModel viewModel;
    private MedicineAdapter adapter;
    private TextView medicineCountTextView;

    public void TapPills(View view) {
        Intent intent = new Intent(mainpageActivity.this, AddMedicine.class);
        startActivity(intent);
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainpage);

        // Initialize views
        RecyclerView recyclerView = findViewById(R.id.medicineRecyclerView);
        medicineCountTextView = findViewById(R.id.medicineCount);

        // Set RecyclerView layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MedicineAdapter(); // We'll define this adapter in a moment
        recyclerView.setAdapter(adapter);

        // Get ViewModel and observe the data
        viewModel = new ViewModelProvider(this).get(MedicineViewModel.class);
        viewModel.getAllMedicines().observe(this, medicines -> {
            adapter.setMedicineList(medicines);
            medicineCountTextView.setText(String.valueOf(medicines.size()));
        });
    }
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_mainpage);
//
//        // Initialize views
//        RecyclerView recyclerView = findViewById(R.id.medicineRecyclerView);
//        medicineCountTextView = findViewById(R.id.medicineCount);
//
//        // Set RecyclerView layout
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new MedicineAdapter();
//        recyclerView.setAdapter(adapter);
//
//        // ✅ Handle item clicks
//        adapter.setOnItemClickListener(medicine -> {
//            Intent intent = new Intent(mainpageActivity.this, medicineDetails.class);
//            intent.putExtra("medicine_id", medicine.getId()); // pass the ID
//            startActivity(intent);
//        });
//
//        // Get ViewModel and observe the data
//        viewModel = new ViewModelProvider(this).get(MedicineViewModel.class);
//        viewModel.getAllMedicines().observe(this, medicines -> {
//            adapter.setMedicineList(medicines);
//            medicineCountTextView.setText(String.valueOf(medicines.size()));
//        });
//    }
//}