package edu.utep.cs.cs4330.fragmentexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("pattern");

        PatternDetailFragment fragment = (PatternDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        fragment.setPattern(name);

    }

}
