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
