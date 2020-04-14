package com.project.ourspace.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.project.ourspace.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<Tile> tileList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create the observer which updates the UI.
        final Observer<List<Tile>> tileObserver = new Observer<List<Tile>>() {

            @Override
            public void onChanged(@Nullable final List<Tile> tileList) {
                // Update the UI, in this case, a TextView.
                //creating recyclerview adapter
                TileAdapter adapter = new TileAdapter(getActivity(), tileList);

                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

        };
        homeViewModel.getList().observe(getActivity(), tileObserver);


        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_group_white_24dp);


        return root;
    }
}
