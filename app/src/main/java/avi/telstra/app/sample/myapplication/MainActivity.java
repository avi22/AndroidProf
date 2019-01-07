package avi.telstra.app.sample.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import avi.telstra.app.sample.myapplication.adapterpackage.CustomAdapter;
import avi.telstra.app.sample.myapplication.datamodel.Rows;
import avi.telstra.app.sample.myapplication.interfaces.GetDataInterface;
import  avi.telstra.app.sample.myapplication.asynctaskpackage.PraseJson;
import avi.telstra.app.sample.myapplication.util.Conditionclass;


public class MainActivity extends AppCompatActivity implements  GetDataInterface {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    PraseJson paPraseJson ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init SwipeRefreshLayout and ListView
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        // get the reference of RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if (Conditionclass.isNetworkAvailable(this)) {
             paPraseJson = new PraseJson(this,
                    "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.js");
            paPraseJson.init(this);
            paPraseJson.execute();
        }
        else
        {
            Toast.makeText(this , "There is no network available", Toast.LENGTH_LONG).show();
        }
        // call the constructor of CustomAdapter to send the reference and data to Adapter

        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh


                if (Conditionclass.isNetworkAvailable(MainActivity.this)) {

                    PraseJson praseJsonrefresh = new PraseJson(MainActivity.this,
                            "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.js");
                    praseJsonrefresh.init(MainActivity.this);
                    praseJsonrefresh.execute();
                }
                else
                {
                    Toast.makeText(MainActivity.this , "There is no network available", Toast.LENGTH_LONG).show();
                }

                // ca

            }
        });


    }



    @Override
    public void getJsonData(ArrayList<Rows> displayData , String apptitle) {

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,displayData );
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        setTitle(apptitle);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }


    }


}