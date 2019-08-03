package com.danny.codingchallenge.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danny.codingchallenge.R;
import com.danny.codingchallenge.model.Topic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private List<Topic> topicList;
    private Context mContext;
    private int selectedPos = 0;


    public final TopicAdapter.OnItemClickListener mListener;

    public TopicAdapter(List<Topic> topicList,Context context,TopicAdapter.OnItemClickListener listener){
        this.topicList = topicList;
        this.mListener = listener;
        mContext= context;
    }
    public interface OnItemClickListener{
        void onItemClick(Topic topic_item);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView topicDescription;
        public TextView topicUpvoteCountText;
        public TextView topicDownvoteCountText;
        public ImageView imageDownVote;
        public ImageView imageUpVote;
        public MyViewHolder(View view) {
            super(view);
            topicDescription = (TextView)view.findViewById(R.id.topic_description);
            topicUpvoteCountText = (TextView)view.findViewById(R.id.topic_up_vote_count);
            topicDownvoteCountText = (TextView)view.findViewById(R.id.topic_down_vote_count);
            imageDownVote = (ImageView) view.findViewById(R.id.image_down_vote);
            imageUpVote = (ImageView)view.findViewById(R.id.image_up_vote);
        }

        public void bind(final Topic topic_item,final TopicAdapter.OnItemClickListener listener){

        }

        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final TopicAdapter.MyViewHolder holder, int position) {

    final Topic topic = topicList.get(position);
    holder.bind(topic,mListener);
    holder.topicDescription.setText(topic.getTopicDescription());
    holder.topicDownvoteCountText.setText(topic.getDownVote()+mContext.getResources().getString(R.string.vote));
    holder.topicUpvoteCountText.setText(topic.getUpVote()+mContext.getResources().getString(R.string.vote));

    holder.imageDownVote.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            topic.incrementDownVote();
            holder.topicDownvoteCountText.setText(topic.getDownVote()+mContext.getResources().getString(R.string.vote));
//            TopicAdapter.this.notifyDataSetChanged();
        }
    });

    holder.imageUpVote.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            topic.incrementUpVote();
            holder.topicUpvoteCountText.setText(topic.getUpVote()+mContext.getResources().getString(R.string.vote));
            sorting();
            TopicAdapter.this.notifyDataSetChanged();
        }
    });

    holder.itemView.setSelected(selectedPos == position);
    }

//    @Override
//    public int getItemCount() {
//        return topicList.size();
//    }
    private final int limit = 4;


    @Override
    public int getItemCount() {
        if(topicList.size() > limit){
            return limit;
        }
        else
        {
            return topicList.size();
        }

    }
    public void sorting() {
        Collections.sort(topicList, new Comparator<Topic>() {
            public int compare(Topic emp1, Topic emp2) {
                Log.e("Sss", String.valueOf(Integer.compare(emp1.getUpVote(),emp2.getUpVote())));
                return   Integer.compare(emp2.getUpVote(),emp1.getUpVote());
            }
        });
    }

}
