package com.example.jiayuanfa.androidscrolldetailpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;


/**
 * 主视图
 * 该项目主要通过一个带头视图+分段控制器+ViewPager的典型界面来分析了NestedScrolling机制
 */
public class MainActivity extends FragmentActivity
{
//	滚动指示器标题
	private String[] mTitles = new String[] { "简介", "评价", "相关" };
//	滚动指示器
	private SimpleViewPagerIndicator mIndicator;
//	内容滚动视图
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private TabFragment[] mFragments = new TabFragment[mTitles.length];

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initDatas();
		initEvents();
	}

	/**
	 * 初始化事件
	 */
	private void initEvents()
	{
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				mIndicator.scroll(position, positionOffset);
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});

	}

	/**
	 * 初始化数据
	 */
	private void initDatas() {

//		给分段控制器设置标题
		mIndicator.setTitles(mTitles);

//		根据分段控制器标题数组的长度决定Fragment的数量并初始化
		for (int i = 0; i < mTitles.length; i++) {
			mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
		}

//		初始化Adapter数据
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mTitles.length;
			}

			@Override
			public Fragment getItem(int position)
			{
				return mFragments[position];
			}

		};

//		给ViewPager设置Adapter
		mViewPager.setAdapter(mAdapter);

//		给ViewPager设置默认的Item
		mViewPager.setCurrentItem(0);
	}

	/**
	 * 初始化View
	 */
	private void initViews() {
		mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
		mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
		
		/*
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
		TextView tv = new TextView(this);
		tv.setText("我的动态添加的");
		tv.setBackgroundColor(0x77ff0000);
		ll.addView(tv, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 600));
		*/
	}

}
