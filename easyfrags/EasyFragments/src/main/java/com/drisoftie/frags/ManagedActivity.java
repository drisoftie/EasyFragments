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

/**
 * A simple skeleton for a managed {@link Activity}.
 * 
 * @author Alexander Dridiger
 */
public abstract class ManagedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * Callback to do work when this {@link Activity} is resumed.
	 */
	protected abstract void onResuming();

	/**
	 * When the UI is shown, a registration process is started to build the necessary {@link Activity} components. Must run in the UI
	 * {@link Thread}.
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
	 * The {@link Activity} is being paused.
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
