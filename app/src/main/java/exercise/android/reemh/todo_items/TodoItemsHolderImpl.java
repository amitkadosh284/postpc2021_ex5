package exercise.android.reemh.todo_items;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  List<TodoItem> toDoList = new Vector<>();
  List<TodoItem> doneItems = new Vector<>();


  @Override
  public List<TodoItem> getCurrentItems() {
    List<TodoItem> combine = new Vector<>();
    combine.addAll(doneItems);
    combine.addAll(toDoList);
    Collections.reverse(combine);
    return combine;
  }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem item = new TodoItem(description);
    toDoList.add(item);
  }

  @Override
  public void markItemDone(TodoItem item) {
    item.setDone(true);
    doneItems.add(item);
    toDoList.remove(item);
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.setDone(false);
    toDoList.add(item);
    doneItems.remove(item);
  }

  @Override
  public void deleteItem(TodoItem item) {
    if (item.isDone()){
      doneItems.remove(item);
    }
    toDoList.remove(item);
  }

}
