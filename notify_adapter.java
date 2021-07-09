package com.example.attendence;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence.data.NotifictionResponse;

import java.util.ArrayList;

public class notify_adapter extends RecyclerView.Adapter<notify_adapter.holder>{
    ArrayList<NotifictionResponse.notificationlist> list;
    Context context;

    public notify_adapter(ArrayList<NotifictionResponse.notificationlist> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_row_notify,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.title.setText(list.get(position).title1);
        holder.notify_date_time.setText(list.get(position).date_time_set1);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,description.class);
                intent.putExtra("description",list.get(position).description1);//get code is in description.java
                context.startActivity(intent);
               // Toast.makeText(context,"hi",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder
    {
        TextView title,notify_date_time;
        CardView cardView;


        public holder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_notify);
            cardView=itemView.findViewById(R.id.cv);
            notify_date_time=itemView.findViewById(R.id.date_time_notify);

//            String dis_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//            notify_date.setText(dis_date);
        }
    }

}
