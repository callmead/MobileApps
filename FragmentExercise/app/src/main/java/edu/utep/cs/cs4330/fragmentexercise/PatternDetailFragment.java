package edu.utep.cs.cs4330.fragmentexercise;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatternDetailFragment extends ListFragment {


    public PatternDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pattern_detail, container, false);
    }

    public void setPattern(String name) {
        View view = getView();
        TextView nameView = view.findViewById(R.id.nameView);
        nameView.setText(name);
        DesignPattern pattern = DesignPattern.find(name);
        if (pattern != null) {
            TextView categoryView = view.findViewById(R.id.categoryView);
            categoryView.setText(pattern.category.name);
            TextView descriptionView = view.findViewById(R.id.descriptionView);
            descriptionView.setText(pattern.description);
        }
    }

}
