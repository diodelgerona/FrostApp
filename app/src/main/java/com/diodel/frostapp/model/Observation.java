package com.diodel.frostapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Observation {

    @SerializedName("elementId")
    @Expose
    private String elementId;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("level")
    @Expose
    private Level level;
    @SerializedName("timeOffset")
    @Expose
    private String timeOffset;
    @SerializedName("timeResolution")
    @Expose
    private String timeResolution;
    @SerializedName("timeSeriesId")
    @Expose
    private Integer timeSeriesId;
    @SerializedName("performanceCategory")
    @Expose
    private String performanceCategory;
    @SerializedName("exposureCategory")
    @Expose
    private String exposureCategory;
    @SerializedName("qualityCode")
    @Expose
    private Integer qualityCode;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(String timeOffset) {
        this.timeOffset = timeOffset;
    }

    public String getTimeResolution() {
        return timeResolution;
    }

    public void setTimeResolution(String timeResolution) {
        this.timeResolution = timeResolution;
    }

    public Integer getTimeSeriesId() {
        return timeSeriesId;
    }

    public void setTimeSeriesId(Integer timeSeriesId) {
        this.timeSeriesId = timeSeriesId;
    }

    public String getPerformanceCategory() {
        return performanceCategory;
    }

    public void setPerformanceCategory(String performanceCategory) {
        this.performanceCategory = performanceCategory;
    }

    public String getExposureCategory() {
        return exposureCategory;
    }

    public void setExposureCategory(String exposureCategory) {
        this.exposureCategory = exposureCategory;
    }

    public Integer getQualityCode() {
        return qualityCode;
    }

    public void setQualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
    }

}