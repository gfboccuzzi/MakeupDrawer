package edu.wit.mobilepp.md3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment_MakeupTab extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_collection_tab1_makeup, container, false);

        Bitmap lipstick;
        lipstick =
                BitmapFactory.decodeResource(getResources(), R.drawable.lipstick);
        Bitmap lipgloss;
        lipgloss =
                BitmapFactory.decodeResource(getResources(), R.drawable.lipgloss);
        List<CardItem> listItems = new ArrayList<CardItem>();
        CardItem item1 = new CardItem();
        item1.image = lipstick;
        item1.brand = "Tom Ford";
        item1.product = "Lip Color";
        item1.category = "Lipstick";
        item1.shade = "Smoke Sable";
        item1.purchase_date = "10/29/18";
        item1.lifespan = "24 Months";
        listItems.add(item1);
        CardItem item2 = new CardItem();
        item2.image = lipgloss;
        item2.brand = "FENTY BEAUTY by Rihanna";
        item2.product = "Gloss Bomb Universal Lip Luminizer";
        item2.category = "Lip Gloss";
        item2.shade = "FU$$Y";
        item2.purchase_date = "07/14/19";
        item2.lifespan = "24 Months";
        listItems.add(item2);

        ListView listView= (ListView) V.findViewById(R.id.MakeupCollectionTab);

        CardItemAdapter listViewAdapter = new CardItemAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listViewAdapter);


        FloatingActionButton mFab = (FloatingActionButton) V.findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),NewProductMakeupCollection.class);
                startActivity(intent);
            }
        });
         // Inflate the layout for this fragment
        return V;


    }
}