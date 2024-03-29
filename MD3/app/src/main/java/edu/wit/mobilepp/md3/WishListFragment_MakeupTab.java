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

public class WishListFragment_MakeupTab extends Fragment  {
    public View V;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        V = inflater.inflate(R.layout.fragment_wishlist_tab1_makeup, container, false);


        List<CardItemMakeupWishList> listItems = new ArrayList<CardItemMakeupWishList>();

        ListView listView= (ListView) V.findViewById(R.id.MakeupWishListTab);

        CardItemMakeupWishListAdapter listViewAdapter = new CardItemMakeupWishListAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listViewAdapter);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        FloatingActionButton filtbutmwl = (FloatingActionButton) V.findViewById(R.id.filterbuttonmwl);
        filtbutmwl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FilterBy_MakeupWishList.class);
                intent.setClass(getActivity(), FilterBy_MakeupWishList.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        FloatingActionButton mFab = (FloatingActionButton) V.findViewById(R.id.floatingActionButton3);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProductMakeupWishList.class);
                intent.setClass(getActivity(),NewProductMakeupWishList.class);
                startActivity(intent);
            }
        });


        return V;


    }

    @Override
    public void onStart() {
        super.onStart();
        String cat= FilterBy_MakeupWishList.category_selection_mwl;
        String spec= FilterBy_MakeupWishList.specific_selection_mwl;

        String whereby=null;
        if (cat=="Category") {
            whereby = "category= '" + spec + "'";
            Log.v("category", whereby);
        }
        else if (cat=="Brand"){
            whereby="brand= '" + spec +"'";
            Log.v("brand", whereby);
        }
        String path = "/data/data/" + getActivity().getPackageName() + "/makeup_wishlist.db";
        Log.v("db", path);
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        // Create a table - people
        String sql = "CREATE TABLE IF NOT EXISTS makeup_wishlist" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT);";

        db.execSQL(sql);
        String[] columns = {"_id","brand","product","category","shade"};
        String where = whereby;
        String[] where_args = null;
        String having = null;
        String group_by = null;
        String order_by = null;
        Cursor cursor = db.query("makeup_wishlist", columns, where, where_args, group_by, having, order_by);
        List<CardItemMakeupWishList> listItems = new ArrayList<CardItemMakeupWishList>();
        while(cursor.moveToNext()){
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            String brand = cursor.getString(cursor.getColumnIndex("brand"));
            String product = cursor.getString(cursor.getColumnIndex("product"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String shade = cursor.getString(cursor.getColumnIndex("shade"));

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
            else if (category.equals("Setting Powder")){
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

            CardItemMakeupWishList item1 = new CardItemMakeupWishList();
            item1.id = _id;
            item1.image = image;
            item1.brand = brand;
            item1.product = product;
            item1.category = category;
            item1.shade = shade;
            listItems.add(item1);

            ListView listView= (ListView) V.findViewById(R.id.MakeupWishListTab);

            CardItemMakeupWishListAdapter listViewAdapter = new CardItemMakeupWishListAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
            listView.setAdapter(listViewAdapter);
        }
        Log.v("db", "end the printing");
//Close the database
        db.close();
    }
}
