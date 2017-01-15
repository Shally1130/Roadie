package com.example.zhenying.roadie;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static android.support.v7.widget.DividerItemDecoration.*;

/**
 * Created by Zhenying on 1/14/17.
 */

public class ListItemsFragment extends Fragment {
    private ArrayList<ItemInfo> mIntemList;
    private RecyclerView mItemListRecyclerView;
    private ItemListAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("search_result.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    public void onCreate(Bundle savedInstanceState) {

        mIntemList = new ArrayList<>();
        getResult(mIntemList);
        super.onCreate(savedInstanceState);
    }

    private void getResult(ArrayList<ItemInfo> mIntemList) {
        try {
            JSONObject json = new JSONObject(loadJSONFromAsset());
            JSONArray resultArray = json.getJSONArray("gigs");
            for (int i = 0; i < resultArray.length(); i++) {
                ItemInfo itemInfo = new ItemInfo();
                itemInfo.setmCreateDate(resultArray.getJSONObject(i).getString("created_at"));
                itemInfo.setmDeliveryLocation(resultArray.getJSONObject(i).getJSONObject("pickup_location").getString("city")+", "
                        +resultArray.getJSONObject(i).getJSONObject("pickup_location").getString("state"));
                //Log.d("!!!!!!!!!!!!",resultArray.getJSONObject(i).getJSONObject("pickup_location").getString("city")+","
                //+resultArray.getJSONObject(i).getJSONObject("pickup_location").getString("state"));
                itemInfo.setmPickUpLocation(resultArray.getJSONObject(i).getJSONObject("delivery_location").getString("city")+", "
                        +resultArray.getJSONObject(i).getJSONObject("pickup_location").getString("state"));
                itemInfo.setmTotalPrice(resultArray.getJSONObject(i).getJSONObject("pricing").getInt("total_price"));
//                //Log.d("!!!!!!!!!!!!",String.valueOf(resultArray.getJSONObject(i).getJSONObject("pricing").getInt("total_price")));
                itemInfo.setmImageId(R.drawable.image);
                mIntemList.add(itemInfo);
                Collections.shuffle(mIntemList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private class ItemListAdapter extends RecyclerView.Adapter<ItemHolder>{
        private ArrayList<ItemInfo> mIntemList;
        public ItemListAdapter(ArrayList<ItemInfo> itemInfo){
            mIntemList = itemInfo;
        }
        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_listitems,parent,false);
            return new ItemHolder(view);
        }
        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            ItemInfo itemInfo = mIntemList.get(position);
            holder.bindData(itemInfo);

        }
        @Override
        public int getItemCount() {
            return mIntemList.size();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mItemListRecyclerView = (RecyclerView) view
                .findViewById(R.id.list);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(mItemListRecyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
        mItemListRecyclerView.addItemDecoration(itemDecoration);
        mItemListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIntemList = new ArrayList<ItemInfo>();
                        getResult(mIntemList);
                        updateUI();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
        return view;
    }



    private void updateUI(){
        mAdapter = new ItemListAdapter(mIntemList);
        mItemListRecyclerView.setAdapter(mAdapter);
    }

}
