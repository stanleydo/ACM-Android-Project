package com.example.acm_app_stanleydo.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acm_app_stanleydo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAnnouncements extends RecyclerView.Adapter<RecyclerAnnouncements.RecyclerViewHolder> {

    private List<Announcement> announcementList;
    private Context mContext;

    public RecyclerAnnouncements(List<Announcement> listOfAnnouncements, Context context) {
        mContext = context;
        announcementList = listOfAnnouncements;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Here, we inflate the Layout with our item.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.announcement_item,viewGroup,false);
        //Then we return the ViewHolder so we can use it in our Resources class.
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //These are the variables for each view.
        ImageView mImageUrl;
        TextView mAuthor;
        TextView mDate;
        TextView mMessage;

        //Here in the RecyclerViewHolder, we instantiate each View by finding their id.
        //Since findViewById returns a view, we must type-cast it to match each type of the variables above.
        public RecyclerViewHolder(View itemView){
            super(itemView);
            mImageUrl = (ImageView) itemView.findViewById(R.id.image_url);
            mAuthor = (TextView) itemView.findViewById(R.id.author);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mMessage = (TextView) itemView.findViewById(R.id.message);


        }

        //Here, we bind the information with the view itself.
        void bind(final int position){
            Picasso.with(mContext).load(announcementList.get(position).getImgurl()).into(mImageUrl);
            mAuthor.setText(announcementList.get(position).getAuthor());
            mDate.setText(announcementList.get(position).getDate());
            mMessage.setText(announcementList.get(position).getMessage());
            //We also set each item view to have a onClickListener.
            itemView.setOnClickListener(this);
        }

        //We give functionality to the "onClick" saying that if the view is clicked, create a new intent and open up the
        //URL on a browser.
        @Override
        public void onClick(View view) {
            String urlString = announcementList.get(getAdapterPosition()).getImgurl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            mContext.startActivity(browserIntent);
        }

    }
}
