package com.pragyamutluru.news;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CategoryViewActivity extends SingleFragmentActivity {
    String category="";
    Bundle args;
    @Override
    protected Fragment createFragment() {

        category=getIntent().getSerializableExtra(CategoryFragment.CATEGORY_EXTRA_KEY).toString();
        args= new Bundle();
        args.putSerializable("com.pragyamutluru.news.CAT", category);
        Fragment fragment=new CategoryViewFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);


    }
}
