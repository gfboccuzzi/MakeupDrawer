package edu.wit.mobilepp.md3;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

    public class ExpandableListDataPump_MakeupCollection {
        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
            String path = "/data/data/" + "edu.wit.mobilepp.md3" + "/makeup_collection.db";
            SQLiteDatabase db;
            db = SQLiteDatabase.openOrCreateDatabase(path, null);
            // Create a table - people
            String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER, date_sort TEXT);";
            db.execSQL(sql);

            List<String> sortby = new ArrayList<String>();
            sortby.add("Newest to Oldest");
            sortby.add("Oldest to Newest");

            List<String> category = new ArrayList<String>();
            db.execSQL("DROP TABLE IF EXISTS filter_by_makeupcategory;");
            String sql4 = "CREATE TABLE IF NOT EXISTS filter_by_makeupcategory" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT);";
            db.execSQL(sql4);
            String sql5 = "INSERT INTO filter_by_makeupcategory (category) SELECT DISTINCT category FROM makeup_collection;";
            db.execSQL(sql5);
            String[] columns = {"_id","category"};
            String where = null;
            String[] where_args = null;
            String having = null;
            String group_by = null;
            String order_by = null;
            Cursor cursor = db.query("filter_by_makeupcategory", columns, where, where_args, group_by, having, order_by);
            while(cursor.moveToNext()) {
                String _id = cursor.getString(cursor.getColumnIndex("_id"));
                String categories = cursor.getString(cursor.getColumnIndex("category"));
                category.add(categories);
            }

            List<String> brand = new ArrayList<String>();

            db.execSQL("DROP TABLE IF EXISTS filter_by_makeupbrand;");
            String sql2 = "CREATE TABLE IF NOT EXISTS filter_by_makeupbrand" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT);";
            db.execSQL(sql2);
            String sql3 = "INSERT INTO filter_by_makeupbrand (brand) SELECT DISTINCT brand FROM makeup_collection;";
            db.execSQL(sql3);
            String[] columns1 = {"_id","brand"};
            String where1 = null;
            String[] where_args1 = null;
            String having1 = null;
            String group_by1 = null;
            String order_by1 = null;
            Cursor cursor1 = db.query("filter_by_makeupbrand", columns1, where1, where_args1, group_by1, having1, order_by1);
            while(cursor1.moveToNext()) {
                String _id = cursor1.getString(cursor1.getColumnIndex("_id"));
                String brands = cursor1.getString(cursor1.getColumnIndex("brand"));
                brand.add(brands);
            }
            Collections.sort(brand);

            expandableListDetail.put("Sort By", sortby);
            expandableListDetail.put("Category", category);
            expandableListDetail.put("Brand", brand);

            return expandableListDetail;
        }
    }