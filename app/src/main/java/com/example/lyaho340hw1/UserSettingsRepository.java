package com.example.lyaho340hw1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserSettingsRepository {

    private UserSettingsDao userSettingsDao;
    private LiveData<List<UserSettings>> allUserSettings;
    private UserSettings currentUserSettings;

    UserSettingsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userSettingsDao = db.userSettingsDao();
        allUserSettings = userSettingsDao.getAll();
    }

    LiveData<List<UserSettings>> getAllUserSettings() { return allUserSettings; }

    void insert(UserSettings userSettings) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userSettingsDao.insert(userSettings);
        });
    }

    UserSettings findSettingsByEmail(String email) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            currentUserSettings = userSettingsDao.findByEmail(email);
        });
        return currentUserSettings;
    }

}
