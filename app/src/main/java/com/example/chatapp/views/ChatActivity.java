package com.example.chatapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.chatapp.R;
import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.viewmodel.MyViewModel;
import com.example.chatapp.views.adapters.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private MyViewModel myViewModel;
    private RecyclerView recyclerView;
    private ChatAdapter myAdapter;

    private List<ChatMessage> messagesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        String groupName = getIntent().getStringExtra("groupName");
        myViewModel.getMessagesLiveData(groupName).observe(this, new Observer<List<ChatMessage>>() {
            @Override
            public void onChanged(List<ChatMessage> chatMessages) {
                messagesList = new ArrayList<>();
                messagesList.addAll(chatMessages);
                myAdapter = new ChatAdapter(messagesList, getApplicationContext());
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                int latestPosition = myAdapter.getItemCount() - 1;
                recyclerView.scrollToPosition(latestPosition);
            }
        });
        binding.setVModel(myViewModel);
        binding.sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = binding.edittextChatMessage.getText().toString();
                myViewModel.sendMessage(msg, groupName);
            }
        });
    }
}