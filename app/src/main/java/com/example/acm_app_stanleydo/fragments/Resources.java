package com.example.acm_app_stanleydo.fragments;

import com.example.acm_app_stanleydo.*;
import com.example.acm_app_stanleydo.models.Event;
import com.example.acm_app_stanleydo.models.RecyclerHackathons;
import com.example.acm_app_stanleydo.retrofit.APIClient;
import com.example.acm_app_stanleydo.retrofit.MLHInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Resources extends Fragment {

    //List of Hackathons, this is where our response from our JSON will be held.

    //List of Events called, "hackathons", where response
    private List<Event> hackathons;

    //Instace of TextView for our results. This will be displayed in the fragment_resources.xml
    private RecyclerView recyclerView;
    private RecyclerHackathons recyclerAdapter;

    public Resources() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Understanding this completely is out of the scope of the workshop
        //Just know that we are getting our root view since we are using a fragment instead of an activity.
        //We must know what activity we are on.
        View rootView = inflater.inflate(R.layout.fragment_resources, container, false);

        //Find the results TextView in our XML through Id
        recyclerView = rootView.findViewById(R.id.hackathons_recycler);

        //LoadJSON is a custom method, we take in the parameter container since this is a fragment.
        LoadJson(container);

        return rootView;
    }

    //Loading the JSON file
    public void LoadJson(final ViewGroup container){

        //Create an instance of the API Interface, API Interface will fetch the JSON
        MLHInterface apiInterface = APIClient.getApiClient().create(MLHInterface.class);

        //Create an instance of Call, then use it to get the events from the api interface.
        Call<List<Event>> call;
        call = apiInterface.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                hackathons = filterEvents (response.body());
                recyclerAdapter = new RecyclerHackathons(hackathons, container.getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }

            public List<Event> filterEvents(final List<Event> allEvents){
                List<Event> caEvents = new ArrayList<>();
                for(int i = allEvents.size() - 1; i > 0; i--){
                    //Filter California Events
                    if(allEvents.get(i).getLocation().contains("CA")){
                        caEvents.add(allEvents.get(i));
                    }
                }
                return caEvents;
            }

        });
    }



}