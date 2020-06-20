package com.example.bakingtutorialapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bakingtutorialapp.MasterStepActivity;
import com.example.bakingtutorialapp.POJO.ReciepePOJO;
import com.example.bakingtutorialapp.R;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainViewHolder>
{
    ArrayList<ReciepePOJO> reciepePOJOArrayList;
    Context context;

    public MainActivityAdapter(ArrayList<ReciepePOJO> reciepePOJOArrayList, Context context) {
        this.reciepePOJOArrayList = reciepePOJOArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_item_layout,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, final int position)
    {
        String reciepeName = reciepePOJOArrayList.get(position).getmName();
        holder.reciepeNameTv.setText(reciepeName);
        holder.reciepeNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasterStepActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("reciepePOJOObj",reciepePOJOArrayList.get(position));
                intent.putExtra("reciepePOJOObj",bundle);
                String s = reciepePOJOArrayList.get(position).getmName();
                Log.e("RECIEPE SENT WASS",s);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return reciepePOJOArrayList.size(); }

    public class MainViewHolder extends RecyclerView.ViewHolder
    {
        public TextView reciepeNameTv;
        public MainViewHolder(@NonNull View itemView)
        {
            super(itemView);
            reciepeNameTv = itemView.findViewById(R.id.mainReciepeName);
        }
    }
}
