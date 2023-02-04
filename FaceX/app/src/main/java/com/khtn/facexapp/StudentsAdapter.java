package com.khtn.facexapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khtn.facexapp.model.Student;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {

    private Context context;
    private List<Student> mListStudent;

    public StudentsAdapter(Context context, List<Student> mListStudent){
        this.context = context;
        this.mListStudent = mListStudent;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        Student uploadCurrent = mListStudent.get(position);
        holder.tvName.setText(uploadCurrent.getName());
        holder.tvId.setText(String.valueOf(uploadCurrent.getId()));
        Picasso.with(context)
                .load(uploadCurrent.getImage())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mListStudent.size();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvId;
        public ImageView imageView;

        public StudentsViewHolder(View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvId = itemView.findViewById(R.id.tv_id);
            imageView = itemView.findViewById(R.id.image_view_uploaded);
        }
    }
}
