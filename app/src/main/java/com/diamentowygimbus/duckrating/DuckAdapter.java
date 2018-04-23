package com.diamentowygimbus.duckrating;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

class DuckAdapter extends RecyclerView.Adapter<DuckAdapter.ViewHolder> {
    private List<Duck> duckList;

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        RatingBar userRating;
        TextView generalRating;
        TextView numberOfComments;
        Button showComments;
        View layout;


        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            title = layout.findViewById(R.id.item_title);
            image = layout.findViewById(R.id.item_image);
            userRating = layout.findViewById(R.id.item_user_rating);
            generalRating = layout.findViewById(R.id.item_general_rating);
            numberOfComments = layout.findViewById(R.id.item_number_comments);
            showComments = layout.findViewById(R.id.item_comments);
        }
    }

    DuckAdapter(List<Duck> ducks){
        this.duckList = ducks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.duck_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Duck current = duckList.get(position);
        holder.title.setText(current.getTitle());
        //holder.image.setImage(); TODO
        //holder.userRating.setRating(); TODO
        String general = ""+String.format("%.2f", average(current.getRating()))+"/5";
        holder.generalRating.setText(general);
        String comments = "Comments: "+current.getComments().length;
        holder.numberOfComments.setText(comments);
        holder.showComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO open fragment/dialog with comments
            }
        });
    }

    @Override
    public int getItemCount() {
        return duckList.size();
    }

    private float average(int[] values){
        float average = 0;
        for (int i : values){
            average+= i;
        }
        return average/values.length;
    }
}
