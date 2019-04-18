package com.jack.androidbase.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.adapter.MyRecycler1Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考
 * https://blog.csdn.net/whdalive/article/details/80539976
 */
public class UIRecyclerView1Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyRecycler1Adapter mMyAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<String> list;
    private SwipeRefreshLayout mRefreshLayout;
//    private Handler mHandler;

    //最后一个可见Item的位置，关键所在
    private int lastVisibleItem;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_recycer_view1);

        mHandler = new Handler();
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMyAdapter = new MyRecycler1Adapter(list);
        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager = new GridLayoutManager(this,4);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //点击监听
        mMyAdapter.setOnItemClickListener(new MyRecycler1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(UIRecyclerView1Activity.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(UIRecyclerView1Activity.this, position + " Long click", Toast.LENGTH_SHORT).show();
                mMyAdapter.removeData(position);
            }
        });


        //下拉刷新
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { //刷新动画开始后 回调此方法 //设置可见 mRefreshLayout.setRefreshing(true); //向头部插入数据 List<String> newDatas = new ArrayList<String>(); for (int i = 0; i < 5; i++) { int index = i + 1; newDatas.add("new item" + index); } mMyAdapter.addItem(newDatas); mHandler.postDelayed(new Runnable() { @Override public void run() { //模拟加载时间，设置不可见 mRefreshLayout.setRefreshing(false); } }, 1000); } });
                mRefreshLayout.setRefreshing(true); //向头部插入数据
                List<String> newDatas = new ArrayList<String>();
                for (int i = 0; i < 5; i++) {
                    int index = i + 1;
                    newDatas.add("new item" + index);
                }
                mMyAdapter.addItem(newDatas);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() { //模拟加载时间，设置不可见
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        //设置滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState); //判断是否滑动到底
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mMyAdapter.getItemCount()) { //滑动到底，需要改变状态为 上拉加载更多
                    mMyAdapter.changeMoreStatus(MyRecycler1Adapter.LOADING_MORE); //模拟加载数据
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> newDatas = new ArrayList<String>();
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                newDatas.add("more item" + index);
                            }
                            mMyAdapter.addMoreItem(newDatas); //此时显示 正在加载中
                            mMyAdapter.changeMoreStatus(MyRecycler1Adapter.PULLUP_LOAD_MORE);
                        }
                    }, 2500);
                }
            } //更新最后可见位置

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recy_right1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                mMyAdapter.addData(1);
                break;
            case R.id.delete:
                mMyAdapter.removeData(1);
                break;
        }
        return true;
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            list.add("Item " + i);
        }
    }

}
