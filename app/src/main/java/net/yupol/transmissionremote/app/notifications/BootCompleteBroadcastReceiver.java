package net.yupol.transmissionremote.app.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.yupol.transmissionremote.app.TransmissionRemote;

public class BootCompleteBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        boolean isExpectedAction = Intent.ACTION_BOOT_COMPLETED.equals(action)
                || Intent.ACTION_MY_PACKAGE_REPLACED.equals(action)
                || "android.intent.action.QUICKBOOT_POWERED".equals(action);
        if (isExpectedAction && TransmissionRemote.getApplication(context).isNotificationEnabled()) {
            BackgroundUpdater.start(context);
        }
    }
}
