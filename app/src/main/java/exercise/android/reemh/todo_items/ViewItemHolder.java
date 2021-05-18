package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewItemHolder extends RecyclerView.ViewHolder {
    private TextView description;
    private CheckBox checkBox;


    public ViewItemHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        checkBox = itemView.findViewById(R.id.checkBox);
    }

    void setDescription(String description){
        this.description.setText(description);
    }

    void setCheckBox(boolean isDone){
        checkBox.setChecked(isDone);
    }
}
