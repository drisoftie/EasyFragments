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

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @author Alexander Dridiger
 * 
 */
public interface IFragManaged {

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
	 * @param id
	 * @return
	 */
	View findViewById(int id);

	/**
	 * A change occurred and should be handled here.
	 * 
	 * @param args
	 */
	void onChangeOccurred(Object... args);
	
	
	/**
	 * @return
	 */
	INotificationForwarder getNotificationForwarder();
}
