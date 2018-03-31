package com.pragyamutluru.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suresh on 13/2/18.
 */

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>{
    //Views
    RecyclerView recyclerView;

    //Member Variables
    ArrayList<News> newsList;
    NewsAdapter newsAdapter;

    final String SERVE_URL="https://newsapi.org/v2/top-headlines?country=in&apiKey=02c6792e177a4a8998c3df95149f77da";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        initialiseViews(view);
        serveQuery(SERVE_URL);
        return view;

    }

    public void initialiseViews(View view){
        recyclerView=view.findViewById(R.id.news_recycler_view);
        newsList=new ArrayList<>();
        //edit
//        News temp= new News();
//        temp.setSource("source");
//        temp.setDate("23/23/23");
//        temp.setTitle("Title");
//        temp.setDescription("Desc");
//        temp.setImageUrl("https://static.politico.com/c6/cc/a6fd422c421eb60d26935b543caf/180213-chris-wray-ap-1160.jpg");
//        newsList.add(temp);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity()));
        // Create a new {@link ArrayAdapter} of earthquakes
        newsAdapter= new NewsAdapter(newsList);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.getActivity(),recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        int itemPosition = position;
                        String item = newsList.get(itemPosition).getWebUrl();
                        if(item!=null){
                            open(item);
                        }

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void serveQuery(String query){
        query=safeQuery(query);
        String temp=SERVE_URL;

        Bundle bundle= new Bundle();
        bundle.putString("queryString",temp);
        Log.i("QUERY_STRING1",bundle.getString("queryString"));
        getActivity().getSupportLoaderManager().restartLoader(0,bundle,this);

    }
    private String safeQuery(String query) {
        String temp=query;
        temp=temp.replace(" ","+");
        return temp;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        newsAdapter.clear();
        Log.i("QUERY_STRING", args.getString("queryString"));
        return new NewsLoader(this.getActivity(), args.getString("queryString"));
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        if(data!=null && newsList!=null) {
            newsList.addAll(data);

        }
//        if(data.size()==0){
//            noDataAvailable();
//        }
        for(int i=0; i<newsList.size(); i++){
            Log.i("BOOK_LOG",newsList.get(i).getTitle());
        }
        newsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();

    }

    //Adapter Class
    class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {
        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        public void clear() {
            int size = this.newsList.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    this.newsList.remove(0);
                }

                this.notifyItemRangeRemoved(0, size);
            }
        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(HomeFragment.this.getActivity());

            return new NewsHolder(layoutInflater,parent);

        }

        @Override
        public void onBindViewHolder(NewsHolder holder, int position) {

            News news=newsList.get(position);
            holder.bind(news);

        }

        @Override
        public int getItemCount() {
            if(newsList!=null)
                return newsList.size();
            else return 0;
        }

    }
//Holder class

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
      //  private TextView mDescriptionTextView;
        private ImageView mImageView;
        private Button sourceButton;
        private Button dateButton;

        public NewsHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.news_item_layout, parent, false));
            mTitleTextView=itemView.findViewById(R.id.news_item_title);
    //        mDescriptionTextView=itemView.findViewById(R.id.news_item_description);
            mImageView=itemView.findViewById(R.id.news_item_image);
            sourceButton=itemView.findViewById(R.id.news_item_source);
            dateButton=itemView.findViewById(R.id.news_item_date);



        }

        public void bind(News news){
            String title= news.getTitle();
  //          String description=news.getDescription();
            String source=news.getSource();
            String date=news.getDate();
            String imageUrl=news.getImageUrl();



            mTitleTextView.setText(title);
//            mDescriptionTextView.setText(description);
            sourceButton.setText(source);
            dateButton.setText(date);
            Picasso.with(HomeFragment.this.getActivity()).load(imageUrl).into(mImageView);

        }

        @Override
        public void onClick(View view) {
        }
    }

    public void open(String url){
        Intent intent= new Intent(this.getActivity(),ArticleViewActivity.class);
        intent.putExtra(ArticleViewActivity.URL_KEY,url);
        startActivity(intent);

    }
}
