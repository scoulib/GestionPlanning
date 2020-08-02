package fr.univangers.cours.gestionplanning;

import android.provider.BaseColumns;

public final class PlanningContract {

    private PlanningContract() {

    }

    public static class PlanningEntry implements BaseColumns {
        public static final String TABLE_NAME = "planningCours";
        public static final String COLUMN_NAME_PROF = "professeur";
        public static final String COLUMN_DATE = "dateCours";
        public static final String COLUMN_NAME_COURS = "intituleCours";

    }
}
