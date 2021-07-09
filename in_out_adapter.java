package com.example.attendence;

//public class in_out_adapter extends RecyclerView.Adapter<in_out_adapter.holder> {
//    List<in_out_adminResponse.EmployeeList> list;
//
//    public in_out_adapter(List<in_out_adminResponse.EmployeeList> list) {
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
//       // View view=inflater.inflate(R.layout.single_row_in_out,parent,false);
//        View view=inflater.inflate(R.layout.table_format,parent,false);
//
//        return new holder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull holder holder, int position) {
//        holder.name.setText(list.get(position).username1);
//        holder.in_time.setText(list.get(position).checkin1);
//        holder.out_time.setText(list.get(position).checkout1);
//        holder.date_time.setText(list.get(position).date_time1);
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class holder extends RecyclerView.ViewHolder
//
//    {
//        TextView name,in_time,out_time,date_time;
//
//        public holder(@NonNull View itemView) {
//            super(itemView);
//            name=itemView.findViewById(R.id.name_ad1);
//            in_time=itemView.findViewById(R.id.in_time_ad1);
//            out_time=itemView.findViewById(R.id.out_time_ad1);
//            date_time=itemView.findViewById(R.id.date_time);
//        }
//    }
//}
