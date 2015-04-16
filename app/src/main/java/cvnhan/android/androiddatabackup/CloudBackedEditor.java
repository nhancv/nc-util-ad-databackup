package cvnhan.android.androiddatabackup;

import android.app.backup.BackupManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

/**
 * Created by cvnhan on 16-Apr-15.
 */
public class CloudBackedEditor implements SharedPreferences.Editor {
    private final Editor editor;
    private final BackupManager backupManager;

    public CloudBackedEditor(Editor editor, BackupManager backupManager) {
        this.editor = editor;
        this.backupManager = backupManager;
    }

    @Override
    public void apply() {
        throw new UnsupportedOperationException("Just a tutorial");
    }

    @Override
    public Editor clear() {
        editor.clear();
        return this;
    }

    @Override
    public boolean commit() {
        boolean commit = editor.commit();
        backupManager.dataChanged();
        return commit;
    }

    @Override
    public Editor putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return this;
    }

    @Override
    public Editor putFloat(String key, float value) {
        editor.putFloat(key, value);
        return this;
    }

    @Override
    public Editor putInt(String key, int value) {
        editor.putInt(key, value);
        return this;
    }

    @Override
    public SharedPreferences.Editor putLong(String key, long value) {
        editor.putLong(key, value);
        return this;
    }

    @Override
    public SharedPreferences.Editor putString(String key, String value) {
        editor.putString(key, value);
        return this;
    }

    @Override
    public SharedPreferences.Editor putStringSet(String arg0, Set<String> arg1) {
        throw new UnsupportedOperationException("Just a tutorial");
    }

    @Override
    public SharedPreferences.Editor remove(String key) {
        editor.remove(key);
        return this;
    }
}
