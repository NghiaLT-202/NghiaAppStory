package com.example.appstory88.ui.home.topstory

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appstory88.R
import com.example.appstory88.adapter.ItemTopStoryAdapter
import com.example.appstory88.base.BaseBindingActivity
import com.example.appstory88.commom.Constant
import com.example.appstory88.databinding.ActivityTopStoryBinding
import com.example.appstory88.model.Story
import com.example.appstory88.ui.MainViewModel
import com.example.appstory88.ui.home.topstory.detailstorytop.DetailStoryTopActivity
import com.example.appstory88.ui.morestory.ViewMoreStoryActivity

class TopStoryActivity : BaseBindingActivity<ActivityTopStoryBinding, TopStoryViewModel>() {
    private lateinit var mainViewModel: MainViewModel
    private val listStory: MutableList<Story> = mutableListOf()
    private var itemTopStoryAdapter: ItemTopStoryAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_top_story
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
            listStory.clear()
            listStory.addAll(story)
            itemTopStoryAdapter?.listStory = story
        }
    }

    private fun initAdapter() {
        itemTopStoryAdapter = ItemTopStoryAdapter().apply {
            binding.rcItemStory.adapter = this
            onItemClickListener = object : ItemTopStoryAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    intentActivity(listStory[position])
                }
            }
        }
    }

    override fun getViewModel(): Class<TopStoryViewModel> {
        return TopStoryViewModel::class.java
    }

    private fun intentActivity(story: Story) {
        val intent = Intent(this, DetailStoryTopActivity::class.java)
        intent.putExtra(Constant.CATEGORY_STORY, story.nameCategory)
        startActivity(intent)

    }
}