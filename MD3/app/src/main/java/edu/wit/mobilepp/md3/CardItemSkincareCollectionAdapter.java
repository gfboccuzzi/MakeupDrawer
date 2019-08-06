package edu.wit.mobilepp.md3;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
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

public class CardItemSkincareCollectionAdapter extends ArrayAdapter<CardItemSkincareCollection> {
    public List<CardItemSkincareCollection> l;
    private LayoutInflater mInflater;
    PopupWindow popupWindow;
    private Context mcontext;
    public CardItemSkincareCollectionAdapter(Context context, int rid, List<CardItemSkincareCollection> list){
        super(context, rid, list);
        l=list;
        mcontext=context;
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        // Retrieve data
        final CardItemSkincareCollection item = (CardItemSkincareCollection)getItem(position);
        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.card_item_collection, null);
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

        FloatingActionButton editBtn = (FloatingActionButton) view.findViewById(R.id.editbutton);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                Intent intent = new Intent(mcontext, EditProductSkincareCollection.class);
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

//        TextView lifespan;
//        lifespan= (TextView) view.findViewById(R.id.lifespan);
//        lifespan.setText(item.lifespan);

        FloatingActionButton deleteBtn = (FloatingActionButton) view.findViewById(R.id.deletebutton);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
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
                        String path = "/data/data/" + getContext().getPackageName() + "/skincare_collection.db";
                        SQLiteDatabase db;
                        db = SQLiteDatabase.openOrCreateDatabase(path, null);
                        // Create a table - people
                        String sql = "CREATE TABLE IF NOT EXISTS skincare_collection" +
                                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life TEXT, days INTEGER, date_sort TEXT);";
                        db.execSQL(sql);
//                String[] deleteArgs = {""+position+""};
//                db.delete("skincare_collection", "_id=?", deleteArgs);
                        db.execSQL("DELETE FROM skincare_collection WHERE _ID= " + item.id);
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

        return view;
    }
}
