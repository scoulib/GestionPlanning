package fr.univangers.cours.gestionplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Date;

public class AddCoursActivity extends AppCompatActivity {
    private CalendarView m_calendar_view;
    private EditText mEditText_matiere;
    private EditText mEditText_professeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cours);

        m_calendar_view = (CalendarView)findViewById(R.id.cv_date);
        mEditText_matiere = (EditText)findViewById(R.id.nom_cours);
        mEditText_professeur = (EditText)findViewById(R.id.nom_prof);


    }

    public  void ajouter(View view) {
        m_calendar_view = (CalendarView)findViewById(R.id.cv_date);
        mEditText_matiere = (EditText)findViewById(R.id.nom_cours);
        mEditText_professeur = (EditText)findViewById(R.id.nom_prof);

        PlanningAdapter.EntryDescription nouv = new PlanningAdapter.EntryDescription(new Date(m_calendar_view.getDate()).toString(),mEditText_professeur.getText().toString(),mEditText_matiere.getText().toString());
        ((MainActivity)getParent()).addEntry(nouv);
    }
}
