package com.example.gymBroApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymBroApp.R;
import com.example.gymBroApp.model.SettingsItem;

import java.util.ArrayList;



public class SettingsAdapter  extends RecyclerView.Adapter<SettingsAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<SettingsItem> itemList;
    private ArrayList<String> list;
    private ArrayList<Integer> imgList;
    Context mContext;



    public SettingsAdapter(Context context, ArrayList<String> itemList,ArrayList<Integer> imgList) {
        this.mContext = context;
        this.list=itemList;
        this.imgList=imgList;

    }

    @NonNull
    @Override
    public SettingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_settings_item, parent,false);
        return new MyViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.MyViewHolder holder, int position) {
        String mCurrent = list.get(position);
        Integer iCurrent = imgList.get(position);
        holder.textInputS.setText(mCurrent);
        holder.imgV.setImageResource(iCurrent);
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        SettingsAdapter mAdapter;
        ImageView imgV;

        EditText textInputS;

        public MyViewHolder(View itemView, SettingsAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgV= itemView.findViewById(R.id.iconSettings);
            textInputS = itemView.findViewById(R.id.textInputSettings);
            this.mAdapter = adapter;

        }
        @Override
        public void onClick(View view) {
        }
    }

}
