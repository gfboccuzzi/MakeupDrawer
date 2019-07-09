package edu.wit.mobilepp.md3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExpiringFragment extends Fragment {
    private FragmentTabHost mTabHost;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_expiring);

        Bundle arg1 = new Bundle();
        arg1.putInt("Arg for Frag1", 1);
        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("Makeup"),
                ExpiringFragment_MakeupTab.class, arg1);

        Bundle arg2 = new Bundle();
        arg2.putInt("Arg for Frag2", 2);
        mTabHost.addTab(mTabHost.newTabSpec("Tab2").setIndicator("Skincare"),
                ExpiringFragment_SkincareTab.class, arg2);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
