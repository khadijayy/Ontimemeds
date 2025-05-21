package com.example.ontimemeds;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicineViewModel extends AndroidViewModel {

    private MedicineRepository repository;
    private LiveData<List<Medicine>> allMedicines;

    public MedicineViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicineRepository(application);
        allMedicines = repository.getAllMedicines();
    }

    public void insert(Medicine medicine) {
        repository.insert(medicine);
    }

    public LiveData<List<Medicine>> getAllMedicines() {
        return allMedicines;
    }
}
