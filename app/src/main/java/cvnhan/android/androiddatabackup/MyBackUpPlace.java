package cvnhan.android.androiddatabackup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * Created by cvnhan on 15-Apr-15.
 */
public class MyBackUpPlace extends BackupAgentHelper{
    @Override
    public void onCreate() {
        // A Helper for our Preferences, this name is the same name we use when saving SharedPreferences
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PreferenceConstants.TUTORIAL_PREFERENCES);
        addHelper(PreferenceConstants.HELPER_KEY, helper);
    }

}
