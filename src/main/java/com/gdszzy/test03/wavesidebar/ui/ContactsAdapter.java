package com.gdszzy.test03.wavesidebar.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gdszzy.test03.R;

import java.util.List;

/**
 * Contact联系人适配器
 */

public abstract class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<ContactModel> contacts;
    private static final String TAG = "ContactsAdapter";
    private int currentItem = -1;

    public ContactsAdapter(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_item_contacts, null);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        ContactModel contact = contacts.get(position);
        holder.iconName.setText(contact.getName());
        holder.iconBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelect(contact, position);
            }
        });
        if (currentItem == position) {
            holder.iconState.setVisibility(View.VISIBLE);
        } else {
            holder.iconState.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView iconName;
        ImageView iconState;
        ImageView iconHead;
        RelativeLayout iconBg;
        ContactsViewHolder(View itemView) {
            super(itemView);
            iconName = (TextView) itemView.findViewById(R.id.icon_name);
            iconState = (ImageView) itemView.findViewById(R.id.icon_state);
            iconHead = (ImageView) itemView.findViewById(R.id.icon_head);
            iconBg = (RelativeLayout) itemView.findViewById(R.id.icon_bg);
        }
    }

    public void setSelect(int currentItem) {
        this.currentItem = currentItem;
    }

    public void setSelect(int lastItem, int currentItem) {
        this.currentItem = currentItem;
        if (lastItem != -1)
            notifyItemChanged(lastItem);
        notifyItemChanged(currentItem);
    }

    abstract void onSelect(ContactModel contact, int position);
}
