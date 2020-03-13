package com.diodel.frostapp.interfaces;

import com.diodel.frostapp.base.BaseView;
import com.diodel.frostapp.model.Reference;
import com.diodel.frostapp.model.Station;

public interface MainView extends BaseView {
    void getSourceAPIResponse(Station stations);
    void getObservationAPIResponse(Reference reference);

}
