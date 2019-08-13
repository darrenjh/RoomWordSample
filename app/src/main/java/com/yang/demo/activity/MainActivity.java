package com.yang.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.yang.demo.adapter.MainAdapter;
import com.yang.demo.R;
import com.yang.demo.entity.Word;
import com.yang.demo.viewmodel.WordViewModel;
import com.yang.demo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        final MainAdapter mainAdapter = new MainAdapter(this);
        binding.recyclerView.setAdapter(mainAdapter);
        final WordViewModel wordViewModel = new WordViewModel(MainActivity.this.getApplication());
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                mainAdapter.setDatas(words);
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("测试数据" + i++);
                wordViewModel.insert(word);
            }
        });
    }
}
