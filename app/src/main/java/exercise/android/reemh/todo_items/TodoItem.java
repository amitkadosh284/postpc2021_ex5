package exercise.android.reemh.todo_items;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class TodoItem implements Serializable {
    private boolean isDone = false;
    private String description;
    private String timeCreation;
    private String lastModification;
    private String id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    TodoItem(String description, String timeCreation, String id){
      this.description = description;
      this.timeCreation = timeCreation;
      this.lastModification = getLastModification();
      this.id = id;
    }


    /**
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getLastModification(){
        try{
            String currentTime = LocalDateTime.now().toString();

            String[] splitDateTimeCurrent = currentTime.split("T");
            String[] splitDateTimeModify = lastModification.split("T");

            String dateCurrent = splitDateTimeCurrent[0];
            String dateModify = splitDateTimeModify[0];

            String[] timeCurrentSplit = splitDateTimeCurrent[1].split(":");
            String[] timeModifySplit = splitDateTimeModify[1].split(":");

            Integer hourCurrent = Integer.valueOf(timeCurrentSplit[0]);
            Integer hourModify = Integer.valueOf(timeModifySplit[0]);

            Integer minuteCurrent = Integer.valueOf(timeCurrentSplit[1]);
            Integer minuteModify = Integer.valueOf(timeModifySplit[1]);

            if (dateCurrent.equals(dateModify)){
                Integer diffHour = hourCurrent - hourModify;
                if (diffHour == 0){
                    Integer diffMinutes = minuteCurrent - minuteModify;
                    return diffMinutes + " minutes ago";
                }
                return "Today at " + hourModify;
            }
            return dateModify + " at " + hourModify;
        } catch (Exception e){
            System.out.println("error string of last modify");
            return "";
        }
    }

    void setLastModification(String modification){
        this.lastModification = modification;
    }

    String getTimeCreation(){
        return timeCreation;
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

    String getId(){
        return id;
    }

    String getStringRepresentation(){
        String doneString = "false";
        if (isDone){
            doneString = "true";
        }
        return doneString + "#" + description + "#" + timeCreation + "#" + lastModification + "#" + id;
    }

}
