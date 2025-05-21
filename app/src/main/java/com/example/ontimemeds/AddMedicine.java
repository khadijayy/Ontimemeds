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
//public class AddMedicine extends AppCompatActivity {
//    public void next(View view)
//    {
//        Intent intent = new Intent(AddMedicine.this,medicineDetails.class);
//        startActivity(intent);
//
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_add_medicine);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}
//
package com.example.ontimemeds;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

import java.util.Calendar;

public class AddMedicine extends AppCompatActivity {

    private EditText medicineName, dosage, intervalInput;
    private String selectedType = "Tablet"; // default
    private int selectedHour = -1, selectedMinute = -1;

    private MedicineViewModel medicineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_medicine);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        medicineName = findViewById(R.id.medicineName);
        dosage = findViewById(R.id.dosage);
        intervalInput = findViewById(R.id.intervalInput);

        // ViewModel setup
        medicineViewModel = new ViewModelProvider(this).get(MedicineViewModel.class);

        // Time picker
        findViewById(R.id.selectTimeButton).setOnClickListener(v -> openTimePicker());

        // Type selection (ImageView click listeners)
        setupTypeSelection();

        // Confirm button
        findViewById(R.id.confirmButton).setOnClickListener(v -> saveMedicine());
    }

    private void openTimePicker() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this,
                (TimePicker view, int hourOfDay, int minute1) -> {
                    selectedHour = hourOfDay;
                    selectedMinute = minute1;
                    Toast.makeText(this, "Time set: " + hourOfDay + ":" + String.format("%02d", minute1), Toast.LENGTH_SHORT).show();
                }, hour, minute, true);
        dialog.show();
    }

    private void setupTypeSelection() {
        ImageView bottle = findViewById(R.id.bottleIcon);
        ImageView syringe = findViewById(R.id.syringeIcon);
        ImageView pill = findViewById(R.id.pillIcon);
        ImageView tablet = findViewById(R.id.tabletIcon);

        bottle.setOnClickListener(v -> {
            selectedType = "Syrup";
            showToast("Syrup selected");
        });
        syringe.setOnClickListener(v -> {
            selectedType = "Injection";
            showToast("Injection selected");
        });
        pill.setOnClickListener(v -> {
            selectedType = "Pill";
            showToast("Pill selected");
        });
        tablet.setOnClickListener(v -> {
            selectedType = "Tablet";
            showToast("Tablet selected");
        });
    }

    private void saveMedicine() {
        String name = medicineName.getText().toString().trim();
        String dose = dosage.getText().toString().trim();
        String intervalStr = intervalInput.getText().toString().trim();

        if (name.isEmpty() || dose.isEmpty() || intervalStr.isEmpty() || selectedHour == -1) {
            showToast("Please fill all fields and select time.");
            return;
        }

        int intervalHours = Integer.parseInt(intervalStr);
        String time = String.format("%02d:%02d", selectedHour, selectedMinute);

        Medicine medicine = new Medicine(name, dose, selectedType, intervalHours, time);
        medicineViewModel.insert(medicine);

        showToast("Medicine Saved!");

        // Go back to mainpageActivity
        Intent intent = new Intent(this, mainpageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

