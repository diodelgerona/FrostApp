package com.diodel.frostapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reference {

    @SerializedName("@context")
    @Expose
    private String context;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("apiVersion")
    @Expose
    private String apiVersion;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("queryTime")
    @Expose
    private Double queryTime;
    @SerializedName("currentItemCount")
    @Expose
    private Integer currentItemCount;
    @SerializedName("itemsPerPage")
    @Expose
    private Integer itemsPerPage;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("totalItemCount")
    @Expose
    private Integer totalItemCount;
    @SerializedName("currentLink")
    @Expose
    private String currentLink;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Double queryTime) {
        this.queryTime = queryTime;
    }

    public Integer getCurrentItemCount() {
        return currentItemCount;
    }

    public void setCurrentItemCount(Integer currentItemCount) {
        this.currentItemCount = currentItemCount;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(Integer totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public String getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(String currentLink) {
        this.currentLink = currentLink;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}