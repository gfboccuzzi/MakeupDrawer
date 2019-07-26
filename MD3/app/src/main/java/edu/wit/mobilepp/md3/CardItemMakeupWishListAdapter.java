package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CardItemMakeupWishListAdapter extends ArrayAdapter<CardItemMakeupWishList> {
    public List<CardItemMakeupWishList> l;
    private LayoutInflater mInflater;
    PopupWindow popupWindow;
    private Context mcontext;
    public CardItemMakeupWishListAdapter(Context context, int rid, List<CardItemMakeupWishList> list){
        super(context, rid, list);
        l=list;
        mcontext=context;
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        // Retrieve data
        final CardItemMakeupWishList item = (CardItemMakeupWishList) getItem(position);
        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.card_item_wishlist, null);
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

//        TextView purchase_date;
//        purchase_date = (TextView) view.findViewById(R.id.purchase_date);
//        purchase_date.setText(item.purchase_date);
//
//        TextView lifespan;
//        lifespan= (TextView) view.findViewById(R.id.lifespan);
//        lifespan.setText(item.lifespan);
        FloatingActionButton deleteBtn2 = (FloatingActionButton) view.findViewById(R.id.deletebutton2);
        deleteBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                l.remove(position);
                notifyDataSetChanged();
                String path = "/data/data/" + getContext().getPackageName() + "/makeup_wishlist.db";
                Log.v("delete", path);
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS makeup_wishlist" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT);";
                db.execSQL(sql);
                db.execSQL("DELETE FROM makeup_wishlist WHERE _ID= " + item.id);
                db.close();

            }
        });

        FloatingActionButton buyBtn = (FloatingActionButton) view.findViewById(R.id.buybutton);
        buyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Log.v("wl","Click seen shopping");
                LayoutInflater layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View customView = layoutInflater.inflate(R.layout.popup_window_wishlist_input, null);

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(customView, Gravity.CENTER,0,0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                Button pubut = (Button) customView.findViewById(R.id.pubutton);
                pubut.setOnClickListener(new View.OnClickListener() {

                    TextInputEditText purchase_date = (TextInputEditText)customView.findViewById(R.id.purchase_date2);
                    TextInputEditText lifespan = (TextInputEditText)customView.findViewById(R.id.lifespan2);
                    @Override
                    public void onClick(View v) {
                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String date;
                        try {
                            Date date1 = dateFormat.parse(purchase_date.getText().toString());
                            date = dateFormat.format(date1);
                            Log.v("new",date1.toString());
                        }catch (ParseException e){
                            date="a";
                            e.printStackTrace();
                        }
                        Integer life;
                        try {
                            life = Integer.parseInt(lifespan.getText().toString());
                        }catch(NumberFormatException e){
                            life=null;
                        }
                        if(item.category.equals("Blush") && life==null){
                            life=24;
                        }
                        if (item.category.equals("Bronzer") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Concealer") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Contour") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Eye Primer") && life == null) {
                            life=12;
                        }
                        if (item.category.equals("Eyebrow") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Eyeliner") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Eyeshadow") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Face Primer") && life == null) {
                            life=12;
                        }
                        if (item.category.equals("False Lashes") && life == null) {
                            life=60;
                        }
                        if (item.category.equals("Foundation") && life == null) {
                            life=12;
                        }
                        if (item.category.equals("Highlighter") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Lip Gloss") && life == null) {
                            life=6;
                        }
                        if (item.category.equals("Lip Liner") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Lipstick") && life == null) {
                            life=12;
                        }
                        if (item.category.equals("Liquid Lipstick") && life == null) {
                            life=6;
                        }
                        if (item.category.equals("Mascara") && life == null) {
                            life=4;
                        }
                        if (item.category.equals("Setting Powder") && life == null) {
                            life=24;
                        }
                        if (item.category.equals("Setting Spray") && life == null) {
                            life=12;
                        }
                        if (item.category.equals("Tinted Moisturizer") && life == null) {
                            life=12;
                        }

//                        Integer days_left;
//                        if(life!=null) {
//                            Calendar c = Calendar.getInstance();
//                            Calendar cal = Calendar.getInstance();
//                            //Date purchase_date;
//                            try {
////            purchase_date = dateFormat.parse(item.purchase_date);
////            date = dateFormat.format(date1);
////            Log.v("new",date1.toString());
//                                c.setTime(dateFormat.parse(date));
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                            c.add(Calendar.MONTH, (life));
//                            Date death_date = c.getTime();
//                            Date todays_date = cal.getTime();
//                            Integer days = daysBetween(todays_date, death_date);
//                            if(days>0) {
//                                days_left=days;
//                            }
//                            else{
//                                days_left=-1;
//                            }
//                        }
//                        else {
//                            days_left=-2;
//                        }

                        Log.v("wl", date);
                        String path = "/data/data/" + getContext().getPackageName() + "/makeup_wishlist.db";
                        SQLiteDatabase db;
                        db = SQLiteDatabase.openOrCreateDatabase(path, null);
                        String path2 = "/data/data/" + getContext().getPackageName() + "/makeup_collection.db";
                        SQLiteDatabase db2;
                        db2 = SQLiteDatabase.openOrCreateDatabase(path2, null);
                        // Create a table - people
                        String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER);";
                        db2.execSQL(sql);
                        ContentValues values = new ContentValues();
                        values.put("brand", item.brand);
                        values.put("product", item.product);
                        values.put("category", item.category);
                        values.put("shade", item.shade);
                        values.put("date", date);
                        values.put("life", life);
                        //values.put("days", days_left);
                        db2.insert("makeup_collection", null, values);
                        db.execSQL("DELETE FROM makeup_wishlist WHERE _ID= " + item.id);
                        l.remove(position);
                        notifyDataSetChanged();
                        //Close the database
                        db2.close();
                        db.close();
                        popupWindow.dismiss();
                    }
                });


            }
        });

        return view;
    }
//    public int daysBetween(Date d1, Date d2){
//        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
//    }
}