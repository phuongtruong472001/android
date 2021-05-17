package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListFood extends AppCompatActivity {
 //   private static final String URL = "https://bookshopb.herokuapp.com/api/books";
    RecyclerView reImageFood;
 //   JSONArray jsonArray;
    List<Food> listFood=new ArrayList<>();
    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        reImageFood=findViewById(R.id.RecycleviewFood);
      //  RequestQueue requestQueue = Volley.newRequestQueue(ListFood.this);
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        foodAdapter=new FoodAdapter(listFood, ListFood.this);
//        StringRequest stringRequest=  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    jsonArray = new JSONArray(response);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListFood.this, RecyclerView.VERTICAL, false);
        reImageFood.setLayoutManager(layoutManager);
        reImageFood.setAdapter(foodAdapter);
        foodAdapter.setmIOnClickMenuItem(new IOnClickMenuItem() {
            @Override
            public void iClickImageFood(Food item) {
                Toast.makeText(ListFood.this, item.getFoodName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ListFood.this,MainFood.class);
                intent.putExtra("imageLink",item.getImage());
                intent.putExtra("FoodName",item.getFoodName());
                intent.putExtra("Material",item.getMaterial());
                intent.putExtra("Recipes",item.getRecipes());
                intent.putExtra("Nutrition",item.getNutrition());
                startActivity(intent);
            }
        });
    }
}
