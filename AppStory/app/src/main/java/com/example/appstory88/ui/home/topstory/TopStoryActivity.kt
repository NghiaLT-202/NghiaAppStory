package com.example.appstory88.ui.home.topstory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import com.example.appstory88.R
import com.example.appstory88.adapter.ItemTopStoryAdapter
import com.example.appstory88.base.BaseBindingActivity
import com.example.appstory88.commom.Constant
import com.example.appstory88.databinding.TopStoryActivityBinding
import com.example.appstory88.model.Story
import com.example.appstory88.ui.MainViewModel
import com.example.appstory88.ui.morestory.ViewMoreStoryActivity

class TopStoryActivity: BaseBindingActivity<TopStoryActivityBinding, TopStoryViewModel>() {
    lateinit var mainViewModel: MainViewModel
    private val listStory: MutableList<Story> = mutableListOf()
     private var itemTopStoryAdapter: ItemTopStoryAdapter?=null
    override fun getLayoutId(): Int {
        return R.layout.top_story_activity
    }
    override fun setupView(savedInstanceState: Bundle?) {
        initListener()
        initAdapter()
    }

    private fun initListener() {


    }

    override fun setupData() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.initData(this)
        mainViewModel.listStoryLiveData.observe(this) { story ->
            listStory.addAll(story)
            itemTopStoryAdapter?.setListStory(story)
        }
    }
    private fun initAdapter() {
        itemTopStoryAdapter=ItemTopStoryAdapter().apply {
            binding.rcItemStory.adapter=this
        }
        itemTopStoryAdapter?.onItemClickListener=object :ItemTopStoryAdapter.ItemClickListener{
            override fun onItemClick(position: Int) {
               intentActivity(ViewMoreStoryActivity::class.java,position)
            }
        }
    }
    override fun getViewModel(): Class<TopStoryViewModel> {
        return TopStoryViewModel::class.java
    }
    private fun intentActivity(activityClass: Class<*>, position: Int) {
        val intent = Intent(this, activityClass)
        intent.putExtra(Constant.CATEGORY_STORY, listStory[position].nameCategory)
        startActivity(intent)

    }
}