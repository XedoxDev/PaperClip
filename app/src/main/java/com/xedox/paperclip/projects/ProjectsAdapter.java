package com.xedox.paperclip.projects;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.xedox.paperclip.activitys.StartActivity;
import java.util.List;
import com.xedox.paperclip.R;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.VH> {

    private Context context;
    private List<Project> list;
    private ProjectsAdapter.OnItemClickListener onItemClickListener = (project, pos) -> {};

    public ProjectsAdapter(Context context, List<Project> list, OnItemClickListener l) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = l;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int pos) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.project_item, parent, false);
        VH vh = new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int pos) {
        holder.name.setText(list.get(pos).getName());
        ((StartActivity)context).registerForContextMenu(holder.parent);

        holder.parent.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            ObjectAnimator colorAnimation =
                                    ObjectAnimator.ofArgb(
                                            view,
                                            "backgroundColor",
                                            context.getColor(R.color.background),
                                            context.getColor(R.color.pressedItem));
                            colorAnimation.setDuration(250);
                            colorAnimation.start();
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP
                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
                            ObjectAnimator colorAnimation =
                                    ObjectAnimator.ofArgb(
                                            view,
                                            "backgroundColor",
                                            context.getColor(R.color.pressedItem),
                                            context.getColor(R.color.background));
                            colorAnimation.setDuration(250);
                            colorAnimation.start();
                        }
                        return false;
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView name;
        public View parent;
        
        public VH(View itemView) {
            super(itemView);
            parent = itemView;
            name = itemView.findViewById(R.id.name);
            parent.setOnClickListener(
                    (view) -> {
                        int pos = getAdapterPosition();
                        onItemClickListener.onItemClick(list.get(pos), pos);
                    });
        }
    }

    public void updateData(List<Project> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    public void addItem(Project newItem) {
        list.add(newItem);
        notifyItemInserted(list.size() - 1);
    }

    public void removeItem(int position) {
        list.get(position).delete();
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void updateItem(int position, Project updatedItem) {
        list.set(position, updatedItem);
        notifyItemChanged(position);
    }

    public static interface OnItemClickListener {
        public void onItemClick(Project project, int pos);
    }
}
