package com.jack.androidbase.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jack.androidbase.R;

import java.util.List;

public class MyRecycler1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //上拉状态0：上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //上拉状态1：正在加载中
    public static final int LOADING_MORE = 1;
    private static final int TYPE_FOOTER = 0;//带Footer的Item
    private static final int TYPE_NORMAL = 1;//不带Footer的Item

    //上拉加载状态，默认为状态0-上拉加载更多
    private int load_more_status = 0;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    //数据源
    private List<String> mList;

    public MyRecycler1Adapter(List<String> list) {
        mList = list;
    }

    //定义接口 OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    //根据Item位置返回viewType，供onCreateViewHolder方法内获取不同的Holder
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            return new NormalHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_1, parent, false));
        } else {
            return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_footer, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalHolder) {
            //为普通Item填充数据
            ((NormalHolder) holder).mView.setText(mList.get(position));
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        } else {
            //footerview，需要判断上拉状态
            switch (load_more_status) { //状态：上拉加载更多
                case PULLUP_LOAD_MORE:
                    ((FooterHolder) holder).mView.setText("上拉加载更多哦");
                    break; //状态：正在加载中
                case LOADING_MORE:
                    ((FooterHolder) holder).mView.setText("正在加载中哦");
                    break;
            }

        }

    }

    //改变当前上拉状态
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    //Footer对应Holder
    public class FooterHolder extends RecyclerView.ViewHolder {
        private TextView mView;

        public FooterHolder(View itemView) {
            super(itemView);
            mView = (TextView) itemView.findViewById(R.id.footer);
        }
    }

    //普通Item对应的Holder
    public class NormalHolder extends RecyclerView.ViewHolder {
        public TextView mView;

        public NormalHolder(View itemView) {
            super(itemView);
            mView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }


    //末尾添加Item，供上拉加载更多时调用
    public void addMoreItem(List<String> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    //头部添加Item，供上拉刷新时调用
    public void addData(int position) {
        mList.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(List<String> list) {
        list.addAll(mList);
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

}