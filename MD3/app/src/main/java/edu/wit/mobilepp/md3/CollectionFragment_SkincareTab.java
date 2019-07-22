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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment_SkincareTab extends Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_collection_tab2_skincare, container, false);

        Bitmap watermelon;
        watermelon =
                BitmapFactory.decodeResource(getResources(), R.drawable.watermelon);
        Bitmap avocado;
        avocado =
                BitmapFactory.decodeResource(getResources(), R.drawable.avocado);
        List<CardItem> listItems = new ArrayList<CardItem>();
        CardItem item1 = new CardItem();
        item1.image = watermelon;
        item1.brand = "Glow Recipe";
        item1.product="Watermelon + AHA Glow Sleeping Mask";
        item1.category = "Face Mask";
        item1.shade = "N/A";
        item1.purchase_date = "04/03/18";
        item1.lifespan = "18 Months";
        listItems.add(item1);
        CardItem item2 = new CardItem();
        item2.image = avocado;
        item2.brand = "Glow Recipe";
        item2.product="Avocado Melt Sleeping Mask";
        item2.category = "Face Mask";
        item2.shade = "N/A";
        item2.purchase_date = "04/30/19";
        item1.lifespan = "18 Months";
        listItems.add(item2);

        ListView listView= (ListView) V.findViewById(R.id.SkincareCollectionTab);

        CardItemAdapter listViewAdapter = new CardItemAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listViewAdapter);
        // Inflate the layout for this fragment

        FloatingActionButton mFab = (FloatingActionButton) V.findViewById(R.id.floatingActionButton2);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProductSkincareCollection.class);
                intent.setClass(getActivity(),NewProductSkincareCollection.class);
                startActivity(intent);
            }
        });
        return V;


    }
}