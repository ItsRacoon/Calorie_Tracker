package com.example.caltack.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.caltack.R;
import com.example.caltack.databinding.FragmentHomeBinding;
import com.example.caltack.ui.notifications.NotificationsFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AutoCompleteTextView foodSearch;
    private Spinner quantitySpinner;
    private LinearLayout itemListContainer;
    private TextView totalCalories;
    private HashMap<String, Integer> calorieData;
    private HashMap<String, String> foodUnits;
    private int totalCalorieCount = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize UI components
        foodSearch = binding.foodSearch;
        quantitySpinner = binding.quantitySpinner;
        itemListContainer = binding.itemListContainer;
        totalCalories = binding.totalCalories;
        Button addItemButton = binding.addItemButton;
        Button calculateButton = binding.calculateCaloriesButton;
        Button resetButton = binding.resetButton;

        // Initialize calorie data
        calorieData = new HashMap<>();
        foodUnits = new HashMap<>();
        populateCalorieData();

        // Set up AutoCompleteTextView with calorie data
        ArrayAdapter<String> foodAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_dropdown_item_1line, new ArrayList<>(calorieData.keySet()));
        foodSearch.setAdapter(foodAdapter);

        // Add item button functionality
        addItemButton.setOnClickListener(v -> addItemToList());

        // Calculate button functionality
        calculateButton.setOnClickListener(v -> calculateTotalCalories());

        // Reset button functionality
        resetButton.setOnClickListener(v -> resetTracker());

        // Update spinner dynamically based on selected food item
        foodSearch.setOnItemClickListener((parent, view, position, id) -> updateQuantitySpinner());

        return root;
    }

    private void populateCalorieData() {
        calorieData.put("Bottle gourd", 11);
        foodUnits.put("Bottle gourd", "100 gm");

        calorieData.put("Ridge gourd", 13);
        foodUnits.put("Ridge gourd", "100 gm");

        calorieData.put("Bitter gourd", 21);
        foodUnits.put("Bitter gourd", "100 gm");

        calorieData.put("Capsicum", 16);
        foodUnits.put("Capsicum", "100 gm");

        calorieData.put("Fenugreek leaves (cooked)", 34);
        foodUnits.put("Fenugreek leaves (cooked)", "100 gm");

        calorieData.put("Radish leaves", 26);
        foodUnits.put("Radish leaves", "100 gm");

        calorieData.put("Spinach", 24);
        foodUnits.put("Spinach", "100 gm");

        calorieData.put("Pumpkin", 23);
        foodUnits.put("Pumpkin", "100 gm");

        calorieData.put("Zucchini", 20);
        foodUnits.put("Zucchini", "100 gm");

        calorieData.put("Drumsticks", 67);
        foodUnits.put("Drumsticks", "100 gm");

        calorieData.put("Tomato", 21);
        foodUnits.put("Tomato", "100 gm");

        calorieData.put("Sprouts", 44);
        foodUnits.put("Sprouts", "100 gm");

        calorieData.put("French beans", 24);
        foodUnits.put("French beans", "100 gm");

        calorieData.put("Kidney beans", 337);
        foodUnits.put("Kidney beans", "cup");

        calorieData.put("Soya beans", 446);
        foodUnits.put("Soya beans", "cup");

        calorieData.put("Beans", 40);
        foodUnits.put("Beans", "cup");

        calorieData.put("Peas", 118);
        foodUnits.put("Peas", "cup");

        calorieData.put("Lady’s finger", 150);
        foodUnits.put("Lady’s finger", "cup");

        calorieData.put("Cabbage", 60);
        foodUnits.put("Cabbage", "cup");

        calorieData.put("Cauliflower", 150);
        foodUnits.put("Cauliflower", "cup");

        calorieData.put("Broccoli", 40);
        foodUnits.put("Broccoli", "cup");

        calorieData.put("Brinjal", 40);
        foodUnits.put("Brinjal", "cup");

        calorieData.put("Cottage cheese", 258);
        foodUnits.put("Cottage cheese", "100 gm");

        calorieData.put("Palak paneer", 280);
        foodUnits.put("Palak paneer", "cup");

        calorieData.put("Fried potato", 200);
        foodUnits.put("Fried potato", "cup");

        calorieData.put("Mashed potatoes", 100);
        foodUnits.put("Mashed potatoes", "cup");

        calorieData.put("Sweet potato", 96);
        foodUnits.put("Sweet potato", "cup");

        calorieData.put("Mushrooms", 296);
        foodUnits.put("Mushrooms", "cup");

        calorieData.put("Mixed Veggies", 80);
        foodUnits.put("Mixed Veggies", "cup");

        calorieData.put("Vegetable Curry", 130);
        foodUnits.put("Vegetable Curry", "cup");

        calorieData.put("Small guava", 32);
        foodUnits.put("Small guava", "piece");

        calorieData.put("Small orange", 37);
        foodUnits.put("Small orange", "piece");

        calorieData.put("Small pear", 37);
        foodUnits.put("Small pear", "piece");

        calorieData.put("Small mango", 42);
        foodUnits.put("Small mango", "piece");

        calorieData.put("Small apple", 62);
        foodUnits.put("Small apple", "piece");

        calorieData.put("Small peach", 40);
        foodUnits.put("Small peach", "piece");

        calorieData.put("Sweet lime", 27);
        foodUnits.put("Sweet lime", "piece");

        calorieData.put("Banana", 110);
        foodUnits.put("Banana", "piece");

        calorieData.put("Watermelon", 20);
        foodUnits.put("Watermelon", "bowl");

        calorieData.put("Litchis", 53);
        foodUnits.put("Litchis", "bowl");

        calorieData.put("Melon", 23);
        foodUnits.put("Melon", "bowl");

        calorieData.put("Strawberries", 25);
        foodUnits.put("Strawberries", "bowl");

        calorieData.put("Pomegranate", 55);
        foodUnits.put("Pomegranate", "bowl");

        calorieData.put("Cherries", 60);
        foodUnits.put("Cherries", "bowl");

        calorieData.put("Grapes", 60);
        foodUnits.put("Grapes", "bowl");

        calorieData.put("Bread", 45);
        foodUnits.put("Bread", "piece");

        calorieData.put("Small poori", 75);
        foodUnits.put("Small poori", "piece");

        calorieData.put("Roti", 100);
        foodUnits.put("Roti", "piece");

        calorieData.put("Parantha", 150);
        foodUnits.put("Parantha", "piece");

        calorieData.put("Aloo parantha", 170);
        foodUnits.put("Aloo parantha", "piece");

        calorieData.put("Pav", 180);
        foodUnits.put("Pav", "piece");

        calorieData.put("Naan", 262);
        foodUnits.put("Naan", "piece");

        calorieData.put("Butter Naan", 310);
        foodUnits.put("Butter Naan", "piece");

        calorieData.put("Water", 0);
        foodUnits.put("Water", "glass");

        calorieData.put("Green tea", 10);
        foodUnits.put("Green tea", "cup");

        calorieData.put("Black tea", 10);
        foodUnits.put("Black tea", "cup");

        calorieData.put("Milk Tea", 45);
        foodUnits.put("Milk Tea", "cup");

        calorieData.put("Cappuccino", 45);
        foodUnits.put("Cappuccino", "cup");

        calorieData.put("Plain milk", 60);
        foodUnits.put("Plain milk", "cup");

        calorieData.put("Milk with added flavours", 120);
        foodUnits.put("Milk with added flavours", "cup");

        calorieData.put("Fruit juice", 120);
        foodUnits.put("Fruit juice", "cup");

        calorieData.put("Tender coconut", 15);
        foodUnits.put("Tender coconut", "cup");

        calorieData.put("Soup", 75);
        foodUnits.put("Soup", "bowl");

        calorieData.put("Soda", 10);
        foodUnits.put("Soda", "bottle");

        calorieData.put("Cold drink", 90);
        foodUnits.put("Cold drink", "bottle");

        calorieData.put("Milkshake", 200);
        foodUnits.put("Milkshake", "bottle");

        calorieData.put("Beer", 200);
        foodUnits.put("Beer", "bottle");

        calorieData.put("Alcohol", 75);
        foodUnits.put("Alcohol", "serving");

        calorieData.put("Papad", 45);
        foodUnits.put("Papad", "piece");

        calorieData.put("Idli", 100);
        foodUnits.put("Idli", "piece");

        calorieData.put("Plain dosa", 120);
        foodUnits.put("Plain dosa", "piece");

        calorieData.put("Masala dosa", 250);
        foodUnits.put("Masala dosa", "piece");

        calorieData.put("Vermicelli", 333);
        foodUnits.put("Vermicelli", "100 gm");

        calorieData.put("Ragi", 320);
        foodUnits.put("Ragi", "100 gm");

        calorieData.put("Quinoa", 328);
        foodUnits.put("Quinoa", "100 gm");

        calorieData.put("Tofu", 76);
        foodUnits.put("Tofu", "100 gm");

        calorieData.put("Raita", 20);
        foodUnits.put("Raita", "serving");

        calorieData.put("Sugar", 30);
        foodUnits.put("Sugar", "spoon");

        calorieData.put("Pickles", 30);
        foodUnits.put("Pickles", "spoon");

        calorieData.put("Salad", 100);
        foodUnits.put("Salad", "plate");

        calorieData.put("Vegetable Rice", 200);
        foodUnits.put("Vegetable Rice", "plate");

        calorieData.put("Boiled Rice", 120);
        foodUnits.put("Boiled Rice", "cup");

        calorieData.put("Fried Rice", 150);
        foodUnits.put("Fried Rice", "cup");

        calorieData.put("Cereal or oats with milk", 150);
        foodUnits.put("Cereal or oats with milk", "cup");

        calorieData.put("Sambar", 150);
        foodUnits.put("Sambar", "cup");

        calorieData.put("Curd", 100);
        foodUnits.put("Curd", "cup");

        calorieData.put("Any lentils or dhal", 150);
        foodUnits.put("Any lentils or dhal", "cup");

        calorieData.put("Coconut", 409);
        foodUnits.put("Coconut", "100 gm");

        calorieData.put("Fried Nuts", 450);
        foodUnits.put("Fried Nuts", "cup");

        calorieData.put("Peanut", 520);
        foodUnits.put("Peanut", "500 gm");

        calorieData.put("Flax seed", 534);
        foodUnits.put("Flax seed", "500 gm");

        calorieData.put("Pistachio", 539);
        foodUnits.put("Pistachio", "100 gm");

        calorieData.put("Cashewnut", 582);
        foodUnits.put("Cashewnut", "100 gm");

        calorieData.put("Almond", 602);
        foodUnits.put("Almond", "100 gm");

        calorieData.put("Pakodas", 175);
        foodUnits.put("Pakodas", "50 gm");

        calorieData.put("Vada", 70);
        foodUnits.put("Vada", "piece");

        calorieData.put("Samosa", 140);
        foodUnits.put("Samosa", "piece");

        calorieData.put("Sandwich", 250);
        foodUnits.put("Sandwich", "piece");

        calorieData.put("Burger", 250);
        foodUnits.put("Burger", "piece");

        calorieData.put("Biscuit", 30);
        foodUnits.put("Biscuit", "piece");

        calorieData.put("Chips", 120);
        foodUnits.put("Chips", "packet");

        calorieData.put("French Fries", 427);
        foodUnits.put("French Fries", "serving");

        calorieData.put("Bhel or Pani puri", 150);
        foodUnits.put("Bhel or Pani puri", "serving");

        calorieData.put("Pav Bhaji", 610);
        foodUnits.put("Pav Bhaji", "plate");

        calorieData.put("Indian sweets", 150);
        foodUnits.put("Indian sweets", "piece");

        calorieData.put("Pudding", 200);
        foodUnits.put("Pudding", "cup");

        calorieData.put("Jam", 30);
        foodUnits.put("Jam", "spoon");

        calorieData.put("Falooda", 300);
        foodUnits.put("Falooda", "glass");

        calorieData.put("Cream", 105);
        foodUnits.put("Cream", "50 gm");

        calorieData.put("Cheese", 155);
        foodUnits.put("Cheese", "50 gm");

        calorieData.put("Butter", 45);
        foodUnits.put("Butter", "tablespoon");

        calorieData.put("Ghee", 45);
        foodUnits.put("Ghee", "tablespoon");

        calorieData.put("Whole milk", 150);
        foodUnits.put("Whole milk", "cup");

        calorieData.put("Egg", 75);
        foodUnits.put("Egg", "piece");

        calorieData.put("Boiled egg", 80);
        foodUnits.put("Boiled egg", "piece");

        calorieData.put("Scrambled egg", 80);
        foodUnits.put("Scrambled egg", "piece");

        calorieData.put("Fried egg", 110);
        foodUnits.put("Fried egg", "piece");

        calorieData.put("Omelette", 120);
        foodUnits.put("Omelette", "piece");

        calorieData.put("Meat", 450);
        foodUnits.put("Meat", "plate");

        calorieData.put("Mutton biryani", 225);
        foodUnits.put("Mutton biryani", "plate");

        calorieData.put("Fried chicken", 200);
        foodUnits.put("Fried chicken", "serving");

        calorieData.put("Chicken curry", 225);
        foodUnits.put("Chicken curry", "serving");

        calorieData.put("Tandoori Chicken", 260);
        foodUnits.put("Tandoori Chicken", "serving");

        calorieData.put("Butter chicken", 490);
        foodUnits.put("Butter chicken", "serving");

        calorieData.put("Chicken tikka masala", 457);
        foodUnits.put("Chicken tikka masala", "serving");

        calorieData.put("Fried fish", 140);
        foodUnits.put("Fried fish", "serving");

        calorieData.put("Salmon", 172);
        foodUnits.put("Salmon", "100 gm");

        calorieData.put("Pomfret", 123);
        foodUnits.put("Pomfret", "100 gm");

        calorieData.put("Squid", 80);
        foodUnits.put("Squid", "100 gm");

        calorieData.put("Crab", 81);
        foodUnits.put("Crab", "100 gm");

        calorieData.put("Prawn", 65);
        foodUnits.put("Prawn", "100 gm");

        calorieData.put("Cooked chicken", 200);
        foodUnits.put("Cooked chicken", "100 gm");

    }

    private void updateQuantitySpinner() {
        String foodName = foodSearch.getText().toString().trim();
        if (foodUnits.containsKey(foodName)) {
            String unit = foodUnits.get(foodName);


            // Create quantity options dynamically, e.g., "1 piece", "2 pieces", etc.
            ArrayList<String> quantities = new ArrayList<>();
            quantities.add("Enter Quantity");
            for (int i = 1; i <= 10; i++) {  // Increased range to 10 for more flexibility
                quantities.add(i + " " + unit);
            }

            // Update spinner with the new quantity options
            ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(
                    getContext(), android.R.layout.simple_spinner_item, quantities);
            quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            quantitySpinner.setAdapter(quantityAdapter);
            quantitySpinner.setSelection(0);
        } else {
            Toast.makeText(getContext(), "No unit available for this food item", Toast.LENGTH_SHORT).show();
        }
    }



    private void addItemToList() {
        String foodName = foodSearch.getText().toString().trim();
        String quantityString = quantitySpinner.getSelectedItem().toString();

        if (calorieData.containsKey(foodName)) {
            int baseCalories = calorieData.get(foodName);
            int quantity = Integer.parseInt(quantityString.split(" ")[0]); // Extract the quantity number
            int totalItemCalories = baseCalories * quantity;
            totalCalorieCount += totalItemCalories;

            calorieHistory.add(foodName + " - " + totalItemCalories + " Calories (" + quantityString + ")");


            // Create a horizontal layout for each food item with a remove button
            LinearLayout itemLayout = new LinearLayout(getContext());
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            // TextView for food item details
            TextView itemTextView = new TextView(getContext());
            itemTextView.setText(foodName + " - " + totalItemCalories + " Calories (" + quantityString + ")");
            itemTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            // Cross button to remove the item
            ImageButton removeButton = new ImageButton(getContext());
            removeButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            removeButton.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            removeButton.setOnClickListener(v -> {
                totalCalorieCount -= totalItemCalories;
                itemListContainer.removeView(itemLayout);
                totalCalories.setText("Total Calories: " + totalCalorieCount);
            });

            // Add TextView and Button to the layout
            itemLayout.addView(itemTextView);
            itemLayout.addView(removeButton);

            // Add the layout to the container
            itemListContainer.addView(itemLayout);

            // Clear the search field
            foodSearch.setText("");
        } else {
            Toast.makeText(getContext(), "Food item not found!", Toast.LENGTH_SHORT).show();
        }
    }



    private void updateNotificationsFragment() {
        // Pass the updated history list to the NotificationsFragment
        NotificationsFragment fragment = NotificationsFragment.newInstance(calorieHistory);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.history_list_container, fragment)
                .addToBackStack(null)
                .commit();
    }





    private void resetTracker() {
        itemListContainer.removeAllViews();
        totalCalories.setText("Total Calories: 0");
        totalCalorieCount = 0;
        foodSearch.setText("");
    }

    private ArrayList<String> calorieHistory = new ArrayList<>();

    private void calculateTotalCalories() {
        totalCalories.setText("Total Calories: " + totalCalorieCount);
        calorieHistory.add("Total Calories: " + totalCalorieCount);
    }

    private void navigateToNotificationsFragment() {
        NotificationsFragment fragment = NotificationsFragment.newInstance(calorieHistory);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.history_list_container, fragment)  // Pass fragment directly
                .addToBackStack(null)
                .commit();
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
