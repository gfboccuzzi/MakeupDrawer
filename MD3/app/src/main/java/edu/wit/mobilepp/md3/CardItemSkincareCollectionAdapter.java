package edu.wit.mobilepp.md3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardItemSkincareCollectionAdapter extends ArrayAdapter<CardItemSkincareCollection> {
    public List<CardItemSkincareCollection> l;
    private LayoutInflater mInflater;
    public CardItemSkincareCollectionAdapter(Context context, int rid, List<CardItemSkincareCollection> list){
        super(context, rid, list);
        l=list;
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
//
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
                l.remove(position);
                notifyDataSetChanged();
                String path = "/data/data/" + getContext().getPackageName() + "/skincare_collection.db";
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS skincare_collection" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life TEXT);";
                db.execSQL(sql);
//                String[] deleteArgs = {""+position+""};
//                db.delete("skincare_collection", "_id=?", deleteArgs);
                db.execSQL("DELETE FROM skincare_collection WHERE _ID= " + item.id);
                db.close();
            }
        });

        return view;
    }
}
