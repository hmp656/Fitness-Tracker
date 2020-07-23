package com.prodpingu.fitnesstracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CaloricReqActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String activity_level;
    String gender;

    String[] heightValues;
    String[] weightValues;

    TextView resultsTV;

    NumberPicker weightPickerUI;
    NumberPicker heightPickerUI;

    EditText ageEnter;

    Spinner Gender_Spinner;
    Spinner Activity_Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloric_req);

        SharedPreferences sharedPreferences = getSharedPreferences("Values", Context.MODE_PRIVATE);
        sharedPreferences.getFloat("Height", 10);
        sharedPreferences.getFloat("Weight", 10);

        resultsTV = findViewById(R.id.CalValue);

        Gender_Spinner = findViewById(R.id.Gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array,
                R.layout.gender_spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender_Spinner.setAdapter(adapter);
        Gender_Spinner.setOnItemSelectedListener(this);


        Activity_Spinner = findViewById(R.id.Activity_Level);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.activity_level_array,
                R.layout.gender_spinner);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Activity_Spinner.setAdapter(adapter2);
        Activity_Spinner.setOnItemSelectedListener(this);


        weightPickerUI = findViewById(R.id.WeightPicker);
        heightPickerUI = findViewById(R.id.HeightPicker);
        ageEnter = findViewById(R.id.age_text_enter);

        weightValues = arrayinitMethod(10, 150);
        heightValues = arrayinitMethod(10, 220);

        setNumberPicker(weightPickerUI, weightValues);
        setNumberPicker(heightPickerUI, heightValues);

        heightPickerUI.setValue((int) sharedPreferences.getFloat("Height", 10));
        weightPickerUI.setValue((int) sharedPreferences.getFloat("Weight", 10));
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void CaloricReqCalc(View view) {
        try {
            float age = (float) Integer.parseInt(ageEnter.getText().toString());
            float height = (float) heightPickerUI.getValue();
            float weight = (float) weightPickerUI.getValue();

            height += 10;
            weight += 10;

            float BMIValueH = (height * height) / 10000;
            float BMIValue = weight / BMIValueH;
            float BMRValue = 0;

            float activity_multiplier = 0;
            switch (activity_level) {
                case "Sedentary":
                    activity_multiplier = (float) 1.2;
                    break;
                case "Lightly Active":
                    activity_multiplier = (float) 1.375;
                    break;
                case "Moderately Active":
                    activity_multiplier = (float) 1.55;
                    break;
                case "Very Active":
                    activity_multiplier = (float) 1.725;
                    break;
                case "Extra Active":
                    activity_multiplier = (float) 1.9;
                    break;
            }

            if (!gender.equals("Choose")) {
                if (gender.equals("Male")) {
                    BMRValue = (float) (10 * weight + 6.25 * height + 5 * age + 5);
                } else if (gender.equals("Female")) {
                    BMRValue = (float) (10 * weight + 6.25 * height + 5 * age - 161);
                }

                float Caloric_Requirement = BMRValue * activity_multiplier;
                resultsTV.setText(String.format("Your BMI is %.2f kg/m2 and your daily energy expenditure is %.2f calories", BMIValue, Caloric_Requirement));
            } else {
                resultsTV.setText("Select a gender please.");
            }
        } catch (IllegalStateException e) {
            resultsTV.setText("Fill out all fields.");
        }
    }

    //    Spinner select begins
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.Gender:
                gender = (String) parent.getItemAtPosition(position);
                break;
            case R.id.Activity_Level:
                activity_level = (String) parent.getItemAtPosition(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.e("test", "Nothing");
    }
//    Spinner select ends


    public String[] arrayinitMethod(int minVal, int maxVal) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = minVal; i <= maxVal; i++) {
            arrayList.add(String.valueOf(i));
        }

        return arrayList.toArray(new String[arrayList.size()]);
    }

    private void setNumberPicker(NumberPicker numberPicker, String[] numbers) {
        numberPicker.setMaxValue(numbers.length - 1);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setDisplayedValues(numbers);
    }
}

