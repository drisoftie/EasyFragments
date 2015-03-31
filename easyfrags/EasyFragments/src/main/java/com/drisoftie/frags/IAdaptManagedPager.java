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
