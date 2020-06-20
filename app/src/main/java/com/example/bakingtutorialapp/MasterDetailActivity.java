package com.example.bakingtutorialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bakingtutorialapp.Fragments.DetailFragment;
import com.example.bakingtutorialapp.POJO.StepsPOJO;

import java.util.ArrayList;

public class MasterDetailActivity extends AppCompatActivity {

    ArrayList<StepsPOJO> stepsPOJOArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);

        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList("stepList", getIntent().getParcelableArrayListExtra("stepList"));
        arguments.putInt("position", getIntent().getIntExtra("position",0));
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.masterfragment_detail_container, fragment)
                .commit();

    }
}