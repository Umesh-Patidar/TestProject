package mytestproj.com.utils

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import mytestproj.com.R

object Dialogs {

    fun getDialog(view: Int, activity: Activity): Dialog {
        val dialog = Dialog(activity)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(R.color.colorOpaque)
        dialog.setContentView(view)

        val layoutParams = dialog.window!!.attributes
        val window = dialog.window

        layoutParams.copyFrom(window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = layoutParams
        layoutParams.gravity = Gravity.CENTER

        return dialog
    }

    fun showSuccessDialog(activity: Activity, iDialogCallback: IDialogCallback) {
        val dialog = getDialog(R.layout.dialog_custom_alert, activity)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation

        val buttonCancel = dialog.findViewById<Button>(R.id.button_cancel)
        buttonCancel.setOnClickListener {
            dialog.dismiss()
            iDialogCallback.onConfirmed()
        }

        val buttonSave = dialog.findViewById<Button>(R.id.button_save)
        buttonSave.setOnClickListener {
            Toast.makeText(activity, "Successfully submited..", Toast.LENGTH_LONG).show()
        }

        dialog.show()
    }

    interface IDialogCallback {
        fun onConfirmed()
        fun onDenied()
    }

}