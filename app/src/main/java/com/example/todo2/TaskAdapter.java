package com.example.todo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private int resource;
    private List<Task> taskList;

    private OnCheckedChangeListener onCheckedChangeListener;

    public TaskAdapter(Context context, int resource, List<Task> taskList) {
        super(context, resource, taskList);
        this.context = context;
        this.resource = resource;
        this.taskList = taskList;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int position, boolean isChecked);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, parent, false);
        }

        final Task task = taskList.get(position);

        if (task != null) {
            TextView titleTextView = view.findViewById(R.id.textViewTitle);
            TextView noteView = view.findViewById(R.id.note);
            TextView dueDateTimeView = view.findViewById(R.id.dueDateTime);
            TextView statusTextView = view.findViewById(R.id.statusTextView);
            CheckBox checkBox = view.findViewById(R.id.checkbox);

            if (titleTextView != null) {
                titleTextView.setText(task.getTitle());
            }

            if (noteView != null) {
                noteView.setText(task.getNote());
            }

            if (dueDateTimeView != null) {
                dueDateTimeView.setText("Due Date: " + task.getTimeDate());
            }

            if (statusTextView != null) {
                statusTextView.setText("Status: " + (task.isStatus() ? "Done" : "Due"));
            }

            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(task.isStatus());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    task.setStatus(isChecked);

                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(position, isChecked);
                    }
                }
            });
        }
        return view;
    }
}
