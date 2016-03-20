/*
 * Copyright 2015 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shiver.me.timbers.waiting;

/**
 * This enum is used to set boolean annotation attributes. Setting the attributes default value as {@link #UNDECIDED}
 * makes it possible to know if a value has been set with the annotation or not. If this is the case then the attribute
 * can be set with any related global property value.
 *
 * @author Karl Bennett
 */
public enum Decision {
    YES,
    NO,
    UNDECIDED
}
