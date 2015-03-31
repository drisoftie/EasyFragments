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