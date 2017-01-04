package com.example.root.listexample;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    private LinearLayout thumbnails;
    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;
    private RecyclerView rv;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");

        mRequestQueue = Volley.newRequestQueue(this);
        imageLoader =  new ImageLoader(mRequestQueue, new LruBitmapCache(LruBitmapCache.getCacheSize(this)));

        ArrayList<String> imageUrls = new ArrayList<String>();

        imageUrls.add( "http://192.168.1.7/concert.jpg");
        imageUrls.add( "http://192.168.1.7/wine_tasting.jpg" );

        LinearLayout container = (LinearLayout) findViewById(R.id.thumbnails);

        for( int i = 0; i < imageUrls.size(); i++ )
        {
            ImageView image = new ImageView( this );
            image.setLayoutParams(new LinearLayout.LayoutParams(700,800));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageLoader.get( imageUrls.get( i ), ImageLoader.getImageListener( image, R.drawable.cheese_1, R.drawable.cheese_2) );

            container.addView( image );
        }

        rv = (RecyclerView) findViewById(R.id.comment_list);
        rv.setLayoutManager(new LinearLayoutManager(this));

        updateList();
    }

    private void updateList() {
        adapter = new CommentAdapter(EventActivity.this);
        rv.setAdapter(adapter);
    }
}
