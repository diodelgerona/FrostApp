package com.diodel.frostapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diodel.frostapp.R;
import com.diodel.frostapp.model.Datum;
import com.diodel.frostapp.model.Observation;
import com.diodel.frostapp.util.AppConstants;
import com.diodel.frostapp.util.CommonUtils;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class ObservationAdapter extends TableDataAdapter<Datum> {
    private long days;
    public ObservationAdapter(Context context, List<Datum> data,long days) {
        super(context, data);
        this.days = days;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Datum datum = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderTextDataRefTime(days > 1 ? CommonUtils.formatAPIForDATE(datum.getReferenceTime()) :CommonUtils.getTimeInDate(datum.getReferenceTime()));

                break;
            case 1:
                renderedView = renderTextData(getELementValue(datum, AppConstants.AIR_TEMPERATURE));

                break;
            case 2:
                renderedView = renderTextData(getELementValue(datum, AppConstants.WIND_SPEED));
                break;

            case 3:
                Observation val = getELementValue(datum, AppConstants.WEATHER_CONDITION);
                renderedView = renderTextDataWithImage(val.getValue() == null ? -1 : val.getValue());
                break;
        }

        return renderedView;
    }


    private Observation getELementValue(Datum data, String key) {
        double val = -1;
        Observation observation = new Observation();
        for (Observation ob : data.getObservations()) {

            if (ob.getElementId().equalsIgnoreCase(key)) {
                val = ob.getValue();
                observation = ob;
            }
        }
        return observation;
    }


    private View renderTextData(final Observation value) {

        String unitWithVal = value.getValue() == null ? "N/A " :value.getValue()+" "+ value.getUnit();
        final TextView textView = new TextView(getContext());
        textView.setText(unitWithVal);
        textView.setGravity(Gravity.LEFT);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(12);

        return textView;
    }
    private View renderTextDataRefTime(final String value) {

        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setGravity(Gravity.LEFT);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(12);

        return textView;
    }
    private View renderTextDataWithImage(final double value) {
        FrameLayout rootView = new FrameLayout(getContext());
        rootView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        ImageView imageView = new ImageView(getContext());
        FrameLayout.LayoutParams iv_layout_params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, // Width
                FrameLayout.LayoutParams.WRAP_CONTENT, // Height
                Gravity.CENTER);
        imageView.setLayoutParams(iv_layout_params);
        imageView.setImageResource(R.drawable.cloudy);

        final TextView textView = new TextView(getContext());
        textView.setText("N/A");
        textView.setGravity(Gravity.LEFT);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(12);
        textView.setLayoutParams(iv_layout_params);

        if (value == 0) {
            imageView.setImageResource(R.drawable.cloudy);
        } else if (value == 1) {
            imageView.setImageResource(R.drawable.sunny);
        }else {
            rootView.addView(textView);
            imageView.setVisibility(View.INVISIBLE);
        }
        rootView.addView(imageView);

        return rootView;
    }
}
