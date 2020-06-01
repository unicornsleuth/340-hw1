package com.example.lyaho340hw1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserSettingsRepository {

    private UserSettingsDao userSettingsDao;
    private LiveData<List<UserSettings>> allUserSettings;
    private LiveData<UserSettings> currentUserSettings;

    UserSettingsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userSettingsDao = db.userSettingsDao();
        allUserSettings = userSettingsDao.getAll();
    }

    void insert(UserSettings userSettings) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userSettingsDao.insert(userSettings);
        });
    }

    LiveData<UserSettings> findSettingsByEmail(String email) {
        return userSettingsDao.findByEmail(email);
    }

}
