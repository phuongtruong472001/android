package com.example.app.food;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.mainlayout.ListFood;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFood extends AppCompatActivity implements View.OnClickListener{
    public static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    EditText edtTenMon, edtNguyenLieu, edtChatDD, edtCachLam;
    ImageView uploadimg;
    Button  btnUpload,btnSelectImg;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;
    String realPathImage= null;
    String URL="https://haui-hit-food.herokuapp.com/api/food";
    String img="";
    private Uri mUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AnhXa();


        btnSelectImg.setOnClickListener(this);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallApi();
                Intent intent=new Intent(AddFood.this, ListFood.class);
                startActivity(intent);
            }
        });

    }
    public  void selectImg(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
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

    public void AnhXa () {
        edtTenMon = findViewById(R.id.edtTenMon);
        edtNguyenLieu = findViewById(R.id.edtNguyenLieu);
        edtChatDD = findViewById(R.id.edtChatDD);
        edtCachLam = findViewById(R.id.edtCachLam);
        uploadimg = findViewById(R.id.selectImg);
        btnUpload = findViewById(R.id.btnUpload);
        btnSelectImg=findViewById(R.id.btnSelectImg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnSelectImg:
                selectImg();

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null) {
            Uri path=data.getData();
            mUri=path;
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                uploadimg.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void CallApi(){
        String strTenMon=edtTenMon.getText().toString().trim();
        String strIMG=RealPathUtil.getRealPath(this,mUri);
        String strNguyenLieu=edtNguyenLieu.getText().toString().trim();
        File file=new File(strIMG);
        String strCachlam=edtCachLam.getText().toString().trim();
        String strChatDD= edtChatDD.getText().toString().trim();
        RequestBody TenMon=RequestBody.create(MediaType.parse("multipart/form-data"),strTenMon);
        RequestBody IMG=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        RequestBody NguyenLieu=RequestBody.create(MediaType.parse("multipart/form-data"),strNguyenLieu);
        RequestBody CachLam=RequestBody.create(MediaType.parse("multipart/form-data"),strCachlam);
        RequestBody ChatDD=RequestBody.create(MediaType.parse("multipart/form-data"),strChatDD);

        MultipartBody.Part multipart=MultipartBody.Part.createFormData(Constants.KEY_img,file.getName(),IMG);
        ImageService.imageservice.addNewFood(TenMon,multipart,NguyenLieu,CachLam,ChatDD).enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {

                Toast.makeText(AddFood.this,"Thêm món thành công !",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                Toast.makeText(AddFood.this,"upload fail !",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayoutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayoutputStream);
        byte[]imgBytes=byteArrayoutputStream.toByteArray();
         img=android.util.Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }

    private String ImageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayoutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayoutputStream);
        byte[]imgBytes=byteArrayoutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

}
//        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
//        {
//            Uri path=data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(path);
//                bitmap= BitmapFactory.decodeStream(inputStream);
//                uploadimg.setImageBitmap(bitmap);
////                encodeBitmapImage(bitmap);
////                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
////                uploadimg.setImageBitmap(bitmap);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////                JSONObject jsonObject = new JSONObject();
////                try {
////                    jsonObject.put("foodName", edtTenMon.getText().toString().trim());
////                    jsonObject.put("img", ImageToString(bitmap));
////                    jsonObject.put("material", edtNguyenLieu.getText().toString().trim());
////                    jsonObject.put("recipes", edtCachLam.getText().toString().trim());
////                    jsonObject.put("nutrition", edtChatDD.getText().toString().trim());
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////                String requestbody = jsonObject.toString();
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        edtTenMon.setText("");
//                        edtNguyenLieu.setText("");
//                        edtCachLam.setText("");
//                        edtChatDD.setText("");
//                        Toast.makeText(getApplicationContext(),"Thêm món thanhf công",Toast.LENGTH_SHORT).show();
//                      //  Toast.makeText(AddFood.this, "Thêm món thành công !", Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), "Lỗi rồi", Toast.LENGTH_SHORT).show();
//                    }
////                }) {
////                    @Override
////                    public String getBodyContentType() {
////                        return "application/json; charset = utf-8";
////                    }
////
////                    @Override
////                    public byte[] getBody() throws AuthFailureError {
////                        if (requestbody == null) return null;
////                        else {
////                            try {
////                                return requestbody.getBytes("utf-8");
////                            } catch (UnsupportedEncodingException e) {
////                                e.printStackTrace();
////                                return null;
////                            }
////                        }
////                    }
//
//                });
//
//                RequestQueue requestQueue = Volley.newRequestQueue(AddFood.this);
//                requestQueue.add(stringRequest);
//                Intent intent=new Intent(AddFood.this, ListFood.class);
//                startActivity(intent);
////                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
////                    @Override
////                    public void onResponse(JSONObject response) {
//////                        Toast.makeText(AddFood.this, img, Toast.LENGTH_SHORT).show();
//////                        Intent intent=new Intent(AddFood.this, ListFood.class);
//////                        startActivity(intent);
////                    }
////                }, new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////
////                    }
////                })
//////                {
//////                    @Override
//////                    protected Map<String, String> getParams() throws AuthFailureError {
//////                        Map<String,String>params=new HashMap<String, String>();
//////                        params.put("foodName", edtTenMon.getText().toString().trim());
//////                        params.put("img", img);
//////                        params.put("material", edtNguyenLieu.getText().toString().trim());
//////                        params.put("recipes", edtCachLam.getText().toString().trim());
//////                        params.put("nutrition", edtChatDD.getText().toString().trim());
//////                        return params;
//////                    }
////                };//đây là bọn em add tay thử ạ
////                // ơ như này là k đc, phải đúng cơ
////                //đúng của bọn em như này ạ
////                // api nó nhận dữ liệu qua body ấy, k phải param đâu, mở cho a chỗ api bên back end cho a xem
////                //cái mà api là gì, nhận hay trả về những cái gì ấy
////                //giờ mà nói em hoang mang chắc anh Trung đấm em chết :v
////                // ờ vậy thôi, để a xem
////                requestQueue.add(jsonObjectRequest);
////
////            //    MySingleton.getInstance(AddFood.this).AddToRequestQueue(stringRequest);
////
////
////            }
//            }
//
//
//        });