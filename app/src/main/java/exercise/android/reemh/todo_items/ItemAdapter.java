package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Vector;

public class ItemAdapter extends RecyclerView.Adapter<ViewItemHolder> {

    private List<TodoItem> _todoItemList = new Vector<>();
    TodoItemsHolder todoItemsHolder;
    private boolean onBind;

    ItemAdapter(TodoItemsHolder todoItemsHolder){
        onBind = false;
        this.todoItemsHolder = todoItemsHolder;
        setTodoItemList(todoItemsHolder.getCurrentItems());
    }

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
        return new ViewItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewItemHolder holder, int position) {
        onBind = true;
        TodoItem item = _todoItemList.get(position);
        holder.setDescription(item.getDescription());
        holder.setCheckBox(item.isDone());
        // sets the strike through text in case of done item
        if (item.isDone()){
            holder.getDescription().setPaintFlags(holder.getDescription().getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.getDescription().setPaintFlags(holder.getDescription().getPaintFlags()
                    & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // sets item is done or in progress when mark the check bok
        holder.getCheckBox().setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(!onBind){
                if (isChecked){
                    todoItemsHolder.markItemDone(item);
                }
                else {
                    todoItemsHolder.markItemInProgress(item);
                }
                setTodoItemList(todoItemsHolder.getCurrentItems());
            }
        });

        // deletes the item if press the delete button
        holder.getTrash().setOnClickListener(v -> {
            if(!onBind) {
                todoItemsHolder.deleteItem(item);
                setTodoItemList(todoItemsHolder.getCurrentItems());
            }
        });
        onBind =false;
    }


    @Override
    public int getItemCount() {
        return _todoItemList.size();
    }
}
