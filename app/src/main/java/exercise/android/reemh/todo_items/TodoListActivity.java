package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

  public TodoItemsHolder dataBase = null;
  public ItemAdapter adapter = null;


  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (dataBase == null) {
      dataBase = TodoListApplication.getInstance().getDataBase();
    }

    //finds the views
    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    EditText editTextInsertTask = findViewById(R.id.editTextInsertTask);
    FloatingActionButton buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);

    adapter = new ItemAdapter(dataBase);

    //sets the view
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.setVisibility(View.VISIBLE);
    buttonCreateTodoItem.setEnabled(true);
    editTextInsertTask.setVisibility(View.VISIBLE);

    //set observer to the liveData
    dataBase.getPublicItemsLiveData().observe(this, todoItems -> {
      adapter.setTodoItemList(todoItems);
    });


            //set listener to create item button
    buttonCreateTodoItem.setOnClickListener(v -> {
      String input = String.valueOf(editTextInsertTask.getText());
      if (!input.isEmpty()){
        dataBase.addNewInProgressItem(input);
        adapter.setTodoItemList(dataBase.getCurrentItems());
        editTextInsertTask.setText("");
      }
    });

  }



}



