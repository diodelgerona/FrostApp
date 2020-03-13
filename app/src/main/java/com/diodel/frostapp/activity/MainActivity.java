package com.diodel.frostapp.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.diodel.frostapp.R;
import com.diodel.frostapp.adapter.CustomHeaderAdapter;
import com.diodel.frostapp.adapter.ObservationAdapter;
import com.diodel.frostapp.base.BaseActivity;
import com.diodel.frostapp.interfaces.MainView;
import com.diodel.frostapp.model.Datum;
import com.diodel.frostapp.model.Reference;
import com.diodel.frostapp.model.Station;
import com.diodel.frostapp.presenter.MainPresenter;
import com.diodel.frostapp.util.AppConstants;
import com.diodel.frostapp.util.CommonUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codecrafters.tableview.TableView;

public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter presenter;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.tv_municipality)
    TextView tvMunicipality;
    @BindView(R.id.tv_short_name)
    TextView tvShortName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tableView)
    TableView tableView;
    @BindView(R.id.tv_date_from)
    TextView tvDateFrom;
    @BindView(R.id.tv_date_to)
    TextView tvDateTo;
    private String id;
    private String currentDate, dateAfter;
    private static final String[] TABLE_HEADERS = {"Date", "Temperature", "Wind Speed", "Weather Condition"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        currentDate = CommonUtils.getCurrentDate();
        dateAfter = CommonUtils.aDayBeforeCurrentDate();
        tvDateTo.setText(currentDate);
        tvDateFrom.setText(dateAfter);
        CustomHeaderAdapter headerAdapter = new CustomHeaderAdapter(this, TABLE_HEADERS);
        headerAdapter.setTextSize(13);
        headerAdapter.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        tableView.setHeaderAdapter(headerAdapter);
        presenter = new MainPresenter(this);
        presenter.getSourceAPI();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getSourceAPIResponse(Station stations) {
        List<String> station = new ArrayList<>();
        for (Station.Datum datum : stations.data) {
            station.add(datum.getName());
        }
        spinner.setItems(station);
        setSelectedStation(stations, 0);

        spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {
            setSelectedStation(stations, position);
        });
    }

    @Override
    public void getObservationAPIResponse(Reference reference) {
        List<Datum> datumList = new ArrayList<>();
        long days = CommonUtils.getDateDifference(tvDateFrom.getText().toString(), tvDateTo.getText().toString());
        if (days > 1) {
            TABLE_HEADERS[0] = AppConstants.HEADER_DATE;
            for (int i = 0; i < reference.getData().size(); i++) {
                if (CommonUtils.getTimeInDate(reference.getData().get(i).getReferenceTime()).equalsIgnoreCase(AppConstants.MIDDAY)) {
                    datumList.add(reference.getData().get(i));
                }
            }
        } else {
            TABLE_HEADERS[0] = AppConstants.HEADER_TIME;
            for (int i = 0; i < reference.getData().size(); i++) {
                int index = reference.getData().get(i).getReferenceTime().indexOf(":");
                int min = Integer.parseInt(reference.getData().get(i).getReferenceTime().substring(index + 1, index + 2));
                if (min == 0) {
                    datumList.add(reference.getData().get(i));
                }
            }
        }

        ObservationAdapter adapterObs = new ObservationAdapter(MainActivity.this, datumList, days);
        tableView.setDataAdapter(adapterObs);

    }

    private void setSelectedStation(Station station, int position) {
        tvMunicipality.setText(station.getData().get(position).getMunicipality());
        tvShortName.setText(station.getData().get(position).getShortName());
        tvName.setText(station.getData().get(position).getName());
        id = station.getData().get(position).getId();
        presenter.getObservationAPI(id, CommonUtils.formatDateForAPI(tvDateFrom.getText().toString()) + "/" + CommonUtils.formatDateForAPI(tvDateTo.getText().toString()));

    }

    private void loadDatePicker(TextView textView) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        textView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    @OnClick({R.id.tv_date_to, R.id.iv_date_to})
    public void dateTo(View view) {
        loadDatePicker(tvDateTo);
    }

    @OnClick({R.id.tv_date_from, R.id.iv_date_from})
    public void dateFrom(View view) {
        loadDatePicker(tvDateFrom);
    }

    @OnClick(R.id.btn_refresh)
    public void submit(View view) {
        presenter.getObservationAPI(id, CommonUtils.formatDateForAPI(tvDateFrom.getText().toString()) + "/" + CommonUtils.formatDateForAPI(tvDateTo.getText().toString()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
