package net.yupol.transmissionremote.app;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public abstract class BaseActivity extends AppCompatActivity {

    private final OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            handleBackPressed();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOnBackPressedDispatcher().addCallback(this, backPressedCallback);
    }

    protected void handleBackPressed() {
        boolean handled = handleBackPressByFragments();
        if (!handled) navigateBack();
    }

    protected final void navigateBack() {
        backPressedCallback.setEnabled(false);
        getOnBackPressedDispatcher().onBackPressed();
        backPressedCallback.setEnabled(true);
    }

    /**
     * @return {@code true} if back press handled by visible fragments
     */
    protected boolean handleBackPressByFragments() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof OnBackPressedListener && fragment.isVisible()) {
                boolean handled = ((OnBackPressedListener) fragment).onBackPressed();
                if (handled) return true;
            }
        }
        return false;
    }
}
