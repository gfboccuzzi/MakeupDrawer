package edu.wit.mobilepp.md3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterBy_MakeupCollection extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    public static String category_selection_mc=null;
    public static String specific_selection_mc=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_layout);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump_MakeupCollection.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Bundle bundle = new Bundle();
//                String category_selection;
//                String specific_selection;
                category_selection_mc=expandableListTitle.get(groupPosition);
                specific_selection_mc=expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                CollectionFragment_MakeupTab fragment_makeupTab = new CollectionFragment_MakeupTab();

                Log.v("Filer-cat", category_selection_mc);
                Log.v("Filter-spec", specific_selection_mc);

                finish();

                return false;

            }
        });
        Button clearfilters = (Button)findViewById(R.id.clearfilters);
        clearfilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_selection_mc=null;
                specific_selection_mc=null;
                finish();
            }
        });
    }

}