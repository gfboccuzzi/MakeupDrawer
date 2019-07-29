package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class CardItemMakeupExpiringAdapter extends ArrayAdapter<CardItemMakeupExpiring> {
    public List<CardItemMakeupExpiring> l;
    private LayoutInflater mInflater;
    public CardItemMakeupExpiringAdapter(Context context, int rid, List<CardItemMakeupExpiring> list){
        super(context, rid, list);
        l=list;
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        // Retrieve data
        final CardItemMakeupExpiring item = (CardItemMakeupExpiring) getItem(position);
        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.card_item_expiring, null);
        // Set image
        ImageView image;
        image = (ImageView)view.findViewById(R.id.image);
        image.setImageBitmap(item.image);
        // Set title
        TextView brand;
        brand = (TextView)view.findViewById(R.id.brand);
        brand.setText(item.brand);
        // Set description
        TextView product;
        product = (TextView) view.findViewById(R.id.product);
        product.setText(item.product);

        TextView category;
        category = (TextView) view.findViewById(R.id.category);
        category.setText(item.category);

        TextView shade;
        shade = (TextView) view.findViewById(R.id.shade);
        shade.setText(item.shade);

        TextView days_left;
        days_left = (TextView) view.findViewById(R.id.days_left);
        String path = "/data/data/" + getContext().getPackageName() + "/makeup_collection.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        // Create a table - people
        String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER, date_sort TEXT);";
        db.execSQL(sql);
        Integer days;
        CardView ly_root = (CardView) view.findViewById(R.id.ly_root);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if(item.lifespan!=null) {
            Calendar c = Calendar.getInstance();
            Calendar cal = Calendar.getInstance();
            //Date purchase_date;
            try {
//            purchase_date = dateFormat.parse(item.purchase_date);
//            date = dateFormat.format(date1);
//            Log.v("new",date1.toString());
                c.setTime(dateFormat.parse(item.purchase_date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.MONTH, (item.lifespan));
            Date death_date = c.getTime();
            Date todays_date = cal.getTime();
            Integer days1 = daysBetween(todays_date, death_date);
            if(days1>0) {
                days=days1;
                if (days>30) {
                    ly_root.setCardBackgroundColor(Color.rgb(138,237,164));
                }
                else{
                    ly_root.setCardBackgroundColor(Color.rgb(255,251,148));
                }
            }
            else{
                days=-1;
                ly_root.setCardBackgroundColor(Color.rgb(255, 66, 66));
            }
        }
        else {
            days=-2;
            ly_root.setCardBackgroundColor(Color.rgb(219,217, 217));
        }
        if(days>0) {
            days_left.setText(days.toString() + " Days Left");
        }
        else if(days==-1){
            days_left.setText("Expired");
        }
        else {
            days_left.setText("UNK" + " Days Left");
        }
        ContentValues values = new ContentValues();
        db.execSQL("UPDATE makeup_collection SET days =  " + days + " WHERE _id= " + item.id+ ";");
        db.close();


//        TextView purchase_date;
//        purchase_date = (TextView) view.findViewById(R.id.purchase_date);
//        purchase_date.setText(item.purchase_date);
//
//        TextView lifespan;
//        lifespan= (TextView) view.findViewById(R.id.lifespan);
//        lifespan.setText(item.lifespan);
        FloatingActionButton deleteBtn = (FloatingActionButton) view.findViewById(R.id.deletebutton3);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                l.remove(position);
                notifyDataSetChanged();
                String path = "/data/data/" + getContext().getPackageName() + "/makeup_collection.db";
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER);";
                db.execSQL(sql);
                db.execSQL("DELETE FROM makeup_collection WHERE _ID= " + item.id);
                db.close();

            }
        });

        return view;
    }
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
