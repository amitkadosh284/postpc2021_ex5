package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


// TODO: feel free to add/change/remove methods as you want
public interface TodoItemsHolder {

  List<TodoItem> toDoList = new Vector<>();
  List<TodoItem> doneItems = new Vector<>();

  /** Get a copy of the current items list */
  List<TodoItem> getCurrentItems();

  /**
   * Creates a new TodoItem and adds it to the list, with the @param description and status=IN-PROGRESS
   * Subsequent calls to [getCurrentItems()] should have this new TodoItem in the list
   */
  void addNewInProgressItem(String description);

  /** mark the @param item as DONE */
  void markItemDone(TodoItem item);

  /** mark the @param item as IN-PROGRESS */
  void markItemInProgress(TodoItem item);

  /** delete the @param item */
  void deleteItem(TodoItem item);

}
