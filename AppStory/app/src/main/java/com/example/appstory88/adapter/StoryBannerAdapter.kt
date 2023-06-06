package com.example.appstory88.adapter

import android.annotation.SuppressLint
import com.example.appstory88.R
import com.example.appstory88.base.BaseBindingAdapter
import com.example.appstory88.databinding.ItemStoryBannerBinding
import com.example.appstory88.model.Story

class StoryBannerAdapter: BaseBindingAdapter<ItemStoryBannerBinding>() {
    var listStory: MutableList<Story> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field=value
            notifyDataSetChanged()
        }
     var iclick:IClick?=null


    override fun onBindViewHolderBase(holder: BaseHolder<ItemStoryBannerBinding>, position: Int) {
        val story:Story=listStory[position]
        holder.binding.imStory.setImageResource( story.imageStory)
        holder.binding.tvNameStory.text= story.nameStory
        holder.binding.tvValueNameAuthor.text= story.nameAuthur
        holder.binding.tvValueNameCategory.text= story.nameCategory
        holder.binding.viewStar.numberStar= story.numberStar
        holder.itemView.setOnClickListener {
            iclick?.clickItem(story,holder.adapterPosition)

        }

    }

    override fun getLayoutIdItem(): Int {
        return R.layout.item_story_banner
    }

    override fun getSizeItem(): Int {
       return listStory.size
    }
    interface IClick{
     fun   clickItem(story: Story,position: Int)
    }

}