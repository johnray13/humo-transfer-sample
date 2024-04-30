package ru.appsmile.humo_transfer_sample.extra

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import ru.appsmile.humo_transfer_sample.databinding.ExitAppBinding

object ExitApp {
    fun showDialog(context: Context, listener: Listener) {
        var exitdialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)

        val binding = ExitAppBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            exitButton.setOnClickListener {
                listener.onClick()
                exitdialog?.dismiss()
            }
            cancelButton.setOnClickListener {
                exitdialog?.dismiss()
            }
        }
        exitdialog = builder.create()
        exitdialog.window?.setBackgroundDrawable(null)
        exitdialog.show()
    }

    interface Listener {
        fun onClick()
    }

}