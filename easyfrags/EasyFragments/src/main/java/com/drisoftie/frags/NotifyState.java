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

/**
 * This enum indicates what information is passed in the context of a {@link FragManagedPaging} and between its
 * child {@link FragManaged}s. Also it's used to pass what operation needs to be done by
 * {@link FragManagedPaging#forwardChanges(NotifyState, Object...)} with its {@link FragManaged}s.
 *
 * @author Alexander Dridiger
 */
public enum NotifyState {

    /**
     * Notify all participating {@link FragManaged}s that something has changed and pass optional data to them.
     */
    NOTIFY_ALL,

    /**
     * Notify {@link FragManagedPaging} that a child {@link FragManaged} should be removed. Or notify
     * all participating {@link FragManaged} that data has been removed and that this change should be accounted for.
     */
    REMOVE,

    /**
     * Notify {@link FragManagedPaging} that a child {@link FragManaged} should be added. Or notify
     * all participating {@link FragManaged} that data has been added and that this change should be accounted for.
     */
    ADD,

    /**
     * Indicates that external input changed data and that this should be accounted for.
     */
    EXTERNAL;
}
