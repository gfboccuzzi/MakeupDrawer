package edu.wit.mobilepp.md3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ExpiringFragment_SkincareTab extends Fragment  {
    public View V;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.fragment_expiring_tab2_skincare, container, false);

        List<CardItemSkincareExpiring> listItems = new ArrayList<CardItemSkincareExpiring>();

        ListView listView = (ListView) V.findViewById(R.id.SkincareExpiringTab);

        CardItemSkincareExpiringAdapter listViewAdapter = new CardItemSkincareExpiringAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listViewAdapter);
        listView.setDivider(null);
        listView.setDividerHeight(0);

        return V;
    }

    @Override
    public void onStart(){
        super.onStart();
        String path = "/data/data/" + getActivity().getPackageName() + "/skincare_collection.db";

        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        String sql = "CREATE TABLE if NOT EXISTS skincare_collection" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER);";

        db.execSQL(sql);
        String[] columns = {"_id","brand","product","category","shade","date","life","days"};
        String where = null;
        String[] where_args = null;
        String having = null;
        String group_by = null;
        String order_by = "days";
        Cursor cursor = db.query("skincare_collection", columns, where, where_args, group_by, having, order_by);
        List<CardItemSkincareExpiring> listItems = new ArrayList<CardItemSkincareExpiring>();
        while(cursor.moveToNext()){
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            String brand = cursor.getString(cursor.getColumnIndex("brand"));
            String product = cursor.getString(cursor.getColumnIndex("product"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String shade = cursor.getString(cursor.getColumnIndex("shade"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Integer life;
            try {
                life = Integer.parseInt(cursor.getString(cursor.getColumnIndex("life")));
            }catch(NumberFormatException e){
                life=null;
            }
            Integer days;
//            try {
//                days = Integer.parseInt(cursor.getString(cursor.getColumnIndex("days")));
//            }catch(NumberFormatException e){
//                days=null;
//            }

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

            CardItemSkincareExpiring item1 = new CardItemSkincareExpiring();
            item1.image = image;
            item1.id = _id;
            item1.brand = brand;
            item1.product = product;
            item1.category = category;
            item1.shade = shade;
            item1.purchase_date = date;
            item1.lifespan = life;
           // item1.days_left=days;
            listItems.add(item1);

            ListView listView= (ListView) V.findViewById(R.id.SkincareExpiringTab);

//
//
//            Collections.sort(listItems, new Comparator<CardItemMakeupExpiring>() {
//                @Override
//                public int compare(CardItemMakeupExpiring data1, CardItemMakeupExpiring data2) {
//                        if (data1.days_left < data2.days_left)
//                            return 1;
//                        else
//                            return 0;
//                }
//            });
            CardItemSkincareExpiringAdapter listViewAdapter = new CardItemSkincareExpiringAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
            listView.setAdapter(listViewAdapter);
        }
        Log.v("db", "end the printing");
//Close the database
        db.close();
    }
}
