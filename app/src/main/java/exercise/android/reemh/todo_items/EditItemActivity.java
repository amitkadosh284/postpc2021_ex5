package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class EditItemActivity extends AppCompatActivity {

    public TodoItemsHolder dataBase = null;
    TodoItem item = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        if (dataBase == null) {
            dataBase = TodoListApplication.getInstance().getDataBase();
        }

        Intent intentCreatedMe = getIntent();
        String id = intentCreatedMe.getStringExtra("id");

        item = dataBase.getById(id);

        TextView creationTime = findViewById(R.id.timeCreation);
        TextView lastModify = findViewById(R.id.lastModify);
        CheckBox checkBox = findViewById(R.id.checkBox);
        EditText description = findViewById(R.id.descriptionEdit);

        description.setText(item.getDescription());
        creationTime.setText(item.getTimeCreation());
        lastModify.setText(item.getLastModification());
        checkBox.setChecked(item.isDone());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                dataBase.markItemDone(item);
            }
            else {
                dataBase.markItemInProgress(item);
            }
        });

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataBase.editDescription(item, description.getText().toString());
                lastModify.setText(item.getLastModification());
            }
        });




    }
}
