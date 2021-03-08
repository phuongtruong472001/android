package com.example.btvnbuoi6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {

    List<Countries> list=new ArrayList<>();
    List<Countries> listFilter=new ArrayList<>();
    Context context;

    public CountryAdapter(List<Countries> list, Context context) {
        this.list = list;
        this.listFilter=list;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.country,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {

        Countries countries=list.get(position);
        holder.Country_Region.setText(String.valueOf(countries.getCountry_Region()));
        holder.Confirmed.setText(String.valueOf(countries.getConfirmed()));
        holder.Deaths.setText(String.valueOf(countries.getDeaths()));
        holder.Recovered.setText(String.valueOf(countries.getRecovered()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public   Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    list=listFilter;
                }
                else{
                    List<Countries> temp=new ArrayList<>();
                    for (Countries x:list){
                        if(x.Country_Region.toLowerCase().contains(strSearch.toLowerCase())){
                            temp.add(x);
                        }

                    }
                    list=temp;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=list;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list=(List<Countries>)results.values;
                notifyDataSetChanged();
            }

        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Country_Region, Confirmed,Deaths,Recovered;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Country_Region=itemView.findViewById(R.id.tvCountry_Region);
            Confirmed=itemView.findViewById(R.id.tvConfirmed);
            Deaths=itemView.findViewById(R.id.tvDeaths);
            Recovered=itemView.findViewById(R.id.tvRecovered);
        }
    }


}

