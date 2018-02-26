package com.example.luigi.fragmentexercise;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by luigi on 22/02/2018.
 */

public class DetailFragment extends Fragment {
    OnMenuClickInterface mCallback;
    int position = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView lvItems = (TextView) view.findViewById(R.id.detail);
        this.position = mCallback.returnPosition();
        lvItems.setText(Pizza.pizzaDetails[position]);
    }

    public void updateView(){
        TextView lvItems = (TextView) getView().findViewById(R.id.detail);
        this.position = mCallback.returnPosition();
        lvItems.setText(Pizza.pizzaDetails[position]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnMenuClickInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }
}
