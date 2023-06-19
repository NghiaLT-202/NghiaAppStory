package com.example.appstory88.ui.home.category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.appstory88.R
import com.example.appstory88.adapter.ItemCategoryStoryAdapter
import com.example.appstory88.base.BaseBindingFragment
import com.example.appstory88.commom.Constant
import com.example.appstory88.databinding.FragmentCategoryStoryBinding
import com.example.appstory88.model.ItemCategory
import com.example.appstory88.ui.MainActivity
import com.example.appstory88.ui.MainViewModel
import com.google.gson.Gson

class CategoryStoryFragment :
    BaseBindingFragment<FragmentCategoryStoryBinding, CategoryStoryViewModel>() {
    private val listCategory: MutableList<ItemCategory> = mutableListOf()
    private var itemCategoryStoryAdapter: ItemCategoryStoryAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_category_story
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initAdapter()
        initData()
    }


    fun initData() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.initData(requireContext())
        mainViewModel.listStoryLiveData.observe(this) {
            mainViewModel.initDataCategory(requireContext(), it)
        }
        mainViewModel.listCategoryLiveData.observe(this) {
            listCategory.clear()
            listCategory.addAll(it)
            itemCategoryStoryAdapter?.listCategory = it
        }
    }

    private fun initAdapter() {
        itemCategoryStoryAdapter = ItemCategoryStoryAdapter().apply {
            binding.rcItemStory.adapter = this
            onItemClickListener = object : ItemCategoryStoryAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    intentActivity(listCategory[position], position)
                }
            }
        }
    }

    override fun getViewModel(): Class<CategoryStoryViewModel> {
        return CategoryStoryViewModel::class.java
    }

    private fun intentActivity(story: ItemCategory, position: Int) {
        val bundle = Bundle()
        bundle.putString(
            Constant.KEY_DETAIL_STORY,
            Gson().toJson(itemCategoryStoryAdapter?.listCategory?.get(position))
        )
        (requireActivity() as MainActivity).navController?.navigate(
            R.id.fragment_detail_story_top,
            bundle
        )

    }
}