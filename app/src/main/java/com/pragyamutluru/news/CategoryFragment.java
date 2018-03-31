package com.pragyamutluru.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suresh on 27/2/18.
 */

public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<NewsCategory> categoryList;
    CategoryAdapter catAdapter;

    static final String CATEGORY_EXTRA_KEY="com.pragyamutluru.news.CATEGORY_KEY";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initialiseViews(view);




        return view;

    }
    public void initialiseViews(View view){
        recyclerView=view.findViewById(R.id.category_recycler_view);
        categoryList=new ArrayList<>();
        initialiseList();
        recyclerView.setLayoutManager(new GridLayoutManager(CategoryFragment.this.getActivity(),2));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CategoryFragment.this.getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        int itemPosition = position;
                        String cat = categoryList.get(itemPosition).getName();
                        if(cat!=null){
                            Log.i("TAGTAGATCATFRAG",cat);
                            openCatView(cat);
                        }

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        catAdapter= new CategoryAdapter(categoryList);
        recyclerView.setAdapter(catAdapter);


    }
    public void initialiseList(){
        addToList("Business");
        addToList("Health");
        addToList("Sports");
        addToList("Science");
        addToList("General");
        addToList("Technology");





    }

    public void addToList(String name){
        NewsCategory temp= new NewsCategory();
        temp.setName(name);
        categoryList.add(temp);
    }

    //CategoryAdapter
    class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
        private List<NewsCategory> newsCatList;

        public CategoryAdapter(List<NewsCategory> newsCatList) {
            this.newsCatList = newsCatList;
        }

        public void clear() {
            int size = this.newsCatList.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    this.newsCatList.remove(0);
                }

                this.notifyItemRangeRemoved(0, size);
            }
        }

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(CategoryFragment.this.getActivity());

            return new CategoryHolder(layoutInflater,parent);

        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {

            NewsCategory news=newsCatList.get(position);
            holder.bind(news);

        }

        @Override
        public int getItemCount() {
            if(newsCatList!=null)
                return newsCatList.size();
            else return 0;
        }

    }


    //CategoryHolder
    class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private  TextView mTextView;

        public CategoryHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.category_item_layout, parent, false));
            mTextView=itemView.findViewById(R.id.category_item_name);
        }

        public void bind(NewsCategory category){
            String name=category.getName();
            int image=category.getImageResource();
            mTextView.setText(name);

        }

        @Override
        public void onClick(View view) {


        }
    }

    public void openCatView(String category){
        Intent intent= new Intent(CategoryFragment.this.getContext(),CategoryViewActivity.class);
        intent.putExtra(CategoryFragment.CATEGORY_EXTRA_KEY,category);
        startActivity(intent);

    }


}
