package com.example.application1.Visitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {

    private Context context;
    private ArrayList<VisitorDetails> objects = new ArrayList<>();

    public VisitorAdapter (Context context, ArrayList<VisitorDetails> objects ){
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list_item_layout, parent, false);
        return new VisitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, int position) {
        VisitorDetails visitorDetails = objects.get(position);
        Glide.with(context).load(visitorDetails.getVisitorImg()).into(holder.imgVisitorCard);
        holder.txtVisitorCardName.setText(visitorDetails.getVisitorName());
        holder.txtVisitorCardDate.setText(visitorDetails.getVisitorDate());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder{
        private TextView txtVisitorCardName, txtVisitorCardDate;
        private ImageView imgVisitorCard;
        public VisitorViewHolder (View view){
            super(view);

            txtVisitorCardName = (TextView) view.findViewById(R.id.txtVisitorCardName);
            txtVisitorCardDate = (TextView) view.findViewById(R.id.txtVisitorCardDate);
            imgVisitorCard = (ImageView) view.findViewById(R.id.imgVisitorCard);
        }
    }
}
