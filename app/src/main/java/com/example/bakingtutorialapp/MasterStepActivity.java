package com.example.bakingtutorialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bakingtutorialapp.Adapters.MasterStepAdapter;
import com.example.bakingtutorialapp.Fragments.DetailFragment;
import com.example.bakingtutorialapp.POJO.ReciepePOJO;
import com.example.bakingtutorialapp.POJO.StepsPOJO;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

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
        final ReciepePOJO reciepePOJO = bundle.getParcelable("reciepePOJOObj");
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
        RecyclerView recyclerView = findViewById(R.id.masterfragment_list);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        decoration.setDrawable(getDrawable(R.drawable.divider_for_list_views));
        recyclerView.addItemDecoration(decoration);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        TextView ingredients = findViewById(R.id.ingredients);
        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MasterStepActivity.this, IngredientsActivity.class);
                Bundle args = new Bundle();
                args.putParcelable("RecipePojoObject", reciepePOJO);
                intent.putExtra("RecipePojoObject", args);
                startActivity(intent);
            }
        });

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
