package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewItemHolder extends RecyclerView.ViewHolder {
    private TextView description;
    private CheckBox checkBox;
    private ImageButton trash;


    public ViewItemHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.descriptionEdit);
        checkBox = itemView.findViewById(R.id.checkBox);
        trash = itemView.findViewById(R.id.trash);
    }

    void setDescription(String description){
        this.description.setText(description);
    }

    void setCheckBox(boolean isDone){
        checkBox.setChecked(isDone);
    }

    ImageButton getTrash() { return trash; }

    TextView getDescription(){
        return description;
    }

    CheckBox getCheckBox(){
        return checkBox;
    }
}
