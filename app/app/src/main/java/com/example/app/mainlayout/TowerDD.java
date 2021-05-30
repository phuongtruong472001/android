package com.example.app.mainlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.modeeat.Adults;
import com.example.app.modeeat.Chidren;
import com.example.app.modeeat.bmi.Fat;
import com.example.app.modeeat.bmi.Thin;

public class TowerDD extends AppCompatActivity {
    EditText edtHeight,edtWeight;
    Button btnSubmit;
    TextView tvBMI,tvKetQua,tvThapddBMI;
    float height,weight,answer;
    int Age;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tower_dd);
        AnhXa();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       btnSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               height=Float.valueOf(edtHeight.getText().toString());
               weight=Float.valueOf(edtWeight.getText().toString());
               answer=weight/(height*height);
               if(answer<16)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang gầy cấp độ 3,bạn muốn tra cứu gì tiếp theo :");
               }
               else if(answer<17)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang gầy cấp độ 2,bạn muốn tra cứu gì tiếp theo :");
               }
               else if(answer<18.5)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang gầy cấp độ 1,bạn muốn tra cứu gì tiếp theo :");
               }
               else if(answer<25)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang ở trạng thái bình thường,bạn muốn tra cứu gì tiếp theo :");
               }
               else if(answer<30)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang thừa cân ,bạn muốn tra cứu gì tiếp theo :");
               }
               else if(answer<35)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang béo phì độ 1,bạn muốn tra cứu gì tiếp theo :");
               }
               else if(answer<40)
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang béo phì cấp độ 2,bạn muốn tra cứu gì tiếp theo :");
               }
               else
               {
                   tvBMI.setText("Chỉ số BMI của bạn là :"+answer);
                   tvKetQua.setText("Bạn đang béo phì cấp độ 3,bạn muốn tra cứu gì tiếp theo :");
               }
               tvBMI.setVisibility(View.VISIBLE);
               tvKetQua.setVisibility(View.VISIBLE);
               tvThapddBMI.setVisibility(View.VISIBLE);
           }

       });
       tvThapddBMI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent ;
               if(answer<18.5)
               {
                   intent=new Intent(TowerDD.this, Thin.class);
                   startActivity(intent);
               }
               else if(answer<25)
               {
//                   if(Age <15)
//                       intent=new Intent(TowerDD.this, Chidren.class);
//                   else
//                       intent=new Intent(TowerDD.this, Adults.class);
                   intent=new Intent(TowerDD.this, Adults.class);
                   startActivity(intent);


               }
               else
               {
                   intent=new Intent(TowerDD.this, Fat.class);
                   startActivity(intent);
               }

           }
       });

    }
    public void AnhXa(){
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);
        btnSubmit=findViewById(R.id.btnSubmit);
        tvBMI=findViewById(R.id.tvBMI);
        tvBMI.setVisibility(View.INVISIBLE);
        tvKetQua=findViewById(R.id.tvKetQua);
        tvKetQua.setVisibility(View.INVISIBLE);
        tvThapddBMI=findViewById(R.id.tvThapddBMI);
        tvThapddBMI.setVisibility(View.INVISIBLE);
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
