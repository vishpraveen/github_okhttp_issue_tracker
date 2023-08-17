package io.vishpraveen.demoapp.issue_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.repository.GithubIssueDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: GithubIssueDataSource
): ViewModel() {

    var loader = MutableLiveData<Boolean>()
        private set
    var issues = MutableLiveData<MutableList<IssueDetailModel>>()
        private set

    init {
        getIssues()
    }

    private fun getIssues() {
        loader.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.getIssues().collect {
                loader.postValue(false)
                issues.postValue(it as MutableList<IssueDetailModel>)
            }
        }
    }

}