package com.example.app.mainlayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.R;
import com.example.app.food.ChatBeo;
import com.example.app.food.ChatBotduong;
import com.example.app.food.ChatDam;
import com.example.app.food.Food;
import com.example.app.food.FoodAdapter;
import com.example.app.food.IOnClickMenuItem;
import com.example.app.food.MainFood;
import com.example.app.food.VitaminKC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ListFood extends AppCompatActivity {
    private static final String URL = "https://haui-api-food.herokuapp.com/api/food";
    RecyclerView reImageFood;
    JSONArray jsonArray;
    SearchView searchView;
    List<Food> listFood=new ArrayList<>();
    FoodAdapter foodAdapter;
    TextView tvChatBeo, tvChatDam, tvVtmKc, tvChatBotDuong;
    List<Food> listChatDam=new ArrayList<>();
    List<Food> listChatBeo=new ArrayList<>();
    List <Food> listChatBotDuong=new ArrayList<>();
    List <Food> listVitaminKC=new ArrayList<>();
    ImageView addFood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AnhXa();
        reImageFood=findViewById(R.id.RecycleviewFood);
        RequestQueue requestQueue = Volley.newRequestQueue(ListFood.this);
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        foodAdapter=new FoodAdapter(listFood, ListFood.this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectFood = jsonArray.getJSONObject(i);
                        String foodName = jsonObjectFood.getString("foodName");
                        String img = jsonObjectFood.getString("img");
                        String material = jsonObjectFood.getString("material");
                        String recipes = jsonObjectFood.getString("recipes");
                        String nutrition = jsonObjectFood.getString("nutrition");
                        listFood.add(new Food(img, foodName, material, recipes, nutrition));
                        listFood.size();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListFood.this, RecyclerView.VERTICAL, false);
                        reImageFood.setLayoutManager(layoutManager);
                        reImageFood.setAdapter(foodAdapter);
                    }
                    

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest);



        foodAdapter.setmIOnClickMenuItem(new IOnClickMenuItem() {
            @Override
            public void iClickImageFood(Food item) {
                Toast.makeText(ListFood.this, item.getFoodName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ListFood.this, MainFood.class);
                intent.putExtra("imageLink",item.getImage());
                intent.putExtra("FoodName",item.getFoodName());
                intent.putExtra("Material",item.getMaterial());
                intent.putExtra("Recipes",item.getRecipes());
                intent.putExtra("Nutrition",item.getNutrition());
                startActivity(intent);
            }
        });
        for (Food food:listFood)
        {
            if(food.getNutrition().toLowerCase().contains("béo"))
                listChatBeo.add(food);
            else if(food.getNutrition().toLowerCase().contains("đạm"))
                listChatDam.add(food);
            else if(food.getNutrition().toLowerCase().contains("đường"))
                listChatBotDuong.add(food);
            else if(food.getNutrition().toLowerCase().contains("vitamin"))
                listVitaminKC.add(food);
        }
        tvChatBeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFood.this, ChatBeo.class);
                intent.putParcelableArrayListExtra("listChatBeo", (ArrayList<? extends Parcelable>) listChatBeo);
                startActivity(intent);
            }
        });
        tvChatBotDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFood.this, ChatBotduong.class);
                intent.putParcelableArrayListExtra("listChatBotDuong", (ArrayList<? extends Parcelable>) listChatBotDuong);
                startActivity(intent);
            }
        });
        tvChatDam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFood.this, ChatDam.class);
                intent.putParcelableArrayListExtra("listChatDam", (ArrayList<? extends Parcelable>) listChatDam);
                startActivity(intent);
            }
        });
        tvVtmKc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFood.this, VitaminKC.class);
                intent.putParcelableArrayListExtra("listVitaminKC", (ArrayList<? extends Parcelable>) listVitaminKC);
                startActivity(intent);
            }
        });
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFood.this, VitaminKC.class);

                startActivity(intent);
            }
        });
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
                foodAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    void AnhXa(){
        tvChatBeo=findViewById(R.id.tvChatBeo);
        tvChatDam=findViewById(R.id.tvChatDam);
        tvVtmKc=findViewById(R.id.tvVtmKc);
        tvChatBotDuong=findViewById(R.id.tvChatBotDuong);
        addFood=findViewById(R.id.addFood);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
