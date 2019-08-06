package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EditProductMakeupWishList extends AppCompatActivity {

    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.edit_product);
//        String makeups[] = {"Blush", "Bronzer", "Concealer", "Contour", "Eye Primer", "Eyebrow", "Eyeliner", "Eyeshadow", "Face Primer", "False Lashes", "Highlighter", "Lip Gloss", "Lip Liner", "Lip Stain", "Lipstick", "Liquid Lipstick", "Mascara", "Setting Powder", "Setting Spray", "Tinted Moisturizer"};
        setContentView(R.layout.edit_product);
        //spinner = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProductMakeupCollection.this, android.R.layout.simple_spinner_item, makeups);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);


        Button add_btn = (Button) findViewById(R.id.edititem_buttone);

        Bundle extra = getIntent().getExtras();
        EditText brand_name = (EditText) findViewById(R.id.brand_namee);
        brand_name.setText(extra.getString("itemBrand"));
        //brand = extra.getString("itemBrand");
        //Log.v("BRAND", brand);

        EditText product_name = (EditText) findViewById(R.id.product_namee);
        product_name.setText(extra.getString("itemProduct"));
        //product = extra.getString("itemProduct");

        //Spinner category = (Spinner) findViewById(R.id.category);
        //category.setTest(extra.getString("itemCategory"));

        EditText shade_name = (EditText) findViewById(R.id.shade_namee);
        shade_name.setText(extra.getString("itemShade"));
        //shade = extra.getString("itemShade");

        ID = extra.getString("itemID");


        add_btn.setOnClickListener(new View.OnClickListener() {
            TextInputEditText brand_name1 = (TextInputEditText)findViewById(R.id.brand_namee);
            TextInputEditText product_name1 = (TextInputEditText)findViewById(R.id.product_namee);
            TextInputEditText shade_name1 = (TextInputEditText)findViewById(R.id.shade_namee);

            @Override
            public void onClick(View v) {
                String brand = brand_name1.getText().toString();
                String product = product_name1.getText().toString();

                String shade = shade_name1.getText().toString();

                //Bundle extra = getIntent().getExtras();

                //String brand = extra.get("itemBrand").toString();
                Log.v("new", "click registered");

                //String product = extra.getString("itemProduct").toString();
                //String category = spinner.getSelectedItem().toString();
                //String shade = extra.getString("itemShade").toString();

                //TextInputEditText lifespan = (TextInputEditText)findViewById(R.id.lifespan);
                //TextInputEditText purchase_date = (TextInputEditText) findViewById(R.id.purchase_date);

                /*
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String date;
                try {
                    Date date1 = dateFormat.parse(purchase_date.getText().toString());
                    date = dateFormat.format(date1);
                    Log.v("new", date1.toString());
                } catch (ParseException e) {
                    date = "a";
                    e.printStackTrace();
                }



                Integer life;
                try {
                    life = Integer.parseInt(lifespan.getText().toString());
                } catch (NumberFormatException e) {
                    life = null;
                }

                */
                /*

                if (category.equals("Blush") && life == null) {
                    life = 24;
                }
                if (category.equals("Bronzer") && life == null) {
                    life = 24;
                }
                if (category.equals("Concealer") && life == null) {
                    life = 24;
                }
                if (category.equals("Contour") && life == null) {
                    life = 24;
                }
                if (category.equals("Eye Primer") && life == null) {
                    life = 12;
                }
                if (category.equals("Eyebrow") && life == null) {
                    life = 24;
                }
                if (category.equals("Eyeliner") && life == null) {
                    life = 24;
                }
                if (category.equals("Eyeshadow") && life == null) {
                    life = 24;
                }
                if (category.equals("Face Primer") && life == null) {
                    life = 12;
                }
                if (category.equals("False Lashes") && life == null) {
                    life = 60;
                }
                if (category.equals("Foundation") && life == null) {
                    life = 12;
                }
                if (category.equals("Highlighter") && life == null) {
                    life = 24;
                }
                if (category.equals("Lip Gloss") && life == null) {
                    life = 6;
                }
                if (category.equals("Lip Liner") && life == null) {
                    life = 24;
                }
                if (category.equals("Lipstick") && life == null) {
                    life = 12;
                }
                if (category.equals("Liquid Lipstick") && life == null) {
                    life = 6;
                }
                if (category.equals("Mascara") && life == null) {
                    life = 4;
                }
                if (category.equals("Setting Powder") && life == null) {
                    life = 24;
                }
                if (category.equals("Setting Spray") && life == null) {
                    life = 12;
                }
                if (category.equals("Tinted Moisturizer") && life == null) {
                    life = 12;
                }
    */
//                Integer days_left;
//                if(life!=null) {
//                    Calendar c = Calendar.getInstance();
//                    Calendar cal = Calendar.getInstance();
//                    //Date purchase_date;
//                    try {
////            purchase_date = dateFormat.parse(item.purchase_date);
////            date = dateFormat.format(date1);
////            Log.v("new",date1.toString());
//                        c.setTime(dateFormat.parse(date));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    c.add(Calendar.MONTH, (life));
//                    Date death_date = c.getTime();
//                    Date todays_date = cal.getTime();
//                    Integer days = daysBetween(todays_date, death_date);
//                    if(days>0) {
//                        days_left=days;
//                    }
//                    else{
//                        days_left=-1;
//                    }
//                }
//                else {
//                    days_left=-2;
//                }


                // Set the path and database name
                String path = "/data/data/" + getPackageName() + "/makeup_wishlist.db";
                Log.v("db", path);
                // Open the database. If it doesn't exist, create it.
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS makeup_wishlist" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER, date_sort TEXT);";

                db.execSQL(sql);

                // Add Data
                ContentValues values = new ContentValues();
                values.put("brand", brand);
                values.put("product", product.toString());
                //values.put("category", category);
                values.put("shade", shade.toString());
                //values.put("date", date);
                //values.put("life", life);
                //values.put("days", days_left);
                db.update("makeup_wishlist", values, "_id = "+ ID, null);

                //Close the database
                db.close();

                finish();

            }

        });

    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
