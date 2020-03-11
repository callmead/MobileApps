package edu.utep.cs.cs4330.listviewapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ListActivity {
    private List myList;
    private ListView lv;
    String[][] myData = {
            {"HW0", "100", "95"},
            {"HW1", "100", "90"},
            {"HW2", "100", "80"},
            {"HW3", "100", "100"},
            {"HW4", "100", "110"},
            {"HW5", "100", "85"},
            {"HW6", "100", "97"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);


        //setListAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item_1, myData));
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myData);
        lv.setAdapter(itemsAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, "You have selected "+myData[position], Toast.LENGTH_SHORT).show();
    }
}
