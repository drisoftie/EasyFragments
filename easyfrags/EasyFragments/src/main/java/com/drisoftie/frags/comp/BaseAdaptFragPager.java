/*****************************************************************************
 *
 * Copyright 2012-2014 Sony Corporation
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

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.drisoftie.frags.DelegAdaptManagedPager;
import com.drisoftie.frags.IAdaptManagedPager;
import com.drisoftie.frags.IFragManaged;
import com.drisoftie.frags.NotifyState;

/**
 * A special {@link FragmentPagerAdapter} that supports caching and the notification of its {@link FragManaged}s.
 * Used as a base compatibility v4 implementation.
 * <p/>
 * <b>{@link Fragment}s inside this adapter MUST inherit from {@link FragManaged}.</b>
 *
 * @author Alexander Dridiger
 */
public abstract class BaseAdaptFragPager extends FragmentPagerAdapter implements IAdaptManagedPager<FragManaged,
        FragmentPagerAdapter, Fragment> {

    /**
     * Delegation component.
     */
    private DelegAdaptManagedPager deleg;

    public BaseAdaptFragPager(FragmentManager fm) {
        super(fm);
        deleg = new DelegAdaptManagedPager();
        initFragPageCount();
    }

    @Override
    public int getCount() {
        return deleg.getCount();
    }

    @Override
    public void initFragPageCount() {
        deleg.initFragPageCount(getInitFragPageCount());
    }

    @Override
    public FragManaged getItem(int position) {
        return (FragManaged) deleg.getItem(position, this);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FragManaged f = (FragManaged) super.instantiateItem(container, position);
        // cache instantiated fragments
        deleg.onInstantiateItem(f, position);
        return f;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // remove frome cache
        deleg.onDestroyItem(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public FragManaged getFragment(int position) {
        return (FragManaged) deleg.getFragment(position);
    }

    @Override
    public void restoreFragments(List<Fragment> fragments) {
        List<IFragManaged> frags = new ArrayList<>();
        for (Fragment fragment : fragments) {
            frags.add((IFragManaged) fragment);
        }
        deleg.restoreFragments(frags, this);
    }

    @Override
    public FragmentPagerAdapter getFragmentPagerAdapter() {
        return this;
    }

    @Override
    public void delegateDataSetChanged() {
        notifyDataSetChanged();
    }

    @Override
    public void processNotifications(NotifyState state, Object... args) {
        deleg.processNotifiedChanges(state, this, args);
    }
}
