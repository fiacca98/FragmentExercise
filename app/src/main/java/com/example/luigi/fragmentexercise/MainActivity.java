package com.example.luigi.fragmentexercise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnMenuClickInterface {

    int position = 0;

    PizzaMenuFragment pizzaFragment = new PizzaMenuFragment();
    DetailFragment detailFragment = new DetailFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentManager fragmentManager1 = getFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();

    Bundle currentPosition = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            position = savedInstanceState.getInt("currentPosition", 0);
        }

        setContentView(R.layout.activity_main);



        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if(savedInstanceState == null) {
                transaction.add(R.id.fragmentContainer, pizzaFragment);
                transaction.commit();
            }
            else{
                transaction.replace(R.id.fragmentContainer, pizzaFragment);
                transaction.commit();
            }
        }
        else{
            if(savedInstanceState != null) {
                transaction.replace(R.id.fragmentContainer1, pizzaFragment);
                transaction.commit();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.fragmentContainer2, detailFragment);
                transaction1.commit();
            }
            else{
                transaction.add(R.id.fragmentContainer1, pizzaFragment);
                transaction.commit();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.add(R.id.fragmentContainer2, detailFragment);
                transaction1.commit();
            }

        }


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("currentPosition", this.position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentPosition", this.position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMenuSelected(int position) {
            this.position = position;
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.fragmentContainer, detailFragment);
                transaction1.addToBackStack(null);
                transaction1.commit();
            }
            else{
                detailFragment.updateView();

            }

    }

    @Override
    public int returnPosition() {
        return this.position;
    }
}
