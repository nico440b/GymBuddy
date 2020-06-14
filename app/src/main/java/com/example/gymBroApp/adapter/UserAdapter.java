package com.example.gymBroApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymBroApp.R;
import com.example.gymBroApp.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> users;
    Context mContext;


    public UserAdapter(Context context, List<User> itemList) {
        this.mContext = context;
        this.users = itemList;

    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_list_item, parent,false);
        return new UserHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User current = users.get(position);
        holder.tName.setText(current.getName());
        holder.tAge.setText(String.valueOf(current.getAge()));
        holder.tWork.setText(current.getWorkPref());
        holder.tWeather.setText(current.getWeatherPref());
    }

    @Override
    public int getItemCount() {
        if (users==null){
            return 0;
        }
        return users.size();
    }


    class UserHolder extends RecyclerView.ViewHolder{
        UserAdapter adapter;
        private TextView tName,tAge,tWork,tWeather;
        private ImageView img;

        public UserHolder(@NonNull View itemView,UserAdapter adapter) {
            super(itemView);

            this.adapter = adapter;
            img = itemView.findViewById(R.id.iconFind);
            tName = itemView.findViewById(R.id.nameView);
            tAge = itemView.findViewById(R.id.ageView);
            tWork = itemView.findViewById(R.id.workoutPrefView);
            tWeather = itemView.findViewById(R.id.weatherPrefView);
        }
    }
}
