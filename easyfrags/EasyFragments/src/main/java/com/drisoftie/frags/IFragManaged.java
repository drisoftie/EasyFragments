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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Alexander Dridiger
 */
public interface IFragManaged {

    /**
     * Callback to do work when this {@link Fragment} is created.
     *
     * @param layout             the created view layout
     * @param inflater           given inflater
     * @param container          the fragment container
     * @param savedInstanceState instance state of the holder {@link Activity}
     */
    void onCreating(View layout, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * Callback to do work when this {@link Fragment} is resumed.
     */
    void onResuming();

    /**
     * When the UI is shown, a registration process is started to build the necessary {@link Fragment} components. Must run in the UI
     * {@link Thread}.
     */
    void registerComponents();

    /**
     * Deregistration process started when pausing. Must run in the UI {@link Thread}.
     */
    void deregisterComponents();

    /**
     * The {@link Fragment} is being paused.
     */
    void onPausing();

    /**
     * Delegating method for {@link Activity#findViewById(int)}.
     *
     * @param id the id to search for
     * @return the found {@link View}
     */
    View findViewById(int id);

    /**
     * A change occurred and should be handled here.
     *
     * @param args the forwarded arguments
     */
    void onChangeOccurred(Object... args);

    /**
     * Return the component to use when forwarding notifications.
     *
     * @return the component to forward notifications
     */
    INotificationForwarder getNotificationForwarder();

    /**
     * Get compatible {@link ActionBar} implementation.
     *
     * @return the compatible {@link ActionBar}
     */
    ActionBar actionBar();
}
