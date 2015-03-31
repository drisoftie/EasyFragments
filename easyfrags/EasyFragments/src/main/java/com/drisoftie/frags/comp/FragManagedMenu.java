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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.drisoftie.frags.IFragManagedMenu;

/**
 * A {@link FragManaged} with its own {@link Menu}. Clears the whole {@link Menu} and sets its own.
 *
 * @author Alexander Dridiger
 */
public abstract class FragManagedMenu extends FragManaged implements IFragManagedMenu {

    private MenuInflater menuInflater;
    private Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        if (menuInflater == null) {
            menuInflater = inflater;
        }
        if (shouldClearMenu()) {
            menu.clear();
        }
        menuInflater.inflate(provideMenuRes(), menu);
        if (!isVisibleMenu()) {
            changeMenuVisibility(isVisibleMenu());
        }
        onMenuReady();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        if (menuInflater == null) {
            menuInflater = getActivity().getMenuInflater();
        }
        if (shouldClearMenu()) {
            menu.clear();
        }
        if (checkMenuInflation(menu)) {
            menuInflater.inflate(provideMenuRes(), menu);
        }
        if (!isVisibleMenu()) {
            changeMenuVisibility(isVisibleMenu());
        }
        onMenuReady();
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    @Override
    public void changeMenuVisibilityById(boolean visible, int... itemIds) {
        if (itemIds == null || (itemIds != null && itemIds.length == 0)) {
            changeMenuVisibility(visible);
        } else {
            MenuItem[] items = new MenuItem[itemIds.length];
            for (int i = 0; i < itemIds.length; i++) {
                items[i] = menu.findItem(itemIds[i]);
            }
            changeMenuVisibility(visible, items);
        }
    }

    @Override
    public void changeMenuVisibility(boolean visible, MenuItem... items) {
        if (items == null || (items != null && items.length == 0)) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(visible);
            }
        } else {
            for (MenuItem menuItem : items) {
                menuItem.setVisible(visible);
            }
        }
    }
}
