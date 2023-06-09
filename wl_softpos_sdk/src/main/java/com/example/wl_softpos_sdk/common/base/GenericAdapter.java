package com.example.wl_softpos_sdk.common.base;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<GenericViewHolder> implements Filterable {

    private List<T> listItems;
    private final List<T> mainList;
    private final LayoutInflater mInflater;

    protected GenericAdapter(Context context) {
        this.listItems = new ArrayList<>();
        mainList=new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void updateData(List<T> listItemsP) {
        //TODO: listItems to listItemsP
        if (listItemsP == null) {
            listItemsP = new ArrayList<>();
        }
        listItems = listItemsP;
        mainList.clear();
        mainList.addAll(listItems);
        notifyDataSetChanged();
    }


    public void submitList(List<T> listItemsP) {
        listItems.addAll(listItemsP);
        mainList.addAll(listItems);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenericViewHolder<V>(DataBindingUtil.inflate(mInflater, getLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        onBindView((V) holder.mBinding, listItems.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public abstract int getLayoutId();

    public abstract void onBindView(V binding, T item, int position);
    public abstract boolean getFilterData(T item,String charSequence);


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listItems = mainList;
                } else {
                    List<T> filteredList = new ArrayList<>();
                    for (T model : mainList) {
                        if (getFilterData(model,charString)) {
                            filteredList.add(model);
                        }
                    }
                    listItems = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listItems = (ArrayList<T>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    protected  boolean isContains(T item,String charSequence){
        return false;
    }


}
