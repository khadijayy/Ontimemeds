//package com.example.ontimemeds;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//@Database(entities = {User.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//
//    private static AppDatabase instance;
//
//    public abstract UserDao userDao();
//
//    // Singleton pattern to ensure only one instance of Room Database
//    public static synchronized AppDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                            AppDatabase.class, "ontimemeds-database")
//                    .allowMainThreadQueries() // For simplicity, but should be avoided in production
//                    .build();
//        }
//        return instance;
//    }
//}
package com.example.ontimemeds;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();

    // Singleton pattern to ensure only one instance of Room Database
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ontimemeds-database")
                    .build();
        }
        return instance;
    }
}

