package com.example.caltack.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.caltack.databinding.FragmentDashboardBinding;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private TextInputEditText age, height, weight;
    private RadioGroup gender;
    private MaterialRadioButton male, female;
    private TextView calories, required, textView1, textView2, textView3, textView4, textView5, textView6, text_dummy;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout using binding
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views using binding
        age = binding.age;
        height = binding.height;
        weight = binding.weight;
        gender = binding.gender;
        male = binding.male;
        female = binding.female;
        calories = binding.calories;
        textView1 = binding.textView1;
        textView2 = binding.textView2;
        textView3 = binding.textView3;
        textView4 = binding.textView4;
        textView5 = binding.textView5;
        textView6 = binding.textView6;
        text_dummy = binding.textDummy;
        required = binding.required;

        // Initialize buttons
        binding.calculate.setOnClickListener(v -> calculateCalories());
        binding.reset.setOnClickListener(v -> resetFields());

        return root;
    }

    // Method to reset fields
    private void resetFields() {
        age.setText("");
        height.setText("");
        weight.setText("");
        gender.clearCheck();
        calories.setText("Calories");
        textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");
        textView5.setText("");
        textView6.setText("");
        text_dummy.setVisibility(View.GONE);
        required.setVisibility(View.GONE);
    }

    // Method to calculate calories
    private void calculateCalories() {
        String ageText = age.getText().toString();
        String heightText = height.getText().toString();
        String weightText = weight.getText().toString();
        Pattern pattern = Pattern.compile("[0-9]+");

        boolean ageCheck = validateInput(age, ageText, pattern);
        boolean heightCheck = validateInput(height, heightText, pattern);
        boolean weightCheck = validateInput(weight, weightText, pattern);

        if (gender.getCheckedRadioButtonId() == -1) {
            required.setText("Please Select Gender");
            required.setVisibility(View.VISIBLE);
        } else {
            required.setText("");
            required.setVisibility(View.GONE);

            if (ageCheck && heightCheck && weightCheck) {
                calculateCalorie();
            }
        }
    }

    // Method to validate input fields
    private boolean validateInput(TextInputEditText field, String text, Pattern pattern) {
        if (text.isEmpty()) {
            field.setError("Please enter a value");
            field.requestFocus();
            return false;
        } else if (!pattern.matcher(text).matches()) {
            field.setError("Please enter a valid number");
            field.requestFocus();
            return false;
        } else {
            field.setError(null);
            return true;
        }
    }

    // Method to perform calorie calculation
    private void calculateCalorie() {
        int ageValue = Integer.parseInt(age.getText().toString());
        int heightValue = Integer.parseInt(height.getText().toString());
        int weightValue = Integer.parseInt(weight.getText().toString());

        double totalCalories;

        if (gender.getCheckedRadioButtonId() == male.getId()) {
            totalCalories = (10 * weightValue) + (6.25 * heightValue) - (5 * ageValue + 5);
        } else {
            totalCalories = (10 * weightValue) + (6.25 * heightValue) - (5 * ageValue - 161);
        }

        // Display the calculated values
        calories.setText(String.format("%.2f", totalCalories) + "*");
        text_dummy.setVisibility(View.VISIBLE);
        textView1.setText(String.format("%.2f", totalCalories) + "*");
        textView2.setText(String.format("%.2f", totalCalories * 1.149) + "*");
        textView3.setText(String.format("%.2f", totalCalories * 1.220) + "*");
        textView4.setText(String.format("%.2f", totalCalories * 1.292) + "*");
        textView5.setText(String.format("%.2f", totalCalories * 1.437) + "*");
        textView6.setText(String.format("%.2f", totalCalories * 1.583) + "*");

        required.setText("* Calculation is based on the Mifflin-St Jeor Equation");
        required.setTextSize(12);
        required.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
