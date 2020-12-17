package com.titowareglobal.humerusrehab.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titowareglobal.humerusrehab.Interface.ItemClickListener;
import com.titowareglobal.humerusrehab.Model.Exercise;
import com.titowareglobal.humerusrehab.R;
import com.titowareglobal.humerusrehab.ViewExerciseActivity;

import java.util.List;

class  RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView mImageView;
    public TextView mTextView;


    private ItemClickListener mItemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = (ImageView)itemView.findViewById(R.id.ex_img);
        mTextView = (TextView)itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);




    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onClick(view,getAdapterPosition());

    }
}
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Exercise> mExerciseList;
    private Context mContext;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        mExerciseList = exerciseList;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        holder.mImageView.setImageResource(mExerciseList.get(position).getImage_id());
        holder.mTextView.setText(mExerciseList.get(position).getName());




        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(mContext, ViewExerciseActivity.class);
                intent.putExtra("image_id",mExerciseList.get(position).getImage_id());
                intent.putExtra("name",mExerciseList.get(position).getName());
                intent.putExtra("my_Description",mExerciseList.get(position).getMyDescription());


                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
