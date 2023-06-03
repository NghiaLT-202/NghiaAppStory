package com.example.appstory88.ui.morestory

import androidx.lifecycle.MutableLiveData
import com.example.appstory88.R
import com.example.appstory88.base.BaseViewModel
import com.example.appstory88.model.Story

class ViewMoreStoryModel : BaseViewModel() {
    var listStoryLiveData = MutableLiveData<MutableList<Story>>()

    var storyList: MutableList<Story> = mutableListOf()

    override fun onCleared() {
        super.onCleared()
    }

    fun getAllListStory() {
        listStoryLiveData.postValue(storyList)



    }

}