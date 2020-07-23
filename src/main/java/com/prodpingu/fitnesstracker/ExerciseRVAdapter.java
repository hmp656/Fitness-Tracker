package com.prodpingu.fitnesstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseRVAdapter extends RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder> {
    private List<ExerciseClass> filteredExercises;
    private Boolean searcher = false;

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseName;
        public TextView exerciseType;
        public TextView exerciseMainMuscle;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.ExerciseName);
            exerciseType = itemView.findViewById(R.id.ExerciseType);
            exerciseMainMuscle = itemView.findViewById(R.id.ExerciseMainMuscle);

        }
    }

    public ExerciseRVAdapter(ArrayList<ExerciseClass> exerciseClassArrayList) {
        filteredExercises = exerciseClassArrayList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row, parent, false);
        ExerciseViewHolder exerciseViewHolder = new ExerciseViewHolder(v);

        return exerciseViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseClass currentItem = filteredExercises.get(position);

        holder.exerciseName.setText(currentItem.getName());
        holder.exerciseType.setText(currentItem.getType());
        holder.exerciseMainMuscle.setText(currentItem.getMain_muscle());
    }


    @Override
    public int getItemCount() {
        return filteredExercises.size();
    }
}
