package com.example.feelgoodmentalwellness.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.feelgoodmentalwellness.Adapter.BlockAdapter;
import com.example.feelgoodmentalwellness.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class BlockActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ArrayList<String> list;
    private BlockAdapter adapter;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private Map<String,Object> map;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView warn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);

        init();
        //fetchData();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        list.clear();
        db.collection("User")
                .document(Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail()))
                .collection("Chat")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                map=document.getData();
                                if ((boolean)map.get("Block")){
                                    list.add(document.getId());
                                }
                            }
                            swipeRefreshLayout.setRefreshing(false);
                            if(list.size() > 0){
                                warn.setVisibility(View.GONE);
                            }else{
                                warn.setVisibility(View.VISIBLE);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            Toasty.error(BlockActivity.this,"Error Fetching Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void init(){
        recyclerView= findViewById(R.id.blockedRecycle);
        list= new ArrayList<>();
        db= FirebaseFirestore.getInstance();
        adapter= new BlockAdapter(this, list);
        recyclerView.setAdapter(adapter);
        mAuth= FirebaseAuth.getInstance();
        map= new HashMap<>();
        swipeRefreshLayout=findViewById(R.id.refreshBlock);
        warn=findViewById(R.id.warn_block);
        if (list.size() == 0){
            warn.setVisibility(View.VISIBLE);
        }
    }
}