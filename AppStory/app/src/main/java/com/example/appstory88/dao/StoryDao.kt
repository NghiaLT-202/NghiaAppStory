package com.example.appstory88.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appstory88.model.Story

@Dao
interface StoryDao {
    @Query("SELECT * FROM STORY")
    fun getAllStory(): MutableList<Story>

    @Insert
    fun insertListStory(listStory: MutableList<Story>)

    @Insert
    fun insertStory(story: Story)
}