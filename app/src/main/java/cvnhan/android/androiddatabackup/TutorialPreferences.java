package cvnhan.android.androiddatabackup;

import android.content.SharedPreferences;

/**
 * Created by cvnhan on 16-Apr-15.
 */
public class TutorialPreferences {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public TutorialPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    public String getName(){
        return getString(PreferenceConstants.NAME);
    }

    // If we don't have the String preference - default is to return blank
    private String getString(String name) {
        return sharedPreferences.getString(name, "");
    }

    public void saveName(String name){
        saveString(PreferenceConstants.NAME, name);
    }

    // Always remember to call commit
    private boolean saveString(String key, String value) {
        return editor.putString(key, value).commit();
    }
}
