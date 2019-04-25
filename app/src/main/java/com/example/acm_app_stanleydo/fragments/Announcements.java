package com.example.acm_app_stanleydo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acm_app_stanleydo.R;
import com.example.acm_app_stanleydo.models.Announcement;
import com.example.acm_app_stanleydo.models.RecyclerAnnouncements;
import com.example.acm_app_stanleydo.models.RecyclerHackathons;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Announcements extends Fragment {

    final private FirebaseDatabase database = FirebaseDatabase.getInstance("https://acmappstanleydo.firebaseio.com");
    private DatabaseReference mDatabase;
    private List<Announcement> announcementList;
    private RecyclerView recyclerView;
    private RecyclerAnnouncements recyclerAdapter;
    private final String TAG = "Firebase Announcements";

    public Announcements() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        announcementList = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_announcements, container, false);
        recyclerView = rootView.findViewById(R.id.announcements_recycler);
        recyclerAdapter = new RecyclerAnnouncements(announcementList,container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(recyclerAdapter);
        getActivity().setTitle("Announcements");

        getData();

        // Inflate the layout for this fragment
        return rootView;
    }

    public void getData() {

        mDatabase = database.getReference();

        Query query = mDatabase.child("Announcements");

        Log.d(TAG, "Reference of database has found Announcements child");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Announcement announcement = singleSnapshot.getValue(Announcement.class);
                    announcementList.add(announcement);
                }

                recyclerAdapter.notifyDataSetChanged();

                Log.d(TAG, "Successfully obtained Announcements");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Firebase failed to obtain Announcements");
            }
        });
    }

    public void setAnnouncementsRecycler(final ViewGroup container) {
        recyclerAdapter = new RecyclerAnnouncements(announcementList, container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }



}
