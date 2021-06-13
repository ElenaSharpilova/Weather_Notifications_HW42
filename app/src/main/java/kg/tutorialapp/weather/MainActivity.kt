package kg.tutorialapp.weather

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    private lateinit var myAdapter : MyAdapter
    lateinit var button1: Button
    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        createNotificationChannel()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Напоминание")
                .setContentText("Пора в отпуск")
                .setSmallIcon(R.drawable.ic_hiking)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

        val notificationManager = NotificationManagerCompat.from(this)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }

    }

    private fun setup() {
        recyclerView = findViewById(R.id.recyclerView)
        myAdapter = MyAdapter()
        recyclerView.adapter = myAdapter
        myAdapter.setItems(Data.getLongListOfItems())
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel (CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.YELLOW
                enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

    }


}