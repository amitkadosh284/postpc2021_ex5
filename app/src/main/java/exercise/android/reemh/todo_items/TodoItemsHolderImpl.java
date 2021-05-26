package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  private final List<TodoItem> toDoList = new Vector<>();
  private final List<TodoItem> doneItems = new Vector<>();
  private transient final SharedPreferences sp;

  private transient final MutableLiveData<List<TodoItem>> privateItemsLivaData = new MutableLiveData<>();
  public transient final LiveData<List<TodoItem>> publicItemsLiveData = privateItemsLivaData;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public TodoItemsHolderImpl(Context context) {
    sp = context.getSharedPreferences("local_db_todo_list", Context.MODE_PRIVATE);
    initializeFromSp();
  }


  @RequiresApi(api = Build.VERSION_CODES.O)
  private void initializeFromSp() {
    Set<String> keys = sp.getAll().keySet();
    for (String key: keys) {
      String itemSaveAsString = sp.getString(key, null);
      System.out.println("****************************************************************************");
      System.out.println(itemSaveAsString);
      System.out.println("****************************************************************************");
      TodoItem item = stringToItem(itemSaveAsString);
      if (item != null){
        if (item.isDone()){
          doneItems.add(item);
        }
        else {
          toDoList.add(item);
        }
      }
    }
    privateItemsLivaData.setValue(getCurrentItems());
  }

  /**
   * this function make a TodoItem from string
   * @param itemSaveAsString = the string represent the item
   * @return a TodoItem withe the data from the string
   */
  @RequiresApi(api = Build.VERSION_CODES.O)
  private TodoItem stringToItem(String itemSaveAsString) {
    if (itemSaveAsString == null){
      return null;
    }
    String[] splitItem = itemSaveAsString.split("#");
    String isDone = splitItem[0];
    String description = splitItem[1];
    String timeCreation = splitItem[2];
    String lastModification = splitItem[3];
    String id = splitItem[4];
    TodoItem newItem = new TodoItem(description, timeCreation, id);
    if (isDone.equals("true")){
      newItem.setDone(true);
    }
    newItem.setLastModification(lastModification);
    return newItem;
  }


  @Override
  public LiveData<List<TodoItem>> getPublicItemsLiveData(){
    return this.publicItemsLiveData;
  }

  @Override
  public TodoItem getById(String id) {
    for (TodoItem item: toDoList) {
        if (item.getId().equals(id)){
          return item;
        }
    }
    for (TodoItem item: doneItems) {
      if (item.getId().equals(id)){
        return item;
      }
    }
    return null;
  }

  @Override
  public List<TodoItem> getCurrentItems() {
    List<TodoItem> combine = new Vector<>();
    combine.addAll(doneItems);
    combine.addAll(toDoList);
    Collections.reverse(combine);
    System.out.println();
    return combine;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void addNewInProgressItem(String description) {
    TodoItem item = new TodoItem(description, LocalDateTime.now().toString() , UUID.randomUUID().toString());
    toDoList.add(item);

    privateItemsLivaData.setValue(getCurrentItems());

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(item.getId(), item.getStringRepresentation());
    editor.apply();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void markItemDone(TodoItem item) {
    TodoItem newItem = new TodoItem(item.getDescription(), item.getTimeCreation(), item.getId());
    newItem.setDone(true);
    doneItems.add(newItem);

    deleteItem(item);

    privateItemsLivaData.setValue(getCurrentItems());

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newItem.getId(), newItem.getStringRepresentation());
    editor.apply();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void markItemInProgress(TodoItem item) {
    TodoItem newItem = new TodoItem(item.getDescription(), item.getTimeCreation(), item.getId());
    newItem.setDone(false);
    toDoList.add(newItem);

    deleteItem(item);

    privateItemsLivaData.setValue(getCurrentItems());

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newItem.getId(), newItem.getStringRepresentation());
    editor.apply();
  }

  @Override
  public void deleteItem(TodoItem item) {
    if (item.isDone()){
      doneItems.remove(item);
    }
    else{
      toDoList.remove(item);
    }

    privateItemsLivaData.setValue(getCurrentItems());

    SharedPreferences.Editor editor = sp.edit();
    editor.remove(item.getId());
    editor.apply();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void editDescription(TodoItem item, String description) {
    deleteItem(item);
    TodoItem newItem = new TodoItem(description, item.getTimeCreation(), item.getId());
    newItem.setDone(item.isDone());
    if (newItem.isDone()){
      doneItems.add(newItem);
    }
    else {
      toDoList.add(newItem);
    }

    privateItemsLivaData.setValue(getCurrentItems());

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newItem.getId(), newItem.getStringRepresentation());
    editor.apply();
  }

}
