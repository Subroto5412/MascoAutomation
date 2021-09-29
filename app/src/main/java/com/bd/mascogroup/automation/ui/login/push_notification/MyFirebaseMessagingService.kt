package com.bd.mascogroup.automation.ui.login.push_notification

import android.R
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "FirebaseMessagingService"


    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        Log.e("----------------", "---------Dikirim dari: ${remoteMessage.from}")


        if (remoteMessage.notification != null) {
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("screen",body.toString())
        intent.addCategory(Intent. CATEGORY_LAUNCHER )
        intent.setAction(Intent. ACTION_MAIN )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

//        val icon = BitmapFactory.decodeResource(resources, R.mipmap.notify)

        //  App.data = body.toString()
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
        notificationBuilder.setSmallIcon(R.drawable.ic_notification_clear_all)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle( NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        /* val notificationBuilder = NotificationCompat.Builder(this)
             .setSmallIcon(R.mipmap.ic_launcher)
             .setContentTitle(title)
             .setContentText(body).setLargeIcon(icon1).setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon1))
             .setAutoCancel(true)
             .setSound(soundUri)
             .setContentIntent(pendingIntent)
 */

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

        /*val intent = Intent(this, MessageShow::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.facebook_testing) //            .setContentTitle(title)
                //                        .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        } else {
            notificationBuilder.setSmallIcon(R.mipmap.facebook_testing) //                                .setContentTitle(title)
                //                        .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        }
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())*/
    }
}