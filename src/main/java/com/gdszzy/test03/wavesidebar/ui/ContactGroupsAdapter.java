package com.gdszzy.test03.wavesidebar.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdszzy.test03.R;

import java.util.List;

/**
 * Contact联系人适配器
 */

public class ContactGroupsAdapter extends RecyclerView.Adapter<ContactGroupsAdapter.ContactsViewHolder> {

    private Context context;
    private List<ContactModel.ContactGroup> contactGroups;
    private static final String TAG = "ContactGroupsAdapter";
    private int lastItem = -1;
    private int currentItem = -1;
    private int currentChildItem = -1;

    public ContactGroupsAdapter(List<ContactModel.ContactGroup> contactGroups) {
        this.contactGroups = contactGroups;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layaout_item_contacts, null);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        ContactModel.ContactGroup contactGroup = contactGroups.get(position);
        holder.tvIndex.setVisibility(View.VISIBLE);
        holder.tvIndex.setText(contactGroup.getIndex());
        ContactsAdapter mAdapter = new ContactsAdapter(contactGroup.getList()) {
            @Override
            void onSelect(ContactModel contact, int childPosition) {
                if (currentItem != position) {
                    lastItem = currentItem;
                    currentItem = position;
                    currentChildItem = childPosition;
                    setSelectItem();
                } else {
                    if (currentChildItem != childPosition) {
                        setSelect(currentChildItem, childPosition);
                        currentChildItem = childPosition;
                    }
                }
            }
        };
        if (currentItem == position) {
            mAdapter.setSelect(currentChildItem);
        }
        holder.itemRecyclerView.setItemAnimator(null);
        holder.itemRecyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        holder.itemRecyclerView.setAdapter(mAdapter);
    }

    private void setSelectItem() {
        if (lastItem != -1)
            notifyItemChanged(lastItem);
        notifyItemChanged(currentItem);
    }

    @Override
    public int getItemCount() {
        return contactGroups.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex;
        RecyclerView itemRecyclerView;

        ContactsViewHolder(View itemView) {
            super(itemView);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
            itemRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_recycler);
        }
    }
}
