package edu.utep.cs.cs4330.testview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<Person> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView name;
        TextView birthday;
        TextView sex;
    }

    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        String birthday = getItem(position).getBirthday();
        String sex = getItem(position).getSex();

        //Create the person object with the information
        Person person = new Person(name,birthday,sex);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.birthday = (TextView) convertView.findViewById(R.id.textView2);
            holder.sex = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        holder.name.setText(person.getName());
        holder.birthday.setText(person.getBirthday());
        holder.sex.setText(person.getSex());

        return convertView;
    }
}