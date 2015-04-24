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
