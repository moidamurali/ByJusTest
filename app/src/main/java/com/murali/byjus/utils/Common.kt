package com.murali.byjus.utils

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.murali.byjus.R
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class Common {
    companion object {

        fun getCurrentDate(): String {
            val date: Date = Calendar.getInstance().getTime();
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return sdf.format(date);
        }

        fun convertDate(value: String): String {
            val resultdate = value.substring(0, 10)

            val fmt = SimpleDateFormat("yyyy-MM-dd")
            val date = fmt.parse(resultdate)

            val fmtOut = SimpleDateFormat("yyyy:MM:dd")
            return fmtOut.format(date)
        }

        fun isOnline(context: Context): Boolean {
            val conMgr = context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = conMgr.activeNetworkInfo
            if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
                Toast.makeText(context, "No Internet connection!", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun showNetworkAlert(context: Context) {

            val builder = AlertDialog.Builder(context)
            //set title for alert dialog
            builder.setTitle(R.string.alert_title)
            //set message for alert dialog
            builder.setMessage(R.string.alert_description)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Ok") { dialogInterface, which ->
                dialogInterface.dismiss()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }

}