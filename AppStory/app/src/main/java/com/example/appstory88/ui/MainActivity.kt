package com.example.appstory88.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.appstory88.R
import com.example.appstory88.adapter.StoryAdapter
import com.example.appstory88.adapter.StoryBanerAdapter
import com.example.appstory88.base.BaseBindingActivity
import com.example.appstory88.commom.Constant
import com.example.appstory88.databinding.HomeActivityBinding
import com.example.appstory88.model.Story
import com.example.appstory88.ui.describestory.ViewDescribeStoryActivity
import com.example.appstory88.ui.home.category.CategoryStoryActicity
import com.example.appstory88.ui.home.ratestory.RateStoryActivity
import com.example.appstory88.ui.home.topstory.TopStoryActivity
import com.example.appstory88.ui.morestory.ViewMoreStoryActivity

class MainActivity : BaseBindingActivity<HomeActivityBinding, MainViewModel>() {
    private val listStory: MutableList<Story> = mutableListOf()
    private var storyNewUpdateAdapter: StoryAdapter? = null
    private val listStoryNewUpdate: MutableList<Story> = mutableListOf()

    private var storyFullAdapter: StoryAdapter? = null
    private val listStoryFull: MutableList<Story> = mutableListOf()

    private var storyGoodLoveLanguageAdapter: StoryAdapter? = null
    private val listStoryGoodLoveLanguage: MutableList<Story> = mutableListOf()

    private var storyGoodFairyTaleAdapter: StoryAdapter? = null
    private val listStoryGoodFairyTale: MutableList<Story> = mutableListOf()

    private var storyGoodPassionAdapter: StoryAdapter? = null
    private val listStoryGoodPassion: MutableList<Story> = mutableListOf()

    private var storyBannerAdapter: StoryBanerAdapter? = null
    private val listStoryBanner: MutableList<Story> = mutableListOf()


    override fun getLayoutId(): Int {
        return R.layout.home_activity
    }

    override fun setupView(savedInstanceState: Bundle?) {
        initAdapter()
        initListener()
    }

    override fun setupData() {
        initData()
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    private fun initData() {
        viewModel.initData(this)
        viewModel.listStoryLiveData.observe(this) { story ->
            listStory.clear()
            listStoryBanner.clear()

            listStory.addAll(story)
            listStoryBanner.addAll(story)

            viewModel.initlistStoryNewUpdateLiveData(listStory, this)
            viewModel.initlistStoryFullAdapterLiveData(listStory, this)
            viewModel.initlistStoryGoodLoveLanguageLiveData(listStory, this)
            viewModel.initlistStoryGoodFairyTaleLiveData(listStory, this)
            viewModel.initlistStoryGoodPassionLiveData(listStory, this)
            storyBannerAdapter?.setListStoryBanner(story)
        }
        viewModel.listStoryNewUpdateLiveData.observe(this) { newUpdate ->
            listStoryNewUpdate.clear()
            listStoryNewUpdate.addAll(newUpdate)
            storyNewUpdateAdapter?.setListStory(newUpdate)
        }
        viewModel.listStoryFullAdapterLiveData.observe(this) { storyFull ->
            listStoryFull.clear()
            listStoryFull.addAll(storyFull)

            storyFullAdapter?.setListStory(storyFull)

        }
        viewModel.listStoryGoodLoveLanguageLiveData.observe(this) { goodLove ->
            listStoryGoodLoveLanguage.clear()
            listStoryGoodLoveLanguage.addAll(goodLove)

            storyGoodLoveLanguageAdapter?.setListStory(goodLove)
        }
        viewModel.listStoryGoodFairyTaleLiveData.observe(this) { goodFairyTale ->
            listStoryGoodFairyTale.clear()
            listStoryGoodFairyTale.addAll(goodFairyTale)
            storyGoodFairyTaleAdapter?.setListStory(goodFairyTale)

        }
        viewModel . listStoryGoodPassionLiveData . observe (this) { goodPassion ->
            listStoryGoodPassion.clear()
            listStoryGoodPassion.addAll(goodPassion)
            storyGoodPassionAdapter?.setListStory(goodPassion)

        }
    }


    private fun initListener() {
        binding.viewTopStory.setOnClickListener {
            intentActivity(TopStoryActivity::class.java, 0)

        }
        binding.viewCategory.setOnClickListener {
            intentActivity(CategoryStoryActicity::class.java, 0)
        }
        binding.viewRating.setOnClickListener {
            intentActivity(RateStoryActivity::class.java, 0)
        }
        binding.imViewMoreNewStoryUpdated.setOnClickListener {
            intentActivity(ViewMoreStoryActivity::class.java, 0)

        }
        binding.imViewMoreStoryFull.setOnClickListener {
            intentActivity(ViewMoreStoryActivity::class.java, 1)

        }
        binding.imViewMoreGoodLoveLanguage.setOnClickListener {
            intentActivity(ViewMoreStoryActivity::class.java, 2)
        }

        binding.imViewMoreGoodFairyTale.setOnClickListener {
            intentActivity(ViewMoreStoryActivity::class.java, 3)
        }
        binding.imViewMoreGoodPassion.setOnClickListener {
            intentActivity(ViewMoreStoryActivity::class.java, 4)

        }


    }


    private fun initAdapter() {
        storyBannerAdapter()
        storyNewUpdateAdapter(binding.rcItemStoryUpdateNew)
        storyFullAdapter(binding.rcItemStoryFull)
        storyGoodLoveLanguageAdapter(binding.rcItemGoodLoveLanguage)
        storyGoodFairyTaleAdapter(binding.rcItemGoodFairyTale)
        storyGoodPassionAdapter(binding.rcItemGoodPassion)


    }

    private fun storyBannerAdapter() {
        storyBannerAdapter = StoryBanerAdapter().apply {
            binding.rcItemStoryBanner.adapter = this
            iclick = object : StoryBanerAdapter.Iclick {
                override fun clickItem(position: Int) {
                    intentActivityAndData(listStory[position])
                }
            }
        }

    }




    private fun storyNewUpdateAdapter(rcItem: RecyclerView) {
        storyNewUpdateAdapter = StoryAdapter().apply {
            rcItem.adapter = this
            onItemClickListener = object : StoryAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    intentActivityAndData(listStoryNewUpdate[position])

                }

            }
        }

    }
    private fun storyFullAdapter(rcItem: RecyclerView) {
        storyFullAdapter = StoryAdapter().apply {
            rcItem.adapter = this
            onItemClickListener = object : StoryAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    intentActivityAndData(listStoryFull[position])
                }
            }

        }

    }

    private fun storyGoodLoveLanguageAdapter(rcItem: RecyclerView) {
        storyGoodLoveLanguageAdapter = StoryAdapter().apply {
            rcItem.adapter = this
            onItemClickListener =
                object : StoryAdapter.ItemClickListener {
                    override fun onItemClick(position: Int) {
                        intentActivityAndData(listStoryGoodLoveLanguage[position])

                    }

                }
        }
    }

    private fun storyGoodFairyTaleAdapter(rcItem: RecyclerView) {
        storyGoodFairyTaleAdapter = StoryAdapter().apply {
            rcItem.adapter = this

            onItemClickListener = object : StoryAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    intentActivityAndData(listStoryGoodFairyTale[position])

                }
            }

        }

    }


    private fun storyGoodPassionAdapter(rcItem: RecyclerView) {
        storyGoodPassionAdapter = StoryAdapter().apply {
            rcItem.adapter = this
            onItemClickListener = object : StoryAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    intentActivityAndData(listStoryGoodPassion[position])

                }
            }

        }


    }


    private fun intentActivityAndData(story: Story) {
        val intent = Intent(this, ViewDescribeStoryActivity::class.java)
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

    private fun intentActivity(activityClass: Class<*>, position: Int) {
        val intent = Intent(this, activityClass)
        intent.putExtra(Constant.CATEGORY_STORY, listStory[position].nameCategory)
        startActivity(intent)

    }


}


