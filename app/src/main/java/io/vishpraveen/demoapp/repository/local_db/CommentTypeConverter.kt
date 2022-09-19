package io.vishpraveen.demoapp.repository.local_db

import androidx.room.TypeConverter
import io.vishpraveen.demoapp.model.IssueCommentsModel
import org.json.JSONArray
import org.json.JSONObject

class CommentTypeConverter {

    @TypeConverter
    fun listOfCommentsToString(comments: List<IssueCommentsModel>): String {
        val commentArray: JSONArray = JSONArray()
        comments.forEach {
            commentArray.put(commentsToString(it))
        }
        return commentArray.toString()
    }

    @TypeConverter
    fun stringOfArrayToSComments(comments: String): MutableList<IssueCommentsModel> {
        val comments = JSONArray(comments)
        val commentList: MutableList<IssueCommentsModel> = mutableListOf()
        for (item in 0 until comments.length()) {
            commentList.add(stringToComments(comments[item].toString()))
        }
        return commentList
    }

    @TypeConverter
    fun commentsToString(commentsModel: IssueCommentsModel): String {
        return JSONObject().apply {
            put("body", commentsModel.body)
            put("updated_at", commentsModel.updatedAt)
            put("user", JSONObject().apply {
                put("login", commentsModel.user?.login)
                put("avatar_url", commentsModel.user?.avatar_url)
            }).toString()
        }.toString()
    }

    @TypeConverter
    fun stringToComments(value: String): IssueCommentsModel {
        val json = JSONObject(value)
        return IssueCommentsModel(
            body = json.optString("body"),
            updatedAt = json.optString("updated_at"),
            user = UserTypeConverter().stringToUser(json.optJSONObject("user").toString())
        )
    }
}