package com.example.bakingtutorialapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.bakingtutorialapp.POJO.IngredientsPOJO;
import com.example.bakingtutorialapp.R;
import java.util.ArrayList;

public class IngredientsAdapter extends ArrayAdapter<IngredientsPOJO>
{
    ArrayList<IngredientsPOJO> ingredientsPOJOArrayList;

    public IngredientsAdapter(Context context, ArrayList<IngredientsPOJO> ingredientsPOJOArrayList)
    {
        super(context,0,ingredientsPOJOArrayList);
        this.ingredientsPOJOArrayList=ingredientsPOJOArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredients_item_layout,parent,false);
        }
        TextView ingredientName = convertView.findViewById(R.id.ingredients_ingredient_name);
        TextView ingredientQuantity = convertView.findViewById(R.id.ingredients_ingredient_quantity);
        TextView ingredientMeasure = convertView.findViewById(R.id.ingredients_ingredient_measure);

        ingredientName.setText(ingredientsPOJOArrayList.get(position).getmIngredient());
        ingredientQuantity.setText(ingredientsPOJOArrayList.get(position).getmQuantity());
        ingredientMeasure.setText(ingredientsPOJOArrayList.get(position).getmMeasure());

        return convertView;
    }
}
