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
package com.drisoftie.frags.comp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * A simple skeleton for a managed {@link FragmentActivity}.
 * 
 * @author Alexander Dridiger
 */
public abstract class ManagedActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * Callback to do work when this {@link FragmentActivity} is resumed.
	 */
	protected abstract void onResuming();

	/**
	 * When the UI is shown, a registration process is started to build the necessary {@link FragmentActivity} components. Must run in the
	 * UI {@link Thread}.
	 */
	protected abstract void registerComponents();

	@Override
	public final void onResume() {
		super.onResume();
		onResuming();
		findViewById(android.R.id.content).post(new Runnable() {
			@Override
			public void run() {
				registerComponents();
			}
		});
	}

	/**
	 * Deregistration process started when pausing. Must run in the UI {@link Thread}.
	 */
	protected abstract void deregisterComponents();

	/**
	 * The {@link FragmentActivity} is being paused.
	 */
	protected abstract void onPausing();

	@Override
	public final void onPause() {
		super.onPause();
		onPausing();
		deregisterComponents();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
