package com.example.appstory88.ui.morestory

import android.content.Intent

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.appstory88.R
import com.example.appstory88.adapter.StoryAdapter
import com.example.appstory88.base.BaseBindingActivity

import com.example.appstory88.commom.Constant

import com.example.appstory88.databinding.LayoutViewListMoreStoryBinding
import com.example.appstory88.model.Story
import com.example.appstory88.ui.MainViewModel
import com.example.appstory88.ui.describestory.ViewDescribeStoryActivity

class ViewMoreStoryActivity :
    BaseBindingActivity<LayoutViewListMoreStoryBinding, ViewMoreStoryModel>() {
    private var storyAdapter: StoryAdapter? = null
    private var mainViewModel: MainViewModel? = null
    private var listStory: MutableList<Story> = mutableListOf()
    override fun getLayoutId(): Int {
        return R.layout.layout_view_list_more_story
    }
    override fun setupView(savedInstanceState: Bundle?) {
        binding.imBack.setOnClickListener {
            finish()
        }
        initAdapter()
    }

    override fun setupData() {
        val category = intent.getStringExtra(Constant.CATEGORY_STORY)
        binding.nameCategory.text = category
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel?.initData(this)
        mainViewModel?.listStoryLiveData?.observe(this) { story ->
            listStory.addAll(story)
            listStory = listStory.filter {
                it.nameCategory.equals(
                    category,
                    ignoreCase = true
                )
            } as MutableList<Story>
            storyAdapter?.setListStory(listStory)
        }
    }


    override fun getViewModel(): Class<ViewMoreStoryModel> {
        return ViewMoreStoryModel::class.java
    }


    private fun initAdapter() {
        storyAdapter = StoryAdapter().apply {
            binding.rcListStory.adapter = this
        }
        storyAdapter?.onItemClickListener = object : StoryAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                intentActivityAndData(listStory[position])
            }

        }

    }

    private fun intentActivityAndData(story: Story) {
        val intent = Intent(this, ViewDescribeStoryActivity::class.java)
        Log.e("tnghia",""+story.nameStory)
        intent.putExtra(Constant.IMAGE_STORY, story.imageStory)
        intent.putExtra(Constant.NAME_STORY, story.nameStory)
        intent.putExtra(Constant.NAME_AUTHUR_STORY, story.nameAuthur)
        intent.putExtra(Constant.NUMBER_STAR_STORY, story.numberStar)
        intent.putExtra(Constant.NUMBER_VIEW_STORY, story.numberView)
        intent.putExtra(Constant.STATUS_STORY, story.status)
        intent.putExtra(Constant.CATEGORY_STORY, story.nameCategory)
        intent.putExtra(Constant.DESCRIBE_STORY, story.describe)
        intent.putExtra(Constant.CHAPTER_STORY, story.chapter)
        intent.putExtra(Constant.CHAPTER_SUM_STORY, story.chapterSum)
        startActivity(intent)

    }
}