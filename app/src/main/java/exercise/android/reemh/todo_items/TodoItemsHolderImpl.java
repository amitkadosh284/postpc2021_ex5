package exercise.android.reemh.todo_items;

import java.util.List;
import java.util.Vector;

// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder {
  @Override
  public List<TodoItem> getCurrentItems() {
    List<TodoItem> combine = new Vector<>();
    combine.addAll(doneItems);
    combine.addAll(toDoList);
    if (combine.isEmpty() ){
      return null;
    }
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
