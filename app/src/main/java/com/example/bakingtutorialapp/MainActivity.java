package com.example.bakingtutorialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.bakingtutorialapp.Adapters.MainActivityAdapter;
import com.example.bakingtutorialapp.POJO.ReciepePOJO;
import com.example.bakingtutorialapp.Utils.JsonUtils;
import com.example.bakingtutorialapp.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {

    MainActivityAsyncClass mainActivityAsyncClass;
    RecyclerView recyclerView;
    MainActivityAdapter mainActivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ReciepePOJO> reciepePOJOArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainActivityAdapter = new MainActivityAdapter(reciepePOJOArrayList,MainActivity.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(mainActivityAdapter);

        mainActivityAsyncClass = new MainActivityAsyncClass();
        String NETWORK_STATUS = getNetworkStatus();
        if (NETWORK_STATUS == getString(R.string.text_connected))
        {
            mainActivityAsyncClass.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        }
        else
        {
            Log.e("MAINACTIVITY","NETWORK NOT CONNECTED");
        }

    }
    private String getNetworkStatus()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return "CONNECTED";
        }
        else {
            return "NOT_CONNECTED";
        }
    }
    public class MainActivityAsyncClass extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {
            String jsonResult = NetworkUtils.fetchJsonString(strings[0]);
            return jsonResult;
        }

        @Override
        protected void onPostExecute(String s)
        {
            ArrayList<ReciepePOJO> recipeArrayList = JsonUtils.fetchFeaturesFromJson(s);
            for (int k = 0; k < recipeArrayList.size(); k++) {
                for (int m = 0; m < recipeArrayList.get(k).getmIngredients().size(); m++) {

                }
            }
            mainActivityAdapter = new MainActivityAdapter(recipeArrayList,MainActivity.this);
            recyclerView.setAdapter(mainActivityAdapter);
            super.onPostExecute(s);
        }
    }
}
