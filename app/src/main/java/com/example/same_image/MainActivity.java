package com.example.same_image;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView title_image;
    String image, albumId, id, thumbnailUrl;
    String title;
    EditText search;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Data_Model> data_models = new ArrayList<>();
Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_resfresh);
        title_image = findViewById(R.id.title);
        recyclerView = findViewById(R.id.recyc);
        search = findViewById(R.id.textView_search);

        enableSwipeToDeleteAndUndo();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filter(s.toString());
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                // filter your list from your input
//                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
        data_models.add(new Data_Model("Manish", R.drawable.manish));
        data_models.add(new Data_Model("Devyansh Sharda ", R.drawable.devyansh));
        data_models.add(new Data_Model("Astha ", R.drawable.astha));
        data_models.add(new Data_Model("Riya", R.drawable.riya));
        data_models.add(new Data_Model("Sejal", R.drawable.sejal));
        data_models.add(new Data_Model("Prakriti", R.drawable.prakriti));
        data_models.add(new Data_Model("Nancy", R.drawable.nancy));
        data_models.add(new Data_Model("Shikha", R.drawable.shikha));
        data_models.add(new Data_Model("Sunaina", R.drawable.sunaina));
        data_models.add(new Data_Model("Tanya", R.drawable.tanya));
        data_models.add(new Data_Model("Yusra", R.drawable.yusra));
        data_models.add(new Data_Model("Zeus ", R.drawable.zeus));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RearrangeItems();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
         adapter = new Adapter(data_models, MainActivity.this);
        recyclerView.setAdapter(adapter);

    }

    private void RearrangeItems() {
        Collections.shuffle(data_models, new Random(System.currentTimeMillis()));
        Collections.sort(data_models, new Comparator<Data_Model>() {
            @Override
            public int compare(Data_Model data_model, Data_Model t1) {
                return data_model.getName().compareToIgnoreCase(t1.getName());
            }
        });
        Adapter adapter = new Adapter(data_models, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Data_Model> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Data_Model item : data_models) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
          adapter.filterList(filteredlist);
        }
    }
    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
//                final String item = adapter.getData().get(position);

                adapter.removeItem(position);


//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        adapter.restoreItem(data_models, position);
//                        recyclerView.scrollToPosition(position);
//                    }
////                });
//
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}