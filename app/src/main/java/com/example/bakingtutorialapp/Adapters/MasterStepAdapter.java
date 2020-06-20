package com.example.bakingtutorialapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingtutorialapp.Fragments.DetailFragment;
import com.example.bakingtutorialapp.MasterDetailActivity;
import com.example.bakingtutorialapp.MasterStepActivity;
import com.example.bakingtutorialapp.POJO.StepsPOJO;
import com.example.bakingtutorialapp.R;

import java.util.ArrayList;

public class MasterStepAdapter extends RecyclerView.Adapter<MasterStepAdapter.MasterStepViewHolder> {
    private MasterStepActivity masterStepActivity;
    private ArrayList<StepsPOJO> stepsPOJOS;
    private boolean twoPane;

    public MasterStepAdapter(MasterStepActivity masterStepActivity, ArrayList<StepsPOJO> stepsPOJOS, boolean twoPane) {
        this.masterStepActivity = masterStepActivity;
        this.stepsPOJOS = stepsPOJOS;
        this.twoPane = twoPane;
    }

    @NonNull
    @Override
    public MasterStepAdapter.MasterStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.steps_item_layout, parent, false);
        return new MasterStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasterStepAdapter.MasterStepViewHolder holder, final int position) {
        holder.shortTextView.setText(stepsPOJOS.get(position).getmShortDescription());
        holder.longTextView.setText(stepsPOJOS.get(position).getmLongDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MasterStepAdapter", String.valueOf(stepsPOJOS.size()));
                if (twoPane) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("stepList", stepsPOJOS);
                    bundle.putInt("position", position);
                    DetailFragment masterDetailFragment = new DetailFragment();
                    masterDetailFragment.setArguments(bundle);
                    masterStepActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.masterfragment_detail_container, masterDetailFragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MasterDetailActivity.class);
                    intent.putParcelableArrayListExtra("stepList", stepsPOJOS);
                    intent.putExtra("position", position);
                    String s = stepsPOJOS.get(position).getmShortDescription();
                    Log.e("STEP SENT NEXT IS", s);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepsPOJOS.size();
    }


    public class MasterStepViewHolder extends RecyclerView.ViewHolder {
        final TextView shortTextView;
        final TextView longTextView;

        MasterStepViewHolder(View view) {
            super(view);
            shortTextView = view.findViewById(R.id.shortStepView);
            longTextView = view.findViewById(R.id.longStepView);
        }
    }
}
