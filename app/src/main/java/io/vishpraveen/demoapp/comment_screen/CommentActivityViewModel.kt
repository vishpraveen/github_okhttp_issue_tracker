package io.vishpraveen.demoapp.comment_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.repository.GithubIssueCommentDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentActivityViewModel @Inject constructor(
    private val repository: GithubIssueCommentDataSource
): ViewModel() {

    var loader = MutableLiveData<Boolean>()
        private set
    var comments = MutableLiveData<MutableList<IssueCommentsModel>>()
        private set

     fun getComments(commentId: String) {
         loader.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.getComments(commentId).collect {
                loader.postValue(false)
                comments.postValue(it.toMutableList())
            }
        }
    }

}