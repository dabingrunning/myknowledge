package com.dabing.myknowledge.hostory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.dabing.myknowledge.R;

/**
 * Created by MyKnowledge on 2017/5/18.
 */

public class HistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, null);
        View viewById = view.findViewById(R.id.drag);
        ViewParent parent = viewById.getParent();
        Log.e("History","parent"+parent);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击拖拽按钮",Toast.LENGTH_LONG).show();
            }
        });
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler_view);
        refreshLayout.setRefreshing(true);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#54bc3f"));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        return view;
    }
}
