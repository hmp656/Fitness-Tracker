package com.prodpingu.fitnesstracker;

import java.util.ArrayList;

public class ExerciseClass {
    private String name;
    private String type; // Push, pull or hold
    private String main_muscle;
    private ArrayList<String> muscles;

    ExerciseClass(String name, String type, String main_muscle, ArrayList<String> muscles) {
        this.name = name;
        this.type = type;
        this.main_muscle = main_muscle;
        this.muscles = muscles;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMain_muscle() {
        return main_muscle;
    }

    public ArrayList<String> getMuscles() {
        return muscles;
    }
}
