package fr.univangers.cours.gestionplanning;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeData {
    public static void insert_fake_data(SQLiteDatabase db){
        if(db == null){
            return;
        }
        String format = "dd/MM/yyyy HH:mm";
        SimpleDateFormat formater = new SimpleDateFormat(format);

        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_PROF, "B. Da Mota");
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_COURS, "Reseau");
        cv.put(PlanningContract.PlanningEntry.COLUMN_DATE,formater.format(new Date()));
        list.add(cv);

        cv = new ContentValues();
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_PROF, "A. TODOSKOV");
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_COURS, "Organisation et conduite de projet");
        cv.put(PlanningContract.PlanningEntry.COLUMN_DATE, formater.format(new Date()));
        list.add(cv);

        cv = new ContentValues();
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_PROF, "Olivier Goudet");
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_COURS, "Design Pattern");
        cv.put(PlanningContract.PlanningEntry.COLUMN_DATE, formater.format(new Date()));
        list.add(cv);

        cv = new ContentValues();
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_PROF, "Christophe");
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_COURS, "Bidon");
        cv.put(PlanningContract.PlanningEntry.COLUMN_DATE, formater.format(new Date()));
        list.add(cv);

        cv = new ContentValues();
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_PROF, "Cécile");
        cv.put(PlanningContract.PlanningEntry.COLUMN_NAME_COURS, "Bidon 2");
        cv.put(PlanningContract.PlanningEntry.COLUMN_DATE, formater.format(new Date()));
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (PlanningContract.PlanningEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(PlanningContract.PlanningEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}
