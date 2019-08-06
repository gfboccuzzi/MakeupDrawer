package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import java.util.Date;
import java.util.List;

public class CardItemSkincareWishListAdapter extends ArrayAdapter<CardItemSkincareWishList> {
    public List<CardItemSkincareWishList> l;
    private LayoutInflater mInflater;
    PopupWindow popupWindow;
    private Context mcontext;
    public CardItemSkincareWishListAdapter(Context context, int rid, List<CardItemSkincareWishList> list){
        super(context, rid, list);
        l=list;
        mcontext=context;
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        // Retrieve data
        final CardItemSkincareWishList item = (CardItemSkincareWishList) getItem(position);
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
        FloatingActionButton editBtn = (FloatingActionButton) view.findViewById(R.id.editbuttonwl);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                Intent intent = new Intent(mcontext, EditProductSkincareWishList.class);
                intent.putExtra("itemID", item.id);
                intent.putExtra("itemBrand", item.brand);
                intent.putExtra("itemProduct", item.product);
                intent.putExtra("itemCategory", item.category);
                intent.putExtra("itemShade", item.shade);
                mcontext.startActivity(intent);
            }
        });
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
                LayoutInflater layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View customView = layoutInflater.inflate(R.layout.popup_window_delete, null);

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(customView, Gravity.CENTER,0,0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                Button yes_wl = (Button) customView.findViewById(R.id.yes_wl);
                yes_wl.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        l.remove(position);
                        notifyDataSetChanged();
                        String path = "/data/data/" + getContext().getPackageName() + "/skincare_wishlist.db";
                        Log.v("delete", path);
                        SQLiteDatabase db;
                        db = SQLiteDatabase.openOrCreateDatabase(path, null);
                        // Create a table - people
                        String sql = "CREATE TABLE IF NOT EXISTS skincare_wishlist" +
                                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT);";
                        db.execSQL(sql);
                        db.execSQL("DELETE FROM skincare_wishlist WHERE _ID= " + item.id);
                        db.close();
                        popupWindow.dismiss();
                    }
                });
                Button no_wl=(Button) customView.findViewById(R.id.no_wl);
                no_wl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

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

                    TextInputLayout date_layout = (TextInputLayout)customView.findViewById(R.id.textInputLayout6);

                    @Override
                    public void onClick(View v) {

                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String date;
                        try {
                            Date date1 = dateFormat.parse(purchase_date.getText().toString());
                            date = dateFormat.format(date1);
                            Log.v("new",date1.toString());
                        }catch (ParseException e){
                            date = "";
                            e.printStackTrace();
                        }
                        Integer life;
                        try {
                            life = Integer.parseInt(lifespan.getText().toString());
                        }catch(NumberFormatException e){
                            life=null;
                        }
                        if(item.category.equals("Cleanser") && life==null){
                            life=12;
                        }
                        if(item.category.equals("Eye Care") && life==null){
                            life=12;
                        }
                        if(item.category.equals("Masks") && life==null){
                            life=12;
                        }
                        if(item.category.equals("Moisturizer") && life==null){
                            life=12;
                        }
                        if(item.category.equals("Self Tanner") && life==null){
                            life=12;
                        }
                        if(item.category.equals("Treatment") && life==null){
                            life=12;
                        }

                        //error check
                        date_layout.setError(null);

                        Log.v("empty", "Date: " + date);
                        if(date.isEmpty()) {
                            date_layout.setError("You must enter a purchase date.");
                        } else {
                            String path = "/data/data/" + getContext().getPackageName() + "/skincare_wishlist.db";
                            SQLiteDatabase db;
                            db = SQLiteDatabase.openOrCreateDatabase(path, null);
                            String path2 = "/data/data/" + getContext().getPackageName() + "/skincare_collection.db";
                            SQLiteDatabase db2;
                            db2 = SQLiteDatabase.openOrCreateDatabase(path2, null);
                            // Create a table - people
                            String sql = "CREATE TABLE IF NOT EXISTS skincare_collection" +
                                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER, date_sort TEXT);";
                            db2.execSQL(sql);
                            ContentValues values = new ContentValues();
                            values.put("brand", item.brand);
                            values.put("product", item.product);
                            values.put("category", item.category);
                            values.put("shade", item.shade);
                            values.put("date", date);
                            values.put("life", life);
                            db2.insert("skincare_collection", null, values);
                            db2.execSQL("UPDATE skincare_collection SET date_sort=substr(date,7,4)||'-'||substr(date,1,2)||'-'||substr(date,4,2);");
                            db.execSQL("DELETE FROM skincare_wishlist WHERE _ID= " + item.id);
                            l.remove(position);
                            notifyDataSetChanged();
                            //Close the database
                            db2.close();
                            db.close();
                            popupWindow.dismiss();
                        }
                    }
                });


            }
        });

        return view;
    }
}
