package com.example.btvnbuoi6;

import android.app.SearchManager;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.widget.Filter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    String url ="https://apincov.herokuapp.com/countries";

    RecyclerView recyclerView;
    JSONArray jsonArray;
   SearchView searchView;
    List<Countries> listCountries=new ArrayList<>();
    CountryAdapter adappter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);

        recyclerView=findViewById(R.id.recyclerView);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                jsonArray=new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++) {
                                    JSONObject jsonObjectCountry = jsonArray.getJSONObject(i);
                                    String Country_Region=jsonObjectCountry.getString("Country_Region");
                                    long ConfirmedCountry=jsonObjectCountry.getLong("Confirmed");
                                    long DeathsCountry=jsonObjectCountry.getLong("Deaths");
                                    long RecoveredCountry=jsonObjectCountry.getLong("Recovered");

                                    listCountries.add(new Countries(Country_Region,ConfirmedCountry,DeathsCountry,RecoveredCountry));
                                    listCountries.size();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // textView.setText("Lỗi rồi :(");
                }
            });
        requestQueue.add(stringRequest);

        adappter = new CountryAdapter(listCountries, MainActivity2.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adappter);



    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adappter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                adappter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
