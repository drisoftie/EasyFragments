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

import android.support.v4.view.ViewPager;

public interface IFragManagedPager<FragT, FragManagerT> {

    /**
     * Creates and returns the appropriate {@link IAdaptManagedPager}.
     *
     * @param childFragmentManager
     * @return
     */
    <AdaptT extends IAdaptManagedPager> AdaptT getPagerAdapter(FragManagerT childFragmentManager);

    /**
     * @param adaptPager
     */
    <AdaptT extends IAdaptManagedPager> void switchPagerAdapter(AdaptT adaptPager);

    /**
     * Returns the currently displayed {@link FragT}.
     *
     * @return
     */
    FragT getCurrentFragment();

    /**
     * @return
     */
    int getCurrentFragmentPage();

    /**
     * @return the layout id to inflate.
     */
    int getPagerLayoutId();

    /**
     * @return the id of the {@link ViewPager}.
     */
    int getViewPagerId();

    /**
     * @return index of the starting page.
     */
    int getStartPage();

    /**
     * @return
     */
    Integer getPageMargin();

    /**
     * @return
     */
    Integer getPageMarginDrawable();
}