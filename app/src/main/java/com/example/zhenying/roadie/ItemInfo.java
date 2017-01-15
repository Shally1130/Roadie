package com.example.zhenying.roadie;

/**
 * Created by Zhenying on 1/13/17.
 */

public class ItemInfo {
    private String mCreateDate;
    private String mPickUpLocation;
    private String mDeliveryLocation;
//    private String mDistance;
//    private String mTimeLimited;
//    private String mSize;
    private int mTotalPrice;
    private int mImageId;

    public String getmCreateDate() {
        return mCreateDate;
    }

    public void setmCreateDate(String mCreateDate) {
        this.mCreateDate = mCreateDate;
    }

    public String getmPickUpLocation() {
        return mPickUpLocation;
    }

    public void setmPickUpLocation(String mPickUpLocation) {
        this.mPickUpLocation = mPickUpLocation;
    }

    public String getmDeliveryLocation() {
        return mDeliveryLocation;
    }

    public void setmDeliveryLocation(String mDeliveryLocation) {
        this.mDeliveryLocation = mDeliveryLocation;
    }

    public int getmTotalPrice() {
        return mTotalPrice;
    }

    public void setmTotalPrice(int mTotalPrice) {
        this.mTotalPrice = mTotalPrice;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }
}
