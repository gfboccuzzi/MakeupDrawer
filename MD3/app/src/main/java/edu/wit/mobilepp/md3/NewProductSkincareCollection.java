package edu.wit.mobilepp.md3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class NewProductSkincareCollection extends AppCompatActivity {
    private Spinner spinner;
    private static final String[] makeups = {"Blush", "Bronzer", "Concealer", "Contour", "Eye Primer", "Eyebrow", "Eyeliner", "Eyeshadow", "Face Primer", "False Lashes", "Highlighter", "Lip Gloss", "Lip Liner", "Lip Stain", "Lipstick", "Liquid Lipstick", "Mascara", "Setting Powder", "Setting Spray", "Tinted Moisturizer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product_skincare_collection);

        spinner = (Spinner) findViewById(R.id.spinnersc);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewProductSkincareCollection.this, android.R.layout.simple_spinner_item, makeups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button add_btn = (Button)findViewById(R.id.additem_buttonsc);

        add_btn.setOnClickListener(new View.OnClickListener(){
            TextInputEditText brand_name = (TextInputEditText)findViewById(R.id.brand_namesc);
            TextInputEditText product_name = (TextInputEditText)findViewById(R.id.product_namesc);
            TextInputEditText shade_name = (TextInputEditText)findViewById(R.id.shade_namesc);
            TextInputEditText purchase_date = (TextInputEditText)findViewById(R.id.purchase_datesc);
            TextInputEditText lifespan = (TextInputEditText)findViewById(R.id.lifespansc);

            @Override
            public void onClick(View v){
//                String brand = brand_name.getText().toString();
//                String product = product_name.getText().toString();
//                String shade = shade_name.getText().toString();
//                String date = purchase_date.getText().toString();
//                String life = lifespan.getText().toString();
//
//                Intent intent = new Intent();
//                intent.setClass(NewProductSkincareCollection.this, CollectionFragment_SkincareTab.class);
//                Bundle bundle = new Bundle();
//
//                bundle.putString("brand", brand);
//                bundle.putString("product", product);
//                bundle.putString("shade", shade);
//                bundle.putString("date", date);
//                bundle.putString("life", life);
//
//                intent.putExtras(bundle);
//                startActivity(intent);
                finish();

            }

        });

    }
}