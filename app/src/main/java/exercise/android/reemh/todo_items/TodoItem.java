package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    private boolean isDone = false;
    private String description;

    TodoItem(String description){
      this.description = description;

    }

    boolean isDone(){
      return isDone;
    }

    void setDone(boolean isDone){
      this.isDone = isDone;
    }

    String getDescription(){
      return this.description;
    }

    void setDescription(String description){ this.description = description;}
}
