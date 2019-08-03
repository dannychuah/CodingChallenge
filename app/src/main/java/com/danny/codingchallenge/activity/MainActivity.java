package com.danny.codingchallenge.activity;

import android.app.Dialog;
import android.os.Bundle;

import com.danny.codingchallenge.R;
import com.danny.codingchallenge.adapter.TopicAdapter;
import com.danny.codingchallenge.model.Topic;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TopicAdapter mTopicAdapter;
    private RecyclerView recyclerView;
    private List<Topic> topicList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);

        mTopicAdapter = new TopicAdapter(topicList, this.getApplicationContext(), new TopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Topic topic_item) {
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mTopicAdapter);

        prepareTopicData();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTopicDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sort) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewTopicDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.topic_form);

        // set the custom dialog components - text, image and button
        TextView submit = (TextView) dialog.findViewById(R.id.submit);
        final EditText editext =(EditText) dialog.findViewById(R.id.edittext);

        // if submit is clicked, close the dialog
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String topic = editext.getText().toString();
               if(!topic.isEmpty()) {
                   Topic topic_item = new Topic(topic);

                   topicList.add(topic_item);
                   mTopicAdapter.notifyDataSetChanged();
                   dialog.dismiss();
               }
            }
        });

        dialog.show();
//    }
    }

    public void prepareTopicData(){
    // currently don't have
    }
}
