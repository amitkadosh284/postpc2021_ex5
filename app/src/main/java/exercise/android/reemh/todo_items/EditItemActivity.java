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

import androidx.annotation.NonNull;
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

        // getting the instance of the singleton dataBase
        if (dataBase == null) {
            dataBase = TodoListApplication.getInstance().getDataBase();
        }

        Intent intentCreatedMe = getIntent();
        String id = intentCreatedMe.getStringExtra("id");


        // finding the item we want to edit
        item = dataBase.getById(id);

        //finds the view
        TextView creationTime = findViewById(R.id.timeCreation);
        TextView lastModify = findViewById(R.id.lastModify);
        CheckBox checkBox = findViewById(R.id.checkBoxEdit);
        EditText description = findViewById(R.id.descriptionEdit);

        //sets the view with the current item data
        description.setText(item.getDescription());
        creationTime.setText(item.showTimeCreation());
        lastModify.setText(item.getLastModification());
        checkBox.setChecked(item.isDone());


        //sets checkCox change listener
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                dataBase.markItemDone(item);
            }
            else {
                dataBase.markItemInProgress(item);
            }
        });

        //sets description changes listener
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
