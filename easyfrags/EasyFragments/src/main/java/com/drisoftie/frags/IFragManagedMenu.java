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

import android.view.Menu;
import android.view.MenuItem;

/**
 * @author Alexander Dridiger
 * 
 */
public interface IFragManagedMenu {

	/**
	 * Indicates if the {@link Menu} should be cleared before initiating it.
	 * 
	 * @return
	 */
	boolean shouldClearMenu();

    /**
     * Indicates if the {@link Menu} should be inflated.
     *
     * @return
     */
    boolean checkMenuInflation(Menu menu);

	/**
	 * {@link Menu} was created and is ready to work with.
	 */
	void onMenuReady();

	/**
	 * Returns the resource id to the {@link Menu} resource to load.
	 * 
	 * @return
	 */
	int provideMenuRes();

	/**
	 * Returns the inflated {@link Menu}.
	 * 
	 * @return the menu
	 */
	Menu getMenu();

	/**
	 * Indicate that the {@link Menu} should be set to visible when created.
	 * 
	 * @return
	 */
	boolean isVisibleMenu();

	/**
	 * Makes all the given {@link MenuItem}s by their id visible or invisible. If {@code itemIds} are {@code null}, all the {@link MenuItem}
	 * s inside the {@link Menu} are targeted.
	 * 
	 * @param visible
	 * @param itemIds
	 */
	void changeMenuVisibilityById(boolean visible, int... itemIds);

	/**
	 * Makes all the given {@link MenuItem}s visible or invisible. If {@code items} are {@code null}, all the {@link MenuItem}s inside the
	 * {@link Menu} are targeted.
	 * 
	 * @param visible
	 * @param items
	 */
	void changeMenuVisibility(boolean visible, MenuItem... items);
}
