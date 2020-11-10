package com.example.habithacker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>  {

        private List<Habit> mData;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<Habit> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.habit_item, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Habit habit = mData.get(position);
            //HERE
            holder.textName.setText(""+habit.getName());
            holder.textDesc.setText(""+habit.getDescription());
            holder.textDesc.setText(""+habit.getFrequency());
            holder.textDesc.setText(""+habit.getProgress());
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
            TextView textName; //add four more of these/with diff var names
            TextView textDesc;
            TextView textFreq;
            TextView textProg;

            ViewHolder(View itemView) {
                super(itemView);
                //HERE
                textName = itemView.findViewById(R.id.textName);
                textDesc = itemView.findViewById(R.id.textDesc);
                textFreq = itemView.findViewById(R.id.textFreq);
                textProg = itemView.findViewById(R.id.textProg);
                itemView.setOnLongClickListener(this);
            }

            @Override
            public boolean onLongClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
                return false;
            }
        }

        // convenience method for getting data at click position
        Habit getItem(int id) {
            return mData.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }

