package exercise.android.reemh.todo_items;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class TodoListApplication extends Application {

    private static TodoListApplication instance;

    private TodoItemsHolderImpl dataBase;

    public  TodoItemsHolderImpl getDataBase(){
        return dataBase;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = new TodoItemsHolderImpl(this);
        instance = this;
    }

    public static TodoListApplication getInstance(){
        return instance;
    }
}
