package com.example.ontimemeds;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicineRepository {
    private MedicineDao medicineDao;
    private LiveData<List<Medicine>> allMedicines;
    private ExecutorService executorService;

    public MedicineRepository(Application application) {
        MedicineDatabase db = MedicineDatabase.getInstance(application);
        medicineDao = db.medicineDao();
        allMedicines = medicineDao.getAllMedicines();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Medicine medicine) {
        executorService.execute(() -> medicineDao.insert(medicine));
    }

    public void delete(Medicine medicine) {
        executorService.execute(() -> medicineDao.delete(medicine));
    }

    public LiveData<List<Medicine>> getAllMedicines() {
        return allMedicines;
    }
}
