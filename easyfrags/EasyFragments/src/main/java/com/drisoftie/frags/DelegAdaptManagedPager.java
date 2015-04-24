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
package com.drisoftie.frags;

import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * A helper component for {@link IAdaptManagedPager} containing all important functionality.
 *
 * @author Alexander Dridiger
 */
public class DelegAdaptManagedPager {

    /**
     * Array used to cache created {@link IFragManaged}s.
     */
    protected SparseArray<WeakReference<IFragManaged>> frags;

    /**
     * Initialize the amount of needed slots in the fragments arrays.
     *
     * @param amount the amount needed
     */
    public void initFragPageCount(int amount) {
        // make room for the upcoming fragment creation
        frags = new SparseArray<>();
        for (int i = 0; i < amount; i++) {
            frags.put(i, null);
        }
    }

    /**
     * Return the amount of fragments inside the pager.
     *
     * @return the amount of fragments
     */
    public int getCount() {
        return frags.size();
    }

    /**
     * Return the appropriate {@link FragManaged} for the given position.
     *
     * @param position the position of the fragment
     * @param adapt    the adapter managing the fragments
     * @return the fragment on the correct position
     */
    public IFragManaged getItem(int position, IAdaptManagedPager adapt) {
        // find cache or initialize
        IFragManaged f;
        WeakReference<IFragManaged> wr = frags.get(position);
        if (wr != null) {
            f = wr.get();
        } else {
            f = (IFragManaged) adapt.initFragment(position);
            // put into cache
            frags.put(position, new WeakReference<>(f));
        }
        return f;
    }

    /**
     * When fragment instantiation occurred, cache the fragment.
     *
     * @param frag     the fragment to cache
     * @param position the position of the fragment
     */
    public void onInstantiateItem(Object frag, int position) {
        frags.put(position, new WeakReference<>((IFragManaged) frag));
    }

    /**
     * When a fragment gets destroyed, remove from cache.
     *
     * @param position the position of the fragment
     */
    public void onDestroyItem(int position) {
        frags.put(position, null);
    }

    /**
     * Gets a cached fragment on a certain position.
     *
     * @param position the position of the fragment
     * @return the cached fragment
     */
    public IFragManaged getFragment(int position) {
        IFragManaged f = null;
        WeakReference<IFragManaged> wr = frags.get(position);
        if (wr != null) {
            f = wr.get();
        }
        return f;
    }

    /**
     * After config changes occurred, restore the retained fragments.
     *
     * @param fragments the retained fragments
     * @param adapt     the adapter managing the fragments
     */
    public void restoreFragments(List<IFragManaged> fragments, IAdaptManagedPager adapt) {
        for (IFragManaged f : fragments) {
            int index = adapt.getIndexFor(f);
            if (index > -1) {
                this.frags.put(index, new WeakReference<>(f));
            }
        }
    }

    /**
     * Processes information about needed operations to do on fragments and forwards notifications based on that.
     *
     * @param state the state indicating what operation to do
     * @param adapt the adapter managing the fragments
     * @param args  the information to forward
     */
    public void processNotifiedChanges(NotifyState state, IAdaptManagedPager adapt, Object... args) {
        switch (state) {
            case ADD: {
                int amount = (Integer) args[0];
                for (int i = 0; i < amount; i++) {
                    frags.put(frags.size() + i, null);
                }
                notifyAllFrags(args);
                adapt.delegateDataSetChanged();
                break;
            }
            case NOTIFY_ALL:
                notifyAllFrags(args);
                break;
            case REMOVE:
                int amount = (Integer) args[0];
                for (int i = 1; i == amount; i++) {
                    frags.delete(frags.size() - i);
                }
                notifyAllFrags(args);
                adapt.delegateDataSetChanged();
                break;
            case EXTERNAL:
                break;
            default:
                break;
        }
    }

    /**
     * Notifies all fragments about changes and forwards data to them.
     *
     * @param args the information to forward
     */
    private void notifyAllFrags(Object... args) {
        for (int i = 0; i < frags.size(); i++) {
            WeakReference<IFragManaged> wr = frags.get(i);
            if (wr != null) {
                wr.get().onChangeOccurred(args);
            }
        }
    }
}