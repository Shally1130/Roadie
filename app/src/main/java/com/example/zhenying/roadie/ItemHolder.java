package com.example.zhenying.roadie;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;

/**
 * Created by Zhenying on 1/14/17.
 */

public class ItemHolder extends RecyclerView.ViewHolder{
    private ItemInfo mItemInfo;
    public ImageView mImageView;
    public TextView mCreateDateTextView;
    public TextView mPickUpLocationTextView;
    public TextView mDeliveryLocationTextView;
    public TextView mTotalPriceTextView;
    public ItemHolder(View itemView) {

        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        mCreateDateTextView = (TextView) itemView.findViewById(R.id.createDate);
        mPickUpLocationTextView = (TextView) itemView.findViewById(R.id.pickUpLocation);
        mDeliveryLocationTextView = (TextView) itemView.findViewById(R.id.deliveryLocation);
        mTotalPriceTextView = (TextView) itemView.findViewById(R.id.price);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),mItemInfo.getmCreateDate() + " clicked!", Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    public void bindData(ItemInfo itemInfo){
        mItemInfo = itemInfo;
        mImageView.setImageResource(itemInfo.getmImageId());
        mCreateDateTextView.setText(itemInfo.getmCreateDate());
        mPickUpLocationTextView.setText(itemInfo.getmPickUpLocation());
        mDeliveryLocationTextView.setText(itemInfo.getmDeliveryLocation());
        mTotalPriceTextView.setText(String.valueOf(itemInfo.getmTotalPrice()));
    }
}
