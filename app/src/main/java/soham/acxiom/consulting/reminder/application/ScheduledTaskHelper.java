package soham.acxiom.consulting.reminder.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ScheduledTaskHelper {

    private static final String TAG = "test->SchedTaskH";

    public static void scheduleEmail(Context context, long timestamp, String recipient, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, emailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC, timestamp, pendingIntent);
    }


    public static void scheduleSMS(Context context, long timestamp, String phoneNumber, String message) {
        Intent smsIntent = new Intent(context, SMSService.class);
        smsIntent.putExtra("phone_number", phoneNumber);
        smsIntent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, smsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC, timestamp, pendingIntent);
    }
}
