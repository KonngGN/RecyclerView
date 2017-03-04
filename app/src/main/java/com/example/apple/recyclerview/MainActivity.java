package com.example.apple.recyclerview;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private String[] datas = new String[]{
            "1", "2", "3", "4", "5", "a", "b", "c", "1", "2", "3", "4", "5", "a", "b", "c", "1", "2", "3", "4", "5", "a", "b", "c", "1", "2", "3", "4", "5", "a", "b", "c"
    };
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recycle);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        refreshLayout.setProgressViewOffset(true, 0, 100);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new MyAdapter());
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final String TAG = "ddd";

        public MyAdapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recycler, parent, false));
            } else if (viewType == 2) {
                return new FootHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footview, parent, false));
            } else {
                return new ImageHololder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sencond, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ViewHolder) {
                ((ViewHolder) holder).textView.setText(datas[position]);
                ((ViewHolder) holder).textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (holder instanceof ImageHololder) {
                ((ImageHololder) holder).imageView.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_launcher));
                ((ImageHololder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });
            } else if (holder instanceof FootHolder) {
                ((FootHolder) holder).progressBar.hide();
                ((FootHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((FootHolder) holder).progressBar.show();
                    }
                });

            }
        }

        @Override
        public int getItemViewType(int position) {
            Log.e(TAG, "position: " + position + " getItemCount(): " + getItemCount());
            String data = datas[position];
            if (data.equals("a")) {
                return 0;
            } else if (position + 1 == getItemCount()) {
                return 2;
            } else if (data.equals("2")) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.text);
            }
        }

        class ImageHololder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public ImageHololder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image);
            }
        }

        class FootHolder extends RecyclerView.ViewHolder {

            private ContentLoadingProgressBar progressBar;
            private LinearLayout linearLayout;

            public FootHolder(View itemView) {
                super(itemView);
                progressBar = (ContentLoadingProgressBar) itemView.findViewById(R.id.progressBar);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
            }
        }


    }

}
