package com.example.bakingtutorialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bakingtutorialapp.Adapters.MasterStepAdapter;
import com.example.bakingtutorialapp.Fragments.DetailFragment;
import com.example.bakingtutorialapp.POJO.ReciepePOJO;
import com.example.bakingtutorialapp.POJO.StepsPOJO;

import java.util.ArrayList;

public class MasterStepActivity extends AppCompatActivity {

    boolean twoPane;
    ArrayList<StepsPOJO> stepsPOJOArrayList;
    RecyclerView recyclerView;
    MasterStepAdapter masterStepAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_step);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("reciepePOJOObj");
        ReciepePOJO reciepePOJO = bundle.getParcelable("reciepePOJOObj");
        stepsPOJOArrayList = reciepePOJO.getmSteps();
        String s = reciepePOJO.getmName();
        Log.e("RECIPE RECIEVERDDD IS", s);

        if (findViewById(R.id.masterfragment_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true;
        }
        View recyclerView = findViewById(R.id.masterfragment_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (twoPane) {
            setUpFirstStep();
        }
    }

    private void setUpFirstStep() {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList("stepList", stepsPOJOArrayList);
        arguments.putInt("position", 0);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.masterfragment_detail_container, fragment)
                .commit();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new MasterStepAdapter(this, stepsPOJOArrayList, twoPane));
    }
}
