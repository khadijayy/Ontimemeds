package com.example.ontimemeds;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicineDao {

    @Insert
    void insert(Medicine medicine);

    @Delete
    void delete(Medicine medicine);

    @Query("SELECT * FROM medicines ORDER BY id DESC")
    LiveData<List<Medicine>> getAllMedicines();
}
