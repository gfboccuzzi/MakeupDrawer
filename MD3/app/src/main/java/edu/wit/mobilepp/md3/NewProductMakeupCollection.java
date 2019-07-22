package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.view.ViewGroup.LayoutParams;

public class NewProductMakeupCollection extends AppCompatActivity {
    private Spinner spinner;
    PopupWindow popupWindow;
    ConstraintLayout constraintLayout1;
    private static final String[] makeups = {"Blush", "Bronzer", "Concealer", "Contour", "Eye Primer", "Eyebrow", "Eyeliner", "Eyeshadow", "Face Primer", "False Lashes", "Foundation", "Highlighter", "Lip Gloss", "Lip Liner", "Lipstick", "Liquid Lipstick", "Mascara", "Setting Powder", "Setting Spray", "Tinted Moisturizer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_makeup_collection);
//        String makeups[] = {"Blush", "Bronzer", "Concealer", "Contour", "Eye Primer", "Eyebrow", "Eyeliner", "Eyeshadow", "Face Primer", "False Lashes", "Highlighter", "Lip Gloss", "Lip Liner", "Lip Stain", "Lipstick", "Liquid Lipstick", "Mascara", "Setting Powder", "Setting Spray", "Tinted Moisturizer"};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewProductMakeupCollection.this, android.R.layout.simple_spinner_item, makeups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        FloatingActionButton question_btn = (FloatingActionButton)findViewById(R.id.questionbutton1);
        constraintLayout1 = (ConstraintLayout)findViewById(R.id.constraintLayout1);
        question_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) NewProductMakeupCollection.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup_window, null);

                ImageButton closePopupBtn = (ImageButton)customView.findViewById(R.id.ib_close);
                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(constraintLayout1, Gravity.CENTER,0,0);

                closePopupBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        popupWindow.dismiss();
                    }
                });
            }
        });





        Button add_btn = (Button)findViewById(R.id.additem_button);

        add_btn.setOnClickListener(new View.OnClickListener(){
            TextInputEditText brand_name = (TextInputEditText)findViewById(R.id.brand_name);
            TextInputEditText product_name = (TextInputEditText)findViewById(R.id.product_name);
            TextInputEditText shade_name = (TextInputEditText)findViewById(R.id.shade_name);
            TextInputEditText purchase_date = (TextInputEditText)findViewById(R.id.purchase_date);
            TextInputEditText lifespan = (TextInputEditText)findViewById(R.id.lifespan);

            @Override
            public void onClick(View v) {
                String brand = brand_name.getText().toString();
                String product = product_name.getText().toString();
                String category = spinner.getSelectedItem().toString();
                String shade = shade_name.getText().toString();
                String date = purchase_date.getText().toString();
                String life = lifespan.getText().toString();

                // Set the path and database name
                String path = "/data/data/" + getPackageName() + "/makeup_collection.db";
                Log.v("db", path);
                // Open the database. If it doesn't exist, create it.
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life TEXT);";

                db.execSQL(sql);

                // Add Data
                ContentValues values = new ContentValues();
                values.put("brand", brand);
                values.put("product", product);
                values.put("category", category);
                values.put("shade", shade);
                values.put("date", date);
                values.put("life", life);
                db.insert("makeup_collection", null, values);

                //Close the database
                db.close();

                finish();
            }

        });

    }
}
