package edu.wit.mobilepp.md3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment_SkincareTab extends Fragment  {
    public View X;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View X = inflater.inflate(R.layout.fragment_collection_tab2_skincare, container, false);

        List<CardItem> listItems = new ArrayList<CardItem>();

        ListView listView= (ListView) X.findViewById(R.id.SkincareCollectionTab);

        CardItemAdapter listViewAdapter = new CardItemAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listViewAdapter);
        // Inflate the layout for this fragment

        FloatingActionButton mFab = (FloatingActionButton) X.findViewById(R.id.floatingActionButton2);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                Intent intent = new Intent(getActivity(), NewProductSkincareCollection.class);
                intent.setClass(getActivity(),NewProductSkincareCollection.class);
                startActivity(intent);
            }
        });
        return X;


    }

    @Override
    public void onStart() {
        super.onStart();

        String path = "/data/data/" + getActivity().getPackageName() + "/skincare_collection.db";
        Log.v("db", path);
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        // Create a table - people
        String sql = "CREATE TABLE IF NOT EXISTS skincare_collection" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life TEXT);";

        db.execSQL(sql);
        String[] columns = {"brand","product","category","shade","date","life"};
        String where = null;
        String[] where_args = null;
        String having = null;
        String group_by = null;
        String order_by = null;
        Cursor cursor = db.query("skincare_collection", columns, where, where_args, group_by, having, order_by);
        List<CardItem> listItems = new ArrayList<CardItem>();
        while(cursor.moveToNext()){
            String brand = cursor.getString(cursor.getColumnIndex("brand"));
            String product = cursor.getString(cursor.getColumnIndex("product"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String shade = cursor.getString(cursor.getColumnIndex("shade"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String life = cursor.getString(cursor.getColumnIndex("life"));

            Bitmap image;

            if (category.equals("Cleanser")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.cleanser); }
            else if (category.equals("Eye Care")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.eye_care);}
            else if (category.equals("Masks")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.masks);}
            else if (category.equals("Moisturizer")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.moisturizer);}
            else if (category.equals("Self Tanner")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.self_tanner);}
            else if (category.equals("Treatment")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.treatment);}
            else {
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.other);
            }

            CardItem item1 = new CardItem();
            item1.image = image;
            item1.brand = brand;
            item1.product = product;
            item1.category = category;
            item1.shade = shade;
            item1.purchase_date = date;
            item1.lifespan = life;
            listItems.add(item1);

            ListView listView= (ListView) X.findViewById(R.id.SkincareCollectionTab);

            CardItemAdapter listViewAdapter = new CardItemAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
            listView.setAdapter(listViewAdapter);
        }
        Log.v("db", "end the printing");
//Close the database
        db.close();
    }
}