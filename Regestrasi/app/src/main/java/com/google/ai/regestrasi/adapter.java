package com.google.ai.regestrasi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapter extends BaseAdapter {
    private Activity       activity;
    private LayoutInflater inflater;
    private List<Data>     items;

    public adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.kkk, null);

        TextView name = convertView.findViewById(R.id.name);
        TextView user = convertView.findViewById(R.id.user);
        TextView pass = convertView.findViewById(R.id.pass);
        TextView email = convertView.findViewById(R.id.email);
        TextView nohp = convertView.findViewById(R.id.nohp);

        Data data = items.get(position);

        name.setText(data.getName());
        user.setText(data.getUser());
        pass.setText(data.getPass());
        email.setText(data.getEmail());
        nohp.setText(data.getNohp());

        return convertView;
    }
}
