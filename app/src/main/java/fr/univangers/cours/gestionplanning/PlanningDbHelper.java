package fr.univangers.cours.gestionplanning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class PlanningDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plannification.db";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ PlanningContract.PlanningEntry.TABLE_NAME+"("+ PlanningContract.PlanningEntry._ID+" INTEGER PRIMARY KEY,"
                    + PlanningContract.PlanningEntry.COLUMN_NAME_COURS+" TEXT,"
                    +PlanningContract.PlanningEntry.COLUMN_DATE+" TEXT,"
                    + PlanningContract.PlanningEntry.COLUMN_NAME_PROF +" TEXT)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ PlanningContract.PlanningEntry.TABLE_NAME;

    public PlanningDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        FakeData.insert_fake_data(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
