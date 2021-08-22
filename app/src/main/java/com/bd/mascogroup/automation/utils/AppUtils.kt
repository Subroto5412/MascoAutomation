package com.bd.mascogroup.automation.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object AppUtils {

    enum class SearchType {
        SIMPLE_SEARCH,
        ADVANCE_SEARCH
    }

    enum class MessageType {
        INTERNET_NOT_FOUND,
        ERROR,
        INFO,
        SUCCESS,
        QUESTION,
        WARNING,
        API_STATUS
    }

    enum class MessageBoxButtons(val type: Int) {
        OK(0),
        OK_CANCEL(1),
        ABORT_RETRY_IGNORE(2),
        YES_NO_CANCEL(3),
        YES_NO(4),
        RETRY_CANCEL(5);

        companion object {
            fun fromInt(value: Int): MessageBoxButtons {
                return values().first { it.type == value }
            }
        }
    }

    enum class MessageBoxDialogResult(val type: Int) {
        NONE(0),
        OK(1),
        CANCEL(2),
        ABORT(3),
        RETRY(4),
        IGNORE(5),
        YES(6),
        NO(7);

        companion object {
            fun fromInt(value: Int): MessageBoxDialogResult {
                return values().first { it.type == value }
            }
        }
    }

    enum class ValidationViewType {
        ACTIVITY,
        FRAGMENT,
        DIALOG
    }
}