package exercise.android.reemh.todo_items;

import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ViewItemHolder> {

    private List<TodoItem> _todoItemList;

    public void setTodoItemList(List<TodoItem> newList){
        _todoItemList.clear();
        _todoItemList.addAll(newList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ()
            }
        });
        return new ViewItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewItemHolder holder, int position) {
        TodoItem item = _todoItemList.get(position);
        holder.setDescription(item.getDescription());
        holder.setCheckBox(item.isDone());
    }

    @Override
    public int getItemCount() {
        return _todoItemList.size();
    }
}
