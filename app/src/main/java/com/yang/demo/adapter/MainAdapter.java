package com.yang.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.yang.demo.R;
import com.yang.demo.entity.Word;
import com.yang.demo.databinding.RecyclerviewItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2019/08/13.
 */
public class MainAdapter extends Adapter<MainAdapter.ContentHolder> {

    private List<Word> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public MainAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<Word> list) {
        if(list==null){
            list=new ArrayList<>();
        }
        this.mDatas = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContentHolder(inflater.inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContentHolder holder, int position) {
        Word word = mDatas.get(position);
        holder.binding.textView.setText("" + word.getWord());
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas.size();
    }

    public class ContentHolder extends RecyclerView.ViewHolder {
        RecyclerviewItemBinding binding;

        public ContentHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
