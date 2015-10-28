package com.summer.and.diana.adventuretime.models;

import android.content.Context;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.summer.and.diana.adventuretime.R;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Guest on 10/28/15.
 */

@Table(name="Gear", id = "_id")
public class Gear extends Model {
    @Column (name="lender")
    private String mLender;

    @Column (name="borrower")
    private String mBorrower;

    @Column (name="item")
    private String mItem;

    @Column (name="description")
    private String mDescription;

    @Column (name="checkout_date")
    private long mCheckoutDate;

    public Gear() {
        super();
    }

    public Gear(String lender, String item, String description) {
        mLender = lender;
        mBorrower = null;
        mItem = item;
        mDescription = description;
        mCheckoutDate = 0;
    }

    public long getCheckoutDate() {
        return mCheckoutDate;
    }

    public void setCheckoutDate(long checkoutDate) {
        mCheckoutDate = checkoutDate;
    }

    public String getFormattedCheckoutDate(Context context) {
        SimpleDateFormat formatter = new SimpleDateFormat(context.getString(R.string.formatted_checkout_date));
        formatter.setTimeZone(TimeZone.getTimeZone(context.getString(R.string.timeZone)));
        return formatter.format(mCheckoutDate);
    }

    public String getLender() {
        return mLender;
    }

    public void setLender(String lender) {
        mLender = lender;
    }

    public String getBorrower() {
        return mBorrower;
    }

    public void setBorrower(String borrower) {
        mBorrower = borrower;
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
