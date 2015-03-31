/*****************************************************************************
 *
 * Copyright 2012-2013 Sony Corporation
 *
 * The information contained here-in is the property of Sony corporation and
 * is not to be disclosed or used without the prior written permission of
 * Sony corporation. This copyright extends to all media in which this
 * information may be preserved including magnetic storage, computer
 * print-out or visual display.
 *
 * Contains proprietary information, copyright and database rights Sony.
 * Decompilation prohibited save as permitted by law. No using, disclosing,
 * reproducing, accessing or modifying without Sony prior written consent.
 *
 ****************************************************************************/
package com.drisoftie.frags.comp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sony.eu.stc.frags.R;
import com.drisoftie.frags.IAdaptManagedPager;
import com.drisoftie.frags.IFragManagedPager;
import com.drisoftie.frags.INotificationForwarder;
import com.drisoftie.frags.NotifyState;

/**
 * A {@link FragManaged} used as a pager with a {@link ViewPager}.
 *
 * @author Alexander Dridiger
 */
public abstract class FragManagedPaging extends FragManaged implements INotificationForwarder, IFragManagedPager<Fragment,
        FragmentManager> {

    /*-###########
     * UI elements
     * ###########*/
    private ViewPager pager;
    private IAdaptManagedPager<FragManaged, FragmentPagerAdapter, Fragment> pagerAdapter;

    /*-#######
     * Actions
     * #######*/
    private DelegationHandler actionDelegateActivityResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(getPagerLayoutId(), container, false);
        pager = (ViewPager) result.findViewById(getViewPagerId());
        pagerAdapter = getPagerAdapter(getChildFragmentManager());
        pager.setAdapter(pagerAdapter.getFragmentPagerAdapter());
        pager.setCurrentItem(getStartPage());

        if (getPageMargin() != null) {
            pager.setPageMargin(getPageMargin());
        }
        if (getPageMarginDrawable() != null) {
            pager.setPageMarginDrawable(getPageMarginDrawable());
        }

        List<Fragment> children = getChildFragmentManager().getFragments();
        if (children != null) {
            pagerAdapter.restoreFragments(children);
        }

        actionDelegateActivityResult = new DelegationHandler();

        return result;
    }

    @Override
    public void forwardChanges(NotifyState state, Object... args) {
        pagerAdapter.processNotifications(state, args);
    }

    /**
     * Returns the currently displayed {@link Fragment}.
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        int index = pager.getCurrentItem();
        return pagerAdapter.getFragment(index);
    }

    /**
     * @return
     */
    public int getCurrentFragmentPage() {
        return pager.getCurrentItem();
    }

    /**
     * @param position
     */
    public void setPage(int position) {
        pager.setCurrentItem(position);
    }

    @Override
    public void onChangeOccurred(Object... args) {
        // do nothing
    }

    /**
     * @param delegation
     * @param intent
     */
    public void sendActivityForResult(Fragment delegation, Intent intent) {
        actionDelegateActivityResult.setDelegation(delegation);
        startActivityForResult(intent, getResources().getInteger(R.integer.intent_start_activity_for_result));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                if (getResources().getInteger(R.integer.intent_start_activity_for_result) == requestCode) {
                    actionDelegateActivityResult.delegate(data);
                }
                break;
        }
    }

    /**
     * @author Alexander Dridiger
     */
    private static class DelegationHandler {

        private Fragment delegation;

        protected void setDelegation(Fragment delegation) {
            this.delegation = delegation;
        }

        protected void delegate(Intent intent) {
            if (delegation != null) {
                delegation.onActivityResult(-1, -1, intent);
                delegation = null;
            }
        }
    }
}