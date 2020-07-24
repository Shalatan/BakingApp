package com.example.bakingtutorialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakingtutorialapp.Adapters.IngredientsAdapter;
import com.example.bakingtutorialapp.POJO.IngredientsPOJO;
import com.example.bakingtutorialapp.POJO.ReciepePOJO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity
{
    ArrayList<IngredientsPOJO> ingredientsPOJOArrayList;
    public static String widgetText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("RecipePojoObject");
        ReciepePOJO recipePOJO = args.getParcelable("RecipePojoObject");
        ingredientsPOJOArrayList = recipePOJO.getmIngredients();
        ListView listView=findViewById(R.id.ingredients_recycler_view);
        IngredientsAdapter ingredientsAdapter=new IngredientsAdapter(this,ingredientsPOJOArrayList);
        listView.setAdapter(ingredientsAdapter);

        FloatingActionButton setWidget=findViewById(R.id.addWidget);
        setWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widgetText="";
                for(int i=0;i<ingredientsPOJOArrayList.size();i++){
                    widgetText+=(i+1)+(".")+ingredientsPOJOArrayList.get(i).getmIngredient()+" -"
                            +ingredientsPOJOArrayList.get(i).getmQuantity()
                            +ingredientsPOJOArrayList.get(i).getmMeasure();
                    widgetText+="\n ";
                }
                Toast.makeText(IngredientsActivity.this,"Added Ingredients to Widget", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IngredientsActivity.this, ReciepeIngredientWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(),ReciepeIngredientWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                sendBroadcast(intent);

            }
        });


    }
}