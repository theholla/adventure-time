package com.summer.and.diana.adventuretime.models;

import android.content.Context;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.ui.BrowseGearActivity;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
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
        //mBorrower = "";
        mItem = item;
        mDescription = description;
        //mCheckoutDate = 2;
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

    public static List allFromUser(String username) {
        return new Select()
                .from(Gear.class)
                .where("lender = ?", username)
                .execute();
    }

    public static List all(String username) {
        return new Select()
                .from(Gear.class)
                .where("lender != ?", username)
                .where("checkout_date = ?", 0)
                .orderBy("item")
                .execute();
    }

    public static Gear find(Gear gear) {
        return new Select()
                .from(Gear.class)
                .where("_id = ?", gear.getId())
                .executeSingle();
    }

    public void borrow(String username) {
        Gear item = Gear.find(this);
        long borrowedAt = new Date().getTime();
        item.setCheckoutDate(borrowedAt);
        String borrower = username;
        item.setBorrower(borrower);
        item.save();
    }

    public static List allCheckedOut(String username) {
        return new Select()
                .from(Gear.class)
                .where("borrower = ?", username)
                .orderBy("item")
                .execute();
    }
}
