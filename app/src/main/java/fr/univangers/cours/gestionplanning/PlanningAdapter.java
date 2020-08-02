package fr.univangers.cours.gestionplanning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanningAdapter extends RecyclerView.Adapter<PlanningAdapter.EntryViewHolder> {
    private Context m_context;
    private ArrayList<EntryDescription> m_data;

    public PlanningDbHelper getDbHelper() {
        return mDbHelper;
    }

    private PlanningDbHelper mDbHelper;

    public PlanningAdapter(Context context) {

        m_context = context;
        m_data = new ArrayList<EntryDescription>();
        mDbHelper = new PlanningDbHelper(context);
    }

    public void add(EntryDescription entry) {
       SQLiteDatabase db =  mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlanningContract.PlanningEntry.COLUMN_NAME_PROF,entry.m_name_professeur);
        values.put(PlanningContract.PlanningEntry.COLUMN_NAME_COURS,entry.m_name_cours);
        values.put(PlanningContract.PlanningEntry.COLUMN_DATE,entry.m_date);
        db.insert(PlanningContract.PlanningEntry.TABLE_NAME,null,values);
        m_data.add(entry);
        notifyItemInserted(m_data.size() - 1);
    }

    public void remove(int id) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selection = PlanningContract.PlanningEntry.COLUMN_NAME_COURS +" = ?";
        String[] selectionArgs = {m_data.get(id).m_name_cours};
        System.out.println(m_data.get(id).m_name_cours+"\n");
        db.delete(PlanningContract.PlanningEntry.TABLE_NAME,
                selection,
                selectionArgs);
        m_data.remove(id);
        notifyItemRemoved(id);
        notifyItemRangeChanged(id,getItemCount() -id);
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(m_context).inflate(R.layout.ligne,parent,false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        EntryDescription slot = m_data.get(position);
        holder.tv_name_prof.setText(slot.m_name_professeur);
        holder.tv_date.setText(slot.m_date);
        holder.tv_name_cours.setText(slot.m_name_cours);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return m_data.size();
    }
    static class EntryViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_date;
        private TextView tv_name_prof;
        private TextView tv_name_cours;


        public EntryViewHolder(View item_view) {
            super(item_view);

            tv_date = item_view.findViewById(R.id.tv_date);
            tv_name_prof = item_view.findViewById(R.id.tv_professeur);
            tv_name_cours = item_view.findViewById(R.id.tv_cours);
        }
    }


    static public class EntryDescription {
        public String m_date;
        public String m_name_professeur;
        public String m_name_cours;

        public EntryDescription(String date, String name_professeur, String name_cours)
        {
           m_date = date;
            m_name_cours = name_cours;
            m_name_professeur = name_professeur;
        }
    }

    public void getUpdates()
    {
        m_data.clear();
        //lecture
       SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Projection utilisée pour la requête
        String [] projection = {
                BaseColumns._ID,
                PlanningContract.PlanningEntry.COLUMN_NAME_COURS,
                PlanningContract.PlanningEntry.COLUMN_DATE,
                PlanningContract.PlanningEntry.COLUMN_NAME_PROF,
        };

        Cursor cursor = db.query(
                PlanningContract.PlanningEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<EntryDescription> entries = new ArrayList<>();
        while (cursor.moveToNext()) {
            String nomCours = cursor.getString(1);
            String date = cursor.getString(2);
            String nomProf = cursor.getString(3);
            EntryDescription nouv = new EntryDescription(date,nomProf,nomCours);
            entries.add(nouv);
        }
        cursor.close();
        System.out.println("\n\n\n entries lenght "+entries.size()+"\n\n\n");
        m_data = entries;
        this.notifyDataSetChanged();
    }


    public void closeDb()
    {
        mDbHelper.close();
    }
}
