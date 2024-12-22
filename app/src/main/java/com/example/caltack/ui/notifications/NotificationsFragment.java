package com.example.caltack.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.caltack.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private LinearLayout historyContainer;
    private ArrayList<String> historyList;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    public static NotificationsFragment newInstance(ArrayList<String> historyList) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("historyList", historyList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        historyContainer = root.findViewById(R.id.history_list_container);

        // Retrieve history list from arguments
        if (getArguments() != null) {
            historyList = getArguments().getStringArrayList("historyList");
        } else {
            historyList = new ArrayList<>();
        }

        // Display the calorie history
        displayHistory();

        return root;
    }

    private void displayHistory() {
        historyContainer.removeAllViews();  // Clear previous data if any
        if (historyList != null && !historyList.isEmpty()) {
            for (String record : historyList) {
                TextView historyItem = new TextView(getContext());
                historyItem.setText(record);
                historyItem.setTextSize(16f);
                historyItem.setPadding(0, 8, 0, 8);
                historyContainer.addView(historyItem);
            }
        } else {
            TextView emptyMessage = new TextView(getContext());
            emptyMessage.setText("No history available.");
            emptyMessage.setTextSize(16f);
            historyContainer.addView(emptyMessage);
        }
    }



}
