package exercise.android.reemh.todo_items;

import androidx.lifecycle.LiveData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public interface TodoItemsHolder extends Serializable {


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

  /**edit the @param item's description */
   void editDescription(TodoItem item, String description);

   /** get the live data */
  LiveData<List<TodoItem>> getPublicItemsLiveData();

  /** get TodoItem by Id */
  TodoItem getById(String id);

}
