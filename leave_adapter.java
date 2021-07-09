package com.example.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendence.api.ApiClient;
import com.example.attendence.api.ApiInterface;
import com.example.attendence.data.app_rej_rep;
import com.example.attendence.data.leavemgmtResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class leave_adapter extends RecyclerView.Adapter<leave_adapter.holder> {
    List<leavemgmtResponse.Leavelist> list;
    Context context;

    public leave_adapter(List<leavemgmtResponse.Leavelist> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public leave_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row_leave, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull leave_adapter.holder holder, int position) {
        holder.name.setText(list.get(position).username_leave);
        holder.start_date.setText(list.get(position).start_date1);

        holder.end_date.setText(list.get(position).end_date1);
        holder.reason.setText(list.get(position).reason1);
        //holder.date_time_leave.setText(list.get(position).date_time_leave1);

        holder.approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context,approved.class);
//                intent.putExtra("lv_status", "1");
//                intent.putExtra("lv_id", list.get(position).leave_id);
//                context.startActivity(intent);
//                //fuctioncalling();
//                ((Activity)context).finish();

                //remove code for approved btn if we clck
                Leave_app_rej_api(list.get(position).leave_id, "1");

                list.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition(), list.size());
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, approved.class);
//                intent.putExtra("lv_status", "2");
//                intent.putExtra("lv_id", list.get(position).leave_id);
//                context.startActivity(intent);
//                ((Activity) context).finish();

                Leave_app_rej_api(list.get(position).leave_id, "2");

                list.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition(), list.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class holder extends RecyclerView.ViewHolder {
        TextView name, start_date, end_date, reason, date_time_leave;
        Button approved, reject;

        public holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_leave);
            start_date = itemView.findViewById(R.id.start_leave);
            end_date = itemView.findViewById(R.id.end_leave);
            reason = itemView.findViewById(R.id.reason_leave);
            approved = (Button) itemView.findViewById(R.id.btn_app);
            reject = (Button) itemView.findViewById(R.id.btn_reject);
            //date_time_leave=itemView.findViewById(R.id.date_time_leave);

        }
    }

    private void Leave_app_rej_api(String leave_id, String leave_status) {
       // Toast.makeText(context, "leave approved", Toast.LENGTH_LONG).show();
//        Toast.makeText(context, leave_id + " - " + leave_status, Toast.LENGTH_LONG).show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<app_rej_rep> call = service.app_rej(leave_id, leave_status);
        call.enqueue(new Callback<app_rej_rep>() {
            @Override
            public void onResponse(Call<app_rej_rep> call, Response<app_rej_rep> response) {
                if (response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        if(leave_status.equalsIgnoreCase("1")){
                            Toast.makeText(context, "Leave Approved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Leave Rejected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<app_rej_rep> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}



