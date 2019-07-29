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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewProductSkincareCollection extends AppCompatActivity {
    private Spinner spinner;
    PopupWindow popupWindow;
    ConstraintLayout constraintLayout2;
    private static final String[] skincares = {"Cleanser", "Eye Care", "Masks", "Moisturizer", "Self Tanner", "Treatment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_skincare_collection);

        spinner = (Spinner) findViewById(R.id.spinnersc);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewProductSkincareCollection.this, android.R.layout.simple_spinner_item, skincares);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        FloatingActionButton question_btn = (FloatingActionButton)findViewById(R.id.questionbutton2);
        constraintLayout2 = (ConstraintLayout)findViewById(R.id.constraintLayout2);
        question_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) NewProductSkincareCollection.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup_window, null);

                ImageButton closePopupBtn = (ImageButton)customView.findViewById(R.id.ib_close);
                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(constraintLayout2, Gravity.CENTER,0,0);

                closePopupBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        popupWindow.dismiss();
                    }
                });
            }
        });

        Button add_btn = (Button)findViewById(R.id.additem_buttonsc);

        add_btn.setOnClickListener(new View.OnClickListener(){
            TextInputEditText brand_name = (TextInputEditText)findViewById(R.id.brand_namesc);
            TextInputEditText product_name = (TextInputEditText)findViewById(R.id.product_namesc);
            TextInputEditText shade_name = (TextInputEditText)findViewById(R.id.shade_namesc);
            TextInputEditText purchase_date = (TextInputEditText)findViewById(R.id.purchase_datesc);
            TextInputEditText lifespan = (TextInputEditText)findViewById(R.id.lifespansc);

            @Override
            public void onClick(View v){
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
                    date="a";
                    e.printStackTrace();
                }
                Integer life;
                try {
                    life = Integer.parseInt(lifespan.getText().toString());
                }catch(NumberFormatException e){
                    life=null;
                }
                if(category.equals("Cleanser") && life==null){
                    life=12;
                }
                if(category.equals("Eye Care") && life==null){
                    life=12;
                }
                if(category.equals("Masks") && life==null){
                    life=12;
                }
                if(category.equals("Moisturizer") && life==null){
                    life=12;
                }
                if(category.equals("Self Tanner") && life==null){
                    life=12;
                }
                if(category.equals("Treatment") && life==null){
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


                // Set the path and database name
                String path = "/data/data/" + getPackageName() + "/skincare_collection.db";
                Log.v("db", path);
                // Open the database. If it doesn't exist, create it.
                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase(path, null);
                // Create a table - people
                String sql = "CREATE TABLE IF NOT EXISTS skincare_collection" +
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
                db.insert("skincare_collection", null, values);
                db.execSQL("UPDATE skincare_collection SET date_sort=substr(date,7,4)||'-'||substr(date,1,2)||'-'||substr(date,4,2);");
                //Close the database
                db.close();

                finish();
            }

        });

    }
//    public int daysBetween(Date d1, Date d2){
//        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
//    }
}