package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolder holder = null;
  public ItemAdapter adapter = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null) {
      holder = new TodoItemsHolderImpl();
    }

    //finds the views
    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    EditText editTextInsertTask = findViewById(R.id.editTextInsertTask);
    FloatingActionButton buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);

    adapter = new ItemAdapter(holder);

    //sets the view
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.setVisibility(View.VISIBLE);
    buttonCreateTodoItem.setEnabled(true);
    editTextInsertTask.setVisibility(View.VISIBLE);

    buttonCreateTodoItem.setOnClickListener(v -> {
      String input = String.valueOf(editTextInsertTask.getText());
      if (!input.isEmpty()){
        holder.addNewInProgressItem(input);
        adapter.setTodoItemList(holder.getCurrentItems());
        editTextInsertTask.setText("");
      }
    });

  }

  @Override
  protected void onSaveInstanceState (@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("holder", holder);
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savesInstanceState){
    super.onRestoreInstanceState(savesInstanceState);
    holder = (TodoItemsHolder) savesInstanceState.get("holder");
    adapter.setTodoItemList(holder.getCurrentItems());
  }

}



