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

import com.drisoftie.frags.IFragManagedMenu;
import com.drisoftie.frags.INotificationForwarder;

/**
 * 
 * @author Alexander Dridiger
 */
public abstract class FragPageMenu extends FragManagedMenu implements IFragManagedMenu {

    @Override
    public INotificationForwarder getNotificationForwarder() {
        return (FragManagedPaging) getParentFragment();
    }
}
