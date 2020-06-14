package com.example.gymBroApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymBroApp.R;
import com.example.gymBroApp.model.SettingsItem;

import java.util.ArrayList;


public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private ArrayList<SettingsItem> itemList;
    Context mContext;



    public WeatherAdapter(Context context, ArrayList<SettingsItem> itemList) {
        this.mContext = context;
        this.itemList=itemList;

    }

    @NonNull
    @Override
    public WeatherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent,false);
        return new MyViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SettingsItem mCurrent = itemList.get(position);

        holder.textViewS.setText(mCurrent.getFieldInput());
        holder.imgV.setImageResource(mCurrent.getImgSrc());
    }

    @Override
    public int getItemCount() {
        if (itemList==null){
            return 0;
        }
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        WeatherAdapter mAdapter;
        ImageView imgV;
        TextView textViewS;

        public MyViewHolder(View itemView, WeatherAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgV= itemView.findViewById(R.id.iconWeather);

            textViewS = itemView.findViewById(R.id.weatherText);

            this.mAdapter = adapter;

        }
        @Override
        public void onClick(View view) {
        }
    }



}
