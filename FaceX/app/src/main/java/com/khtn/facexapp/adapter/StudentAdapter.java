package com.khtn.facexapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.khtn.facexapp.R;
import com.khtn.facexapp.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> list;

    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_student_item, null);
        }

        TextView tvName = view.findViewById(R.id.tvStudentName);
        TextView tvDesc = view.findViewById(R.id.tvStudentDesc);
        Student std = list.get(i);
        tvName.setText(std.getName());
        tvDesc.setText(String.valueOf(std.getId()));
        return view;
    }
}
