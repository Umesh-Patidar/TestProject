package mytestproj.com.utils

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import mytestproj.com.R


object Dialogs {
    var popupWindow: PopupWindow? = null

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
            Toast.makeText(activity, "Successfully registered..", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        dialog.show()
    }


    fun progressDialog(activity: Activity): Dialog {
        val dialogProgress = Dialog(activity)
        dialogProgress.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogProgress.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialogProgress.setContentView(R.layout.layout_progress_dialog)
        dialogProgress.setCanceledOnTouchOutside(false)
        val icon = dialogProgress.findViewById(R.id.image_progress_icon) as CircleImageView
        Picasso.get().load(R.drawable.ic_active_jobs).into(icon)
        val layoutParams = dialogProgress.window!!.attributes
        val window = dialogProgress.window
        layoutParams.copyFrom(window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = layoutParams
        layoutParams.gravity = Gravity.CENTER
        return dialogProgress
    }


    interface IDialogCallback {
        fun onConfirmed()
        fun onDenied()
    }

    public fun dismissEvent() {
        popupWindow?.dismiss()
    }

}