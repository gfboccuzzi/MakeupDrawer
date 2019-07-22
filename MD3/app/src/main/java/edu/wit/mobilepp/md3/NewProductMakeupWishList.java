package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class NewProductMakeupWishList extends AppCompatActivity {
    private Spinner spinner;
    private static final String[] makeups = {"Blush", "Bronzer", "Concealer", "Contour", "Eye Primer", "Eyebrow", "Eyeliner", "Eyeshadow", "Face Primer", "False Lashes", "Foundation", "Highlighter", "Lip Gloss", "Lip Liner", "Lipstick", "Liquid Lipstick", "Mascara", "Setting Powder", "Setting Spray", "Tinted Moisturizer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_makeup_wishlist);
//        String makeups[] = {"Blush", "Bronzer", "Concealer", "Contour", "Eye Primer", "Eyebrow", "Eyeliner", "Eyeshadow", "Face Primer", "False Lashes", "Highlighter", "Lip Gloss", "Lip Liner", "Lip Stain", "Lipstick", "Liquid Lipstick", "Mascara", "Setting Powder", "Setting Spray", "Tinted Moisturizer"};
        spinner = (Spinner) findViewById(R.id.spinnerwl);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewProductMakeupWishList.this, android.R.layout.simple_spinner_item, makeups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button add_btn = (Button)findViewById(R.id.additem_buttonwl);

        add_btn.setOnClickListener(new View.OnClickListener(){
            TextInputEditText brand_name = (TextInputEditText)findViewById(R.id.brand_namewl);
            TextInputEditText product_name = (TextInputEditText)findViewById(R.id.product_namewl);
            TextInputEditText shade_name = (TextInputEditText)findViewById(R.id.shade_namewl);

            @Override
            public void onClick(View v) {
                String brand = brand_name.getText().toString();
                String product = product_name.getText().toString();
                String category = spinner.getSelectedItem().toString();
                String shade = shade_name.getText().toString();

                // Set the path and database name
                String path = "/data/data/" + getPackageName() + "/makeup_wishlist.db";
                Log.v("db", path);
                // Open the database. If it doesn't exist, create it.
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS makeup_wishlist" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT);";

                db.execSQL(sql);

                // Add Data
                ContentValues values = new ContentValues();
                values.put("brand", brand);
                values.put("product", product);
                values.put("category", category);
                values.put("shade", shade);
                db.insert("makeup_wishlist", null, values);

                //Close the database
                db.close();

                finish();
            }

        });

    }
}