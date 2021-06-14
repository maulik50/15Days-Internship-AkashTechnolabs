package com.example.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ProductList> arrayList;
    ProductAdapter adapter;

    String[] productNameArray0 = {"Beet"};
    String[] priceArray0 = {"50"};
    String[] unitArray0 = {"250 GM"};
    int[] productImageArray0 = {R.drawable.beet};

    String[] productNameArray1 = {"Apple","Banana"};
    String[] priceArray1 = {"200","40"};
    String[] unitArray1 = {"1 KG","12 Item"};
    int[] productImageArray1 = {R.drawable.apple,R.drawable.banana};

    String[] productNameArray2 = {"Blue Berries","Grapes","Strawberry"};
    String[] priceArray2 = {"150","100","60"};
    String[] unitArray2 = {"500 GM","1 KG","4 Box"};
    int[] productImageArray2 = {R.drawable.blue_blueberries,R.drawable.grapes,R.drawable.strawberry};

    String[] productNameArray3 = {"Green Chilli","Red Chilli"};
    String[] priceArray3 = {"20","30"};
    String[] unitArray3 = {"100 GM","100 GM"};
    int[] productImageArray3 = {R.drawable.green_chilli,R.drawable.red_chillies};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.product_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = getIntent().getExtras();
        int iPosition = bundle.getInt("categoryPosition");
        getSupportActionBar().setTitle(bundle.getString("name"));
        arrayList = new ArrayList<>();
        if(iPosition==0){
            for(int i=0;i<productNameArray0.length;i++){
                ProductList list = new ProductList();
                list.setName(productNameArray0[i]);
                list.setImage(productImageArray0[i]);
                list.setPrice(priceArray0[i]);
                list.setUnit(unitArray0[i]);
                arrayList.add(list);
            }
        }
        if(iPosition==1){
            for(int i=0;i<productNameArray1.length;i++){
                ProductList list = new ProductList();
                list.setName(productNameArray1[i]);
                list.setImage(productImageArray1[i]);
                list.setPrice(priceArray1[i]);
                list.setUnit(unitArray1[i]);
                arrayList.add(list);
            }
        }
        if(iPosition==2){
            for(int i=0;i<productNameArray2.length;i++){
                ProductList list = new ProductList();
                list.setName(productNameArray2[i]);
                list.setImage(productImageArray2[i]);
                list.setPrice(priceArray2[i]);
                list.setUnit(unitArray2[i]);
                arrayList.add(list);
            }
        }
        if(iPosition==3){
            for(int i=0;i<productNameArray3.length;i++){
                ProductList list = new ProductList();
                list.setName(productNameArray3[i]);
                list.setImage(productImageArray3[i]);
                list.setPrice(priceArray3[i]);
                list.setUnit(unitArray3[i]);
                arrayList.add(list);
            }
        }
        adapter = new ProductAdapter(ProductActivity.this,arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {

        Context context;
        ArrayList<ProductList> arrayList;

        public ProductAdapter(ProductActivity productActivity, ArrayList<ProductList> arrayList) {
            this.context = productActivity;
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product,parent,false);
            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name,price;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.custom_product_image);
                name = itemView.findViewById(R.id.custom_product_name);
                price = itemView.findViewById(R.id.custom_product_price);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.imageView.setImageResource(arrayList.get(position).getImage());
            holder.name.setText(arrayList.get(position).getName());
            holder.price.setText(getResources().getString(R.string.price_symbol)+arrayList.get(position).getPrice()+"/"+arrayList.get(position).getUnit());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name",arrayList.get(position).getName());
                    bundle.putString("price",getResources().getString(R.string.price_symbol)+arrayList.get(position).getPrice()+"/"+arrayList.get(position).getUnit());
                    bundle.putInt("image",arrayList.get(position).getImage());
                    Intent intent = new Intent(context,ProductDetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}