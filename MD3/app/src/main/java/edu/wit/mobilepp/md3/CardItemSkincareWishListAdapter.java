package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.content.Context;
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
                        String date = purchase_date.getText().toString();
                        String life = lifespan.getText().toString();
                        Log.v("wl", date);
                        String path = "/data/data/" + getContext().getPackageName() + "/skincare_wishlist.db";
                        SQLiteDatabase db;
                        db = SQLiteDatabase.openOrCreateDatabase(path, null);
                        String path2 = "/data/data/" + getContext().getPackageName() + "/skincare_collection.db";
                        SQLiteDatabase db2;
                        db2 = SQLiteDatabase.openOrCreateDatabase(path2, null);
                        // Create a table - people
                        String sql = "CREATE TABLE IF NOT EXISTS skincare_collection" +
                                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life TEXT);";
                        db2.execSQL(sql);
                        ContentValues values = new ContentValues();
                        values.put("brand", item.brand);
                        values.put("product", item.product);
                        values.put("category", item.category);
                        values.put("shade", item.shade);
                        values.put("date", date);
                        values.put("life", life);
                        db2.insert("skincare_collection", null, values);
                        db.execSQL("DELETE FROM skincare_wishlist WHERE _ID= " + item.id);
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
}
