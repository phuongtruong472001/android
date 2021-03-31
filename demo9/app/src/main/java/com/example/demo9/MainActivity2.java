package com.example.demo9;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    private static final String URL = "https://bookshopb.herokuapp.com/api/books";
    RecyclerView reImgBook;
    JSONArray jsonArray;
    List<MenuBook> bookMenu = new ArrayList<>();
    BookMenuAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);

        reImgBook = findViewById(R.id.recycler_view_menu_book);
        adapter = new BookMenuAdapter(bookMenu, MainActivity2.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imageLink=jsonObject.getString("imageLink");
                        String title=jsonObject.getString("title");
                        String author=jsonObject.getString("author");
                        int numOfPage=jsonObject.getInt("numOfPage");
                        long  price=jsonObject.getLong("price");
                        String description=jsonObject.getString("description");
                        String categoty=jsonObject.getString("categoty");
                        float rateStar=jsonObject.getInt("rateStar");
                        float numOfReview=jsonObject.getInt("numOfReview");
                        float DanhGia =rateStar/numOfReview;
                        bookMenu.add(new MenuBook(imageLink,title,author,numOfPage,price,description,categoty,rateStar,numOfReview,DanhGia));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
                        reImgBook.setLayoutManager(layoutManager);
                        reImgBook.setAdapter(adapter);

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

        adapter.setmIOnClickMenuItem(new IOnClickMenuItem() {
            @Override
            public void iClickImageBook(MenuBook item) {
                Toast.makeText(MainActivity2.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                intent.putExtra("imageLink",item.getImageLink());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("author",item.getAuthor());
                intent.putExtra("numOfPage",String.valueOf(item.getNumOfPage()));
                intent.putExtra("description",item.getDescription());
                intent.putExtra("categoty",item.getCategoty());
                intent.putExtra("DanhGia",String.valueOf(item.getDanhGia()));
                intent.putExtra("price",String.valueOf(item.getPrice()));
                intent.putExtra("rateStar",String.valueOf(item.getRateStar()));
                intent.putExtra("numOfReview",String.valueOf(item.getNumOfReview()));
                startActivity(intent);
            }
        });

    }
}
