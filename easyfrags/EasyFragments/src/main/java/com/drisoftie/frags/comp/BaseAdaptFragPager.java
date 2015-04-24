/*
 * Copyright [2015] [Alexander Dridiger - drisoftie@gmail.com]
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package com.drisoftie.frags.comp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.drisoftie.frags.DelegAdaptManagedPager;
import com.drisoftie.frags.IAdaptManagedPager;
import com.drisoftie.frags.IFragManaged;
import com.drisoftie.frags.NotifyState;

import java.util.ArrayList;
import java.util.List;

/**
 * A special {@link FragmentPagerAdapter} that supports caching and the notification of its {@link FragManaged}s.
 * Used as a base compatibility v4 implementation.
 * <p/>
 * <b>{@link Fragment}s inside this adapter MUST inherit from {@link FragManaged}.</b>
 *
 * @author Alexander Dridiger
 */
public abstract class BaseAdaptFragPager extends FragmentPagerAdapter
        implements IAdaptManagedPager<FragManaged, FragmentPagerAdapter, Fragment> {

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
            if (fragment instanceof IFragManaged) {
                frags.add((IFragManaged) fragment);
            }
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
