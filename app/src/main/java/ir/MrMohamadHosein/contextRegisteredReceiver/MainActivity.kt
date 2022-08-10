package ir.MrMohamadHosein.contextRegisteredReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.MrMohamadHosein.contextRegisteredReceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // broad cast receiver

        // 1. manifest declared broadcast receiver
        // 2. context registered broadcast receiver

        broadcastReceiver = object :BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

                checkNetworkConnection()

            }
        }

        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(broadcastReceiver , intentFilter )


    }

    private fun checkNetworkConnection() {

        if (NetworkChecker(this).isInternetConnected) {
            binding.txtShowNetwork.text = "Internet Connected!"
        } else {
            binding.txtShowNetwork.text = "Internet Disconnected!"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

}