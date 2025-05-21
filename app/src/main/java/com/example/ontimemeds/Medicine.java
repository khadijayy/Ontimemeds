package com.example.ontimemeds;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//public class Medicine {
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    private String name;
//    private String dosage;
//    private String type;
//    private int intervalHours;
//
//    // Constructor
//    public Medicine(String name, String dosage, String type, int intervalHours) {
//        this.name = name;
//        this.dosage = dosage;
//        this.type = type;
//        this.intervalHours = intervalHours;
//    }
//
//    // Getters and Setters
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getName() { return name; }
//    public String getDosage() { return dosage; }
//    public String getType() { return type; }
//    public int getIntervalHours() { return intervalHours; }
//}
//
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicines")
public class Medicine {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String time;   // e.g. "08:00 AM"
    private String dosage; // e.g. "1 Tablet"
    private String type;
    private int intervalHours;

    // Constructor
    public Medicine(String name, String time, String dosage) {
        this.name = name;
        this.time = time;
        this.dosage = dosage;
    }
    public Medicine(String name, String dose, String type, int intervalHours, String time) {
        this.name = name;
        this.dosage = dosage;
        this.type = type;
        this.intervalHours = intervalHours;
        this.time = time;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public void setType(String type) {
        this.type = type;
    }

    public void setIntervalHours(int intervalHours) {
        this.intervalHours = intervalHours;
    }

    public String getName() { return name; }
    public String getTime() { return time; }
    public String getDosage() { return dosage; }
    public String getType() {return type; }
    public int getIntervalHours() {return intervalHours;}
}
