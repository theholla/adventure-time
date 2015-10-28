package com.summer.and.diana.adventuretime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.models.Gear;

import java.util.ArrayList;

/**
 * Created by Guest on 10/28/15.
 */
public class GearAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Gear> mGearList;

    public GearAdapter(Context context, ArrayList<Gear> gearList) {
        mContext = context;
        mGearList = gearList;
    }

    @Override
    public int getCount() {
        return mGearList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGearList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gear_list_item, null);
            holder = new ViewHolder();
            holder.itemLabel = (TextView) convertView.findViewById(R.id.itemLabel);
            holder.descriptionLabel = (TextView) convertView.findViewById(R.id.descriptionLabel);
            holder.lenderLabel = (TextView) convertView.findViewById(R.id.lenderLabel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Gear gear = mGearList.get(position);

        holder.itemLabel.setText(gear.getItem());
        holder.descriptionLabel.setText(gear.getDescription());
        holder.lenderLabel.setText("Provided by: " + gear.getLender());

        return convertView;
    }

    private static class ViewHolder {
        TextView itemLabel, descriptionLabel, lenderLabel;
    }
}
