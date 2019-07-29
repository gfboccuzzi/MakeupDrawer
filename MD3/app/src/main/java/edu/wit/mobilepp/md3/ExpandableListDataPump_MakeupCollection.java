package edu.wit.mobilepp.md3;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

    public class ExpandableListDataPump_MakeupCollection {
        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> sortby = new ArrayList<String>();
            sortby.add("Newest to Oldest");
            sortby.add("Oldest to Newest");

            List<String> category = new ArrayList<String>();
            category.add("Blush");
            category.add("Bronzer");
            category.add("Concealer");
            category.add("Contour");
            category.add("Eye Primer");
            category.add("Eyebrow");
            category.add("Eyeliner");
            category.add("Eyeshadow");
            category.add("Face Primer");
            category.add("False Lashes");
            category.add("Foundation");
            category.add("Highlighter");
            category.add("Lip Gloss");
            category.add("Lip Liner");
            category.add("Lipstick");
            category.add("Liquid Lipstick");
            category.add("Mascara");
            category.add("Setting Powder");
            category.add("Setting Spray");
            category.add("Tinted Moisturizer");

            List<String> brand = new ArrayList<String>();
            String path = "/data/data/" + "edu.wit.mobilepp.md3" + "/makeup_collection.db";
            SQLiteDatabase db;
            db = SQLiteDatabase.openOrCreateDatabase(path, null);
            // Create a table - people
            String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER);";
            db.execSQL(sql);
            db.execSQL("DROP TABLE IF EXISTS filter_by_makeup;");
            String sql2 = "CREATE TABLE IF NOT EXISTS filter_by_makeup" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT);";
            db.execSQL(sql2);
            String sql3 = "INSERT INTO filter_by_makeup (brand) SELECT DISTINCT brand FROM makeup_collection;";
            db.execSQL(sql3);
            String[] columns = {"_id","brand"};
            String where = null;
            String[] where_args = null;
            String having = null;
            String group_by = null;
            String order_by = null;
            Cursor cursor = db.query("filter_by_makeup", columns, where, where_args, group_by, having, order_by);
            List<CardItemMakeupCollection> listItems = new ArrayList<CardItemMakeupCollection>();
            while(cursor.moveToNext()) {
                String _id = cursor.getString(cursor.getColumnIndex("_id"));
                String brands = cursor.getString(cursor.getColumnIndex("brand"));
                brand.add(brands);
            }

            expandableListDetail.put("Sort By", sortby);
            expandableListDetail.put("Category", category);
            expandableListDetail.put("Brand", brand);

            return expandableListDetail;
        }
    }