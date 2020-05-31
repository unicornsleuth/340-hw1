package com.example.lyaho340hw1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserSettingsViewModel extends AndroidViewModel {

    private UserSettingsRepository repo;
    private LiveData<List<UserSettings>> allUserSettings;

    public UserSettingsViewModel (Application application) {
        super(application);
        repo = new UserSettingsRepository(application);
        allUserSettings = repo.getAllUserSettings();
    }

    LiveData<List<UserSettings>> getAllUserSettings() { return allUserSettings; }

    public void insert(UserSettings userSettings) { repo.insert(userSettings); }

    public UserSettings findSettingsByEmail(String email) { return repo.findSettingsByEmail(email); }

}
