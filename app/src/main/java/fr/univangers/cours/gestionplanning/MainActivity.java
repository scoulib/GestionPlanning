package fr.univangers.cours.gestionplanning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PlanningAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.add_cours:
                Intent addCoursIntent = new Intent(MainActivity.this, AddCoursActivity.class);
                startActivity(addCoursIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_planning);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new PlanningAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.getUpdates();

        ItemTouchHelper.SimpleCallback item_touch_helper_callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = (int) viewHolder.itemView.getTag();
                adapter.remove(position);

            }
        };

        new ItemTouchHelper(item_touch_helper_callback).attachToRecyclerView(mRecyclerView);




        /*//Création de la liste de valeur
        ContentValues values = new ContentValues();
        values.put(PlanningContract.Personnes.COLUMN_FIRST_NAME,"Abdou Karim");
        values.put(PlanningContract.Personnes.COLUMN_LAST_NAME,"COULIBALY");

        //Insertion d'un n-uplet, renvoie la valeur de clef primaire
        long newRowId = db.insert(PlanningContract.Personnes.TABLE_NAME,null,values);*/

      /*  // Filtrer les résultats  WHERE "nom" = 'Dupont'
        String selection = PlanningContract.Personnes.COLUMN_LAST_NAME+" = ?";
        String[] selectionArgs = {"COULIBALY"};

        //Trier les résultats
        String sortOrder = PlanningContract.Personnes.COLUMN_FIRST_NAME+" DESC";

        Cursor cursor = db.query(
                PlanningContract.Personnes.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
*/

    }
    public void addEntry(PlanningAdapter.EntryDescription entry) {
        adapter.add(entry);
    }
    @Override
    protected void onDestroy() {
        adapter.closeDb();
        super.onDestroy();
    }



}
