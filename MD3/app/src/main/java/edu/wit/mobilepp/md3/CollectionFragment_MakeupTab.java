package edu.wit.mobilepp.md3;

import android.content.ContentValues;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CollectionFragment_MakeupTab extends Fragment  {
    public View V;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        V = inflater.inflate(R.layout.fragment_collection_tab1_makeup, container, false);


        List<CardItem> listItems = new ArrayList<CardItem>();
//        CardItem item1 = new CardItem();
//        item1.image = lipstick;
//        item1.brand = "Tom Ford";
//        item1.product = "Lip Color";
//        item1.category = "Lipstick";
//        item1.shade = "Smoke Sable";
//        item1.purchase_date = "10/29/18";
//        item1.lifespan = "24 Months";
//        listItems.add(item1);
//        CardItem item2 = new CardItem();
//        item2.image = lipgloss;
//        item2.brand = "FENTY BEAUTY by Rihanna";
//        item2.product = "Gloss Bomb Universal Lip Luminizer";
//        item2.category = "Lip Gloss";
//        item2.shade = "FU$$Y";
//        item2.purchase_date = "07/14/19";
//        item2.lifespan = "24 Months";
//        listItems.add(item2);


        ListView listView= (ListView) V.findViewById(R.id.MakeupCollectionTab);

        CardItemAdapter listViewAdapter = new CardItemAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listViewAdapter);


         // Inflate the layout for this fragment
        FloatingActionButton mFab = (FloatingActionButton) V.findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProductMakeupCollection.class);
                intent.setClass(getActivity(),NewProductMakeupCollection.class);
                startActivity(intent);
            }
        });


        return V;


    }

    @Override
    public void onStart() {
        super.onStart();

        String path = "/data/data/" + getActivity().getPackageName() + "/makeup_collection.db";
        Log.v("db", path);
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        // Create a table - people
        String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life TEXT);";

        db.execSQL(sql);
        String[] columns = {"brand","product","category","shade","date","life"};
        String where = null;
        String[] where_args = null;
        String having = null;
        String group_by = null;
        String order_by = null;
        Cursor cursor = db.query("makeup_collection", columns, where, where_args, group_by, having, order_by);
        List<CardItem> listItems = new ArrayList<CardItem>();
        while(cursor.moveToNext()){
            String brand = cursor.getString(cursor.getColumnIndex("brand"));
            String product = cursor.getString(cursor.getColumnIndex("product"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String shade = cursor.getString(cursor.getColumnIndex("shade"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String life = cursor.getString(cursor.getColumnIndex("life"));

            Bitmap image;

            if (category.equals("Blush")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.blush); }
            else if (category.equals("Bronzer")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.bronzer);}
            else if (category.equals("Concealer")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.concealer);}
            else if (category.equals("Contour")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.contour);}
            else if (category.equals("Eye Primer")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.eye_primer);}
            else if (category.equals("Eyebrow")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.eyebrow);}
            else if (category.equals("Eyeliner")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.eyeliner);}
            else if (category.equals("Eyeshadow")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.eyeshadow);}
            else if (category.equals("Face Primer")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.face_primer);}
            else if (category.equals("False Lashes")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.false_lashes);}
            else if (category.equals("Foundation")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.foundation);}
            else if (category.equals("Highlighter")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.highlighter);}
            else if (category.equals("Lip Gloss")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.lip_gloss);}
            else if (category.equals("Lip Liner")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.lip_liner);}
            else if (category.equals("Lipstick")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.lipstick);}
            else if (category.equals("Liquid Lipstick")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.liquid_lipstick);}
            else if (category.equals("Mascara")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.mascara);}
            else if (category.equals("Setting Poweder")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.setting_powder);}
            else if (category.equals("Setting Spray")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.setting_spray);}
            else if (category.equals("Tinted Moisturizer")){
                image =
                        BitmapFactory.decodeResource(getResources(), R.drawable.tinted_moisturizer);}
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

            ListView listView= (ListView) V.findViewById(R.id.MakeupCollectionTab);

            CardItemAdapter listViewAdapter = new CardItemAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
            listView.setAdapter(listViewAdapter);
        }
        Log.v("db", "end the printing");
//Close the database
        db.close();
    }
}