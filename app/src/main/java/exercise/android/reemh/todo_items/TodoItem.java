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
    private final String timeCreation;
    private String lastModification;
    private final String id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    TodoItem(String description, String timeCreation, String id){
        this.id = id;
        this.description = description;
        this.timeCreation = timeCreation;
        this.lastModification = LocalDateTime.now().toString();
        System.out.println(lastModification);
    }


    /**
     * this function return the last modification time according to spec
     * @return string represent the last modification time of the item
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getLastModification(){
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
                return "Last modification: " + diffMinutes + " minutes ago";
            }
            return "Last modification: Today at " + hourModify;
        }
        return "Last modification: " + dateModify + " at " + hourModify;
    }

    /**
     * set the last modification time
     * @param modification - the time of last modification
     */
    void setLastModification(String modification){
        this.lastModification = modification;
    }

    /***
     *
     * @return the creation time of the item
     */
    String getTimeCreation(){
        return timeCreation;
    }

    /**
     *
     * @return the state of the item if is done(true) or in-progress(false).
     */
    boolean isDone(){
      return isDone;
    }

    /**
     * set the state of the item
     * @param isDone - the new state
     */
    void setDone(boolean isDone){
      this.isDone = isDone;
    }

    /**
     *
     * @return the description of the item
     */
    String getDescription(){
      return this.description;
    }

    /**
     * set the description of the item
     * @param description - the new description
     */
    void setDescription(String description){ this.description = description;}

    /**
     *
     * @return the id of the item
     */
    String getId(){
        return id;
    }

    /**
     * this function create a sting that represents the item includes all is current fielda
     * separate by #
     * @return the string represent the item
     */
    String getStringRepresentation(){
        String doneString = "false";
        if (isDone){
            doneString = "true";
        }
        return doneString + "#" + description + "#" + timeCreation + "#" + lastModification + "#" + id;
    }

    String showTimeCreation(){
        String time = this.timeCreation;
        String[] split = time.split("T");
        return "Created at: " + split[0] + " at " + split[1];
    }

}
