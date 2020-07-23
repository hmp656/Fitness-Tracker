package com.prodpingu.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;


// Activity
public class ExerciseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        ArrayList<ExerciseClass> exerciseClassArrayList = new ArrayList<>();

        Tracker tracker = new Tracker(65, 65, 0);

        // get all exercises from the exercises.json resource file
        try {
            exerciseClassArrayList = initializeArrayList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // initialize recycler view and adapters
        recyclerView = findViewById(R.id.ExerciseRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ExerciseRVAdapter(exerciseClassArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        // end init
    }


    // get the array of exercises from the json file
    public ArrayList<ExerciseClass> initializeArrayList() throws JSONException {
        ArrayList<ExerciseClass> exerciseClassArrayList = new ArrayList<>();

        // open json
        InputStream inputStream = getResources().openRawResource(R.raw.exercises);
        // read input stream and convert into a string
        String jsonString = readTextFile(inputStream);

        //convert string to a json object
        JSONObject jsonObject = new JSONObject(jsonString);
        // get the exercise array from the object
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject exercise = jsonArray.getJSONObject(i);
            ArrayList<String> muscles = new ArrayList<>();
            muscles.add(exercise.getString("muscles"));

            // adding classes to the list
            exerciseClassArrayList.add(new ExerciseClass(exercise.getString("name"),
                    exercise.getString("type"),
                    exercise.getString("main_muscle"),
                    muscles));
        }
        return exerciseClassArrayList;
    }

    // for once, thank you medium
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buff = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            return null;
        }
        return outputStream.toString();
    }
}