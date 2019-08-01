package edu.wit.mobilepp.md3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            TextInputEditText lifespan = (TextInputEditText)findViewById(R.id.lifespan);
            TextInputEditText purchase_date = (TextInputEditText) findViewById(R.id.purchase_date);

            TextInputLayout brand_layout = (TextInputLayout)findViewById(R.id.textInputLayout5);
            TextInputLayout product_layout = (TextInputLayout)findViewById(R.id.textInputLayout);
            TextInputLayout shade_layout = (TextInputLayout)findViewById(R.id.textInputLayout2);
            TextInputLayout date_layout = (TextInputLayout)findViewById(R.id.textInputLayout3);


            @Override
            public void onClick(View v) {
                Log.v("new", "click registered");
                String brand = brand_name.getText().toString();
                String product = product_name.getText().toString();
                String category = spinner.getSelectedItem().toString();
                String shade = shade_name.getText().toString();
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

                if(category.equals("Blush") && life==null){
                    life=24;
                }
                if (category.equals("Bronzer") && life == null) {
                    life=24;
                }
                if (category.equals("Concealer") && life == null) {
                    life=24;
                }
                if (category.equals("Contour") && life == null) {
                    life=24;
                }
                if (category.equals("Eye Primer") && life == null) {
                    life=12;
                }
                if (category.equals("Eyebrow") && life == null) {
                    life=24;
                }
                if (category.equals("Eyeliner") && life == null) {
                    life=24;
                }
                if (category.equals("Eyeshadow") && life == null) {
                    life=24;
                }
                if (category.equals("Face Primer") && life == null) {
                    life=12;
                }
                if (category.equals("False Lashes") && life == null) {
                    life=60;
                }
                if (category.equals("Foundation") && life == null) {
                    life=12;
                }
                if (category.equals("Highlighter") && life == null) {
                    life=24;
                }
                if (category.equals("Lip Gloss") && life == null) {
                    life=6;
                }
                if (category.equals("Lip Liner") && life == null) {
                    life=24;
                }
                if (category.equals("Lipstick") && life == null) {
                    life=12;
                }
                if (category.equals("Liquid Lipstick") && life == null) {
                    life=6;
                }
                if (category.equals("Mascara") && life == null) {
                    life=4;
                }
                if (category.equals("Setting Powder") && life == null) {
                    life=24;
                }
                if (category.equals("Setting Spray") && life == null) {
                    life=12;
                }
                if (category.equals("Tinted Moisturizer") && life == null) {
                    life=12;
                }

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


                //error check
                brand_layout.setError(null);
                product_layout.setError(null);
                shade_layout.setError(null);
                date_layout.setError(null);

                if(brand.isEmpty()) {
                    brand_layout.setError("You must enter a brand name.");
                } else {
                    if(product.isEmpty()) {
                        product_layout.setError("You must enter a product name.");
                    } else {
                        if(shade.isEmpty()) {
                            shade_layout.setError("You must enter a shade.");
                        } else {
                            if(date.isEmpty()) {
                                Log.v("inpDate", date);
                                date_layout.setError("You must enter a purchase date.");
                            } else {
                                Log.v("inpDate", date);
                                // Set the path and database name
                                String path = "/data/data/" + getPackageName() + "/makeup_collection.db";
                                Log.v("db", path);
                                // Open the database. If it doesn't exist, create it.
                                SQLiteDatabase db;
                                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                                // Create a table - people
                                String sql = "CREATE TABLE IF NOT EXISTS makeup_collection" +
                                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, product TEXT, category TEXT, shade TEXT, date TEXT, life INTEGER, days INTEGER, date_sort TEXT);";

                                db.execSQL(sql);

                                // Add Data
                                ContentValues values = new ContentValues();
                                values.put("brand", brand);
                                values.put("product", product);
                                values.put("category", category);
                                values.put("shade", shade);
                                values.put("date", date);
                                values.put("life", life);
                                //values.put("days", days_left);
                                db.insert("makeup_collection", null, values);
                                db.execSQL("UPDATE makeup_collection SET date_sort=substr(date,7,4)||'-'||substr(date,1,2)||'-'||substr(date,4,2);");

                                //Close the database
                                db.close();

//                if(brand.equals("")){
//                    brand_layout.setError("You must enter a brand name.");
//                }
//                else {
//                    finish();
//                }
                                finish();
                            }
                        }
                    }
                }

            }

        });

    }
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
