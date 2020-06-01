package com.example.lyaho340hw1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserSettingsViewModel extends AndroidViewModel {

    private UserSettingsRepository repo;

    public UserSettingsViewModel (Application application) {
        super(application);
        repo = new UserSettingsRepository(application);
    }

    public void insert(UserSettings userSettings) { repo.insert(userSettings); }

    public LiveData<UserSettings> findSettingsByEmail(String email) { return repo.findSettingsByEmail(email); }

}
