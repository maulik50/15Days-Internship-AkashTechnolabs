package com.example.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] nameArray = {"Vegetable", "Fruits", "Berries", "Chilli"};
    int[] imageArray = {R.drawable.vegetable, R.drawable.fruits, R.drawable.berries, R.drawable.chilli};

    ArrayList<CategoryList> categoryArrayList;


    //EditText searchView;
    //AutoCompleteTextView searchView;
    CategoryAdapter adapter;
    ArrayList<String> arrayList;

    RecyclerView productRecyclerview;
    ArrayList<ProductList> productArrayList;
    ProductAdapter productAdapter;

    String[] productNameArray = {"Beet","Green Chilli","Red Chilli","Apple","Banana","Blue Berries","Grapes","Strawberry"};
    String[] priceArray = {"50","20","30","200","40","150","100","60"};
    String[] unitArray = {"250 GM","100 GM","100 GM","1 KG","12 Item","500 GM","1 KG","4 Box"};
    int[] productImageArray = {R.drawable.beet,R.drawable.green_chilli,R.drawable.red_chillies,R.drawable.apple,R.drawable.banana,R.drawable.blue_blueberries,R.drawable.grapes,R.drawable.strawberry};

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        categoryArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            CategoryList list = new CategoryList();
            list.setCategoryName(nameArray[i]);
            list.setCategoryImage(imageArray[i]);
            categoryArrayList.add(list);
            arrayList.add(nameArray[i]);
        }

        //CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this,nameArray,imageArray);
        adapter = new CategoryAdapter(HomeActivity.this, categoryArrayList);
        recyclerView.setAdapter(adapter);

        productRecyclerview = findViewById(R.id.home_product_recyclerview);
        productRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        productRecyclerview.setItemAnimator(new DefaultItemAnimator());

        productArrayList = new ArrayList<>();
        for(int i=0;i<productNameArray.length;i++){
            ProductList list = new ProductList();
            list.setName(productNameArray[i]);
            list.setPrice(priceArray[i]);
            list.setUnit(unitArray[i]);
            list.setImage(productImageArray[i]);
            productArrayList.add(list);
        }
        productAdapter = new ProductAdapter(HomeActivity.this,productArrayList);
        productRecyclerview.setAdapter(productAdapter);


        /*ArrayAdapter adapter = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_list_item_1, arrayList);
        searchView.setThreshold(1);
        searchView.setAdapter(adapter);*/
        /*searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().equalsIgnoreCase("")){
                    adapter.filter("");
                }
                else{
                    adapter.filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(newText.trim().equals("")){
//                    adapter.filter("");
//                    productAdapter.filter("");
//                }
//                else{
//                    adapter.filter(newText);
//                    productAdapter.filter(newText);
//                }
//                return false;
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            finishAffinity();
//        }
//        if(id==R.id.main_menu_chat){
//            new CommonMethod(HomeActivity.this,MessageActivity.class);
//        }
//        if(id==R.id.main_menu_logout){
//            //sp.edit().remove(ConstantUrl.ID).commit();
//            sp.edit().clear().commit();
//            new CommonMethod(HomeActivity.this,LoginActivity.class);
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        //super.onBackPressed();
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

        Context context;
        ArrayList<CategoryList> categoryArrayList;
        ArrayList<CategoryList> searchList;

        public CategoryAdapter(HomeActivity homeActivity, ArrayList<CategoryList> categoryArrayList) {
            this.context = homeActivity;
            this.categoryArrayList = categoryArrayList;
            searchList = new ArrayList<>();
            searchList.addAll(categoryArrayList);
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category, parent, false);
            return new MyHolder(view);
        }

        public void filter(String newText) {
            newText = newText.toLowerCase(Locale.getDefault());
            categoryArrayList.clear();
            if (newText.length() == 0) {
                categoryArrayList.addAll(searchList);
            } else {
                for (CategoryList cat : searchList) {
                    if (cat.getCategoryName().toLowerCase(Locale.getDefault()).contains(newText)) {
                        categoryArrayList.add(cat);
                    }
                }
            }
            notifyDataSetChanged();
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.custom_category_image);
                name = itemView.findViewById(R.id.custom_category_name);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.name.setText(categoryArrayList.get(position).getCategoryName());
            holder.imageView.setImageResource(categoryArrayList.get(position).getCategoryImage());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //new CommonMethod(context,ProductActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("categoryPosition",position);
                    bundle.putString("name",categoryArrayList.get(position).getCategoryName());
                    Intent intent = new Intent(context,ProductActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return categoryArrayList.size();
        }

    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {

        Context context;
        ArrayList<ProductList> productArrayList;
        ArrayList<ProductList> searchList;

        public ProductAdapter(HomeActivity homeActivity, ArrayList<ProductList> productArrayList) {
            this.context = homeActivity;
            this.productArrayList = productArrayList;
            this.searchList = new ArrayList<>();
            searchList.addAll(productArrayList);
        }

        public void filter(String newText) {
            newText = newText.toLowerCase(Locale.getDefault());
            productArrayList.clear();
            if (newText.length() == 0) {
                productArrayList.addAll(searchList);
            } else {
                for (ProductList cat : searchList) {
                    if (cat.getName().toLowerCase(Locale.getDefault()).contains(newText)) {
                        productArrayList.add(cat);
                    }
                }
            }
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_horizontal,parent,false);
            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            TextView name,price;
            ImageView imageView;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.custom_product_horizontal_name);
                price = itemView.findViewById(R.id.custom_product_horizontal_price);
                imageView = itemView.findViewById(R.id.custom_product_horizontal_image);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.imageView.setImageResource(productArrayList.get(position).getImage());
            holder.name.setText(productArrayList.get(position).getName());
            holder.price.setText(getResources().getString(R.string.price_symbol)+productArrayList.get(position).getPrice()+"/"+productArrayList.get(position).getUnit());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name",productArrayList.get(position).getName());
                    bundle.putString("price",getResources().getString(R.string.price_symbol)+productArrayList.get(position).getPrice()+"/"+productArrayList.get(position).getUnit());
                    bundle.putInt("image",productArrayList.get(position).getImage());
                    Intent intent = new Intent(context,ProductDetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return productArrayList.size();
        }

    }

    /*private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

        Context context;
        String[] nameArray;
        int[] imageArray;

        public CategoryAdapter(HomeActivity homeActivity, String[] nameArray, int[] imageArray) {
            this.context = homeActivity;
            this.nameArray = nameArray;
            this.imageArray = imageArray;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.custom_category_name);
                imageView = itemView.findViewById(R.id.custom_category_image);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.name.setText(nameArray[position]);
            holder.imageView.setImageResource(imageArray[position]);
        }

        @Override
        public int getItemCount() {
            return nameArray.length;
        }

    }*/
}