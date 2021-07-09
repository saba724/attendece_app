package com.example.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence.data.regemplyinfoResponse;

import java.util.ArrayList;

public class regemplyinfo_adapater extends RecyclerView.Adapter<regemplyinfo_adapater.holder>{
    ArrayList<regemplyinfoResponse.emplydetails> list;
    Context context;

    public regemplyinfo_adapater(ArrayList<regemplyinfoResponse.emplydetails> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_row_emlyinfo,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.name.setText(list.get(position).name1);
        holder.email.setText(list.get(position).emai1);
        holder.phoneno.setText(list.get(position).phoneno1);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder
    {
        TextView name,email,phoneno;

        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_emplyinfo);
            email =itemView.findViewById(R.id.email_empyinfo);
            phoneno=itemView.findViewById(R.id.phoneno_emplyinfo);
        }
    }
}
