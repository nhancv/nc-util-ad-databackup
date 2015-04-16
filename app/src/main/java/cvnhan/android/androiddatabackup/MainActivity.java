package cvnhan.android.androiddatabackup;

import android.app.backup.BackupManager;
import android.app.backup.RestoreObserver;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private EditText inputEditText;
    private TextView displayTextView;
    private TutorialPreferences pref;
    BackupManager backupManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = (EditText) findViewById(R.id.main_edit_text_input);
        displayTextView = (TextView) findViewById(R.id.main_text_view_display);

        // Setup your shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(PreferenceConstants.TUTORIAL_PREFERENCES, MODE_PRIVATE);
        // Backup manager is what tells the android system about your preferences
        backupManager = new BackupManager(this);
        // We wrap our shared preferences in another sharedpreferences that will also inform the backup manager
        CloudBackedSharedPreferences preferences = new CloudBackedSharedPreferences(sharedPreferences, backupManager);
        // We then wrap again so we can have convenience methods that describe what you are saving (keeping your activity cleaner)
        pref = new TutorialPreferences(preferences);
        // Set the name from shared preferences - on the very first install this will be blank, then from then on it will be whatever the user entered
        displayTextView.setText(pref.getName());


    }

    public void onChangeInput(View button) {
        String input = inputEditText.getText().toString();
        if (!"".equals(input)) {
            displayTextView.setText(input);
        }
    }

    public void onSaveInput(View button) {
        String input = inputEditText.getText().toString();
        // Check the user has entered some text
        if (!"".equals(input)) {
            // Update the UI
            displayTextView.setText(input);
            // Save to shared preferences
            pref.saveName(input);
        }
    }

    public void onRestoreInput(View button) {
        backupManager.requestRestore(new RestoreObserver() {
            @Override
            public void restoreFinished(int error) {
                super.restoreFinished(error);
                Log.e("Restore finished", error + "");
            }

            @Override
            public void restoreStarting(int numPackages) {
                super.restoreStarting(numPackages);
                Log.e("Restore restoreStarting", numPackages + "");
            }

            @Override
            public void onUpdate(int nowBeingRestored, String currentPackage) {
                super.onUpdate(nowBeingRestored, currentPackage);
                Log.e("Restore update", currentPackage + " - " + nowBeingRestored);
            }
        });
        displayTextView.setText(pref.getName());
    }
}
