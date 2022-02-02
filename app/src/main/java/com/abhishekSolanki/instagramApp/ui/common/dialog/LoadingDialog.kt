package com.abhishekSolanki.instagramApp.ui.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.abhishekSolanki.instagramApp.R
import kotlinx.android.synthetic.main.message_dialog.view.*


class LoadingDialog : AppCompatDialogFragment() {

    companion object {
        const val TAG = "LoadingDialog"

        const val MESSAGE_KEY = "message_key"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.message_dialog, null)
        arguments?.getInt(MESSAGE_KEY, R.string.loading)?.let { view.tvMessage.setText(it) }
        return AlertDialog.Builder(activity).setView(view).create()
    }
}