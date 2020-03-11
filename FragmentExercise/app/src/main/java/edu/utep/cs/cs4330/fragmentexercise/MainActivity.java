package edu.utep.cs.cs4330.fragmentexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PatternListFragment fragment = (PatternListFragment)
                getSupportFragmentManager().findFragmentById(R.id.listFragment);
        fragment.setListener(this::patternClicked);
    }

    private void patternClicked(String name) {
        // Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("pattern", name);
        startActivity(intent);
    }
}
