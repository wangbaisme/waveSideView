package com.gdszzy.test03.wavesidebar.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdszzy.test03.R;
import com.gdszzy.test03.wavesidebar.view.WaveSideBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ContactModel> mContactModels;
    private List<ContactModel.ContactGroup> mShowModels;
    private RecyclerView mRecyclerView;
    private WaveSideBarView mWaveSideBarView;
    private ContactGroupsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wave);

        initData();
        bindView();
    }

    private void bindView() {

        mAdapter = new ContactGroupsAdapter(mShowModels);

        // RecyclerView设置相关
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mAdapter);


        // 侧边设置相关
        mWaveSideBarView = (WaveSideBarView) findViewById(R.id.main_side_bar);
        mWaveSideBarView.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i=0; i<mShowModels.size(); i++) {
                    if (mShowModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }


    private void initData() {
        mContactModels = new ArrayList<>();
        mShowModels = new ArrayList<>();
        mContactModels.addAll(ContactModel.getContacts());
        mShowModels.addAll(ContactModel.getContactGroup(mContactModels));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mContactModels != null){
            mContactModels.clear();
            mContactModels = null;
        }
    }
}
