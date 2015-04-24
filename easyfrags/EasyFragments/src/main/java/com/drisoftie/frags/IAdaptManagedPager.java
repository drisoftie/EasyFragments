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

import java.util.List;

/**
 * Interface defining a special {@link android.support.v4.view.PagerAdapter}.<br>
 * {@link FragT} defines what custom fragment implementation to use.<br>
 * {@link AdaptT} defines what fragment pager adapter to use.<br>
 * {@link FragOrigT} defines what original fragment implementation to use.
 *
 * @author Alexander Dridiger
 */
public interface IAdaptManagedPager<FragT, AdaptT, FragOrigT> {

    /**
     * Initialize the amount of pages by initializing the internal fragments array.
     */
    void initFragPageCount();

    /**
     * Get the amount of pages to initialize the fragments amount.
     */
    int getInitFragPageCount();

    /**
     * Get the appropriate {@link FragT} for the given position.
     *
     * @param position
     * @return
     */
    FragT initFragment(int position);

    /**
     * Restore all instances when {@link FragT}s are recreated automatically.
     *
     * @param fragments
     */
    void restoreFragments(List<FragOrigT> fragments);

    /**
     * Returns the correct index for the given {@link FragT}.
     *
     * @param fragment
     * @return
     */
    int getIndexFor(FragT fragment);

    /**
     * Gets the {@link FragT} on the given position.
     *
     * @param position the page
     * @return implemented page as {@link FragT}
     */
    FragT getFragment(int position);

    /**
     * Returns the {@link AdaptT}.
     *
     * @return
     */
    AdaptT getFragmentPagerAdapter();

    /**
     * Delegate to {@link AdaptT#notifyDataSetChanged()};
     */
    void delegateDataSetChanged();

    /**
     * Forwards information about what operation to process and invoke based on {@code state}.
     */
    void processNotifications(NotifyState state, Object... args);
}
