package com.example.partyup;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

class FirebaseAuthWithGoogle implements Executor {
    @Override
    public void execute(@NonNull Runnable r) {
        r.run();
    }
}
