package com.example.same_image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    List<Data_Model> list;
    Context context;

    public Adapter(List<Data_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void filterList(List<Data_Model> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new Viewholder(view);
    }
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Data_Model list1, int position) {
        list.add(position, list1);
        notifyItemInserted(position);
    }

    public List<Data_Model> getData() {
        return list;
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Data_Model data_model = list.get(position);
        holder.title.setText(data_model.getName());
        holder.imageView.setImageResource(data_model.getImage());
//        holder.date.setText(data_model.getEmail());
//        Picasso.get().load(data_model.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, date;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView_row);
            title = itemView.findViewById(R.id.title_row);
            date = itemView.findViewById(R.id.date_row);

        }
    }

}
