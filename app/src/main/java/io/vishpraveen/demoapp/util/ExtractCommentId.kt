package io.vishpraveen.demoapp.util

import android.util.Log
import io.vishpraveen.demoapp.BuildConfig

object ExtractCommentId {
    fun getCommentId(comment: String): String {
        return comment.replace("${BuildConfig.BASE_URL}$ISSUE_LINK", EMPTY)
            .replace(COMMENTS, EMPTY)
    }

    private const val EMPTY = ""
    private const val COMMENTS= "/comments"
    private const val ISSUE_LINK= "repos/square/okhttp/issues/"
}