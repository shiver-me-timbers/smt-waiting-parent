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

package shiver.me.timbers.waiting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Primary;
import shiver.me.timbers.waiting.WaitingDefaultsClassComponent;
import shiver.me.timbers.waiting.WaitingDurationClassComponent;
import shiver.me.timbers.waiting.WaitingForClassComponent;
import shiver.me.timbers.waiting.WaitingForNotNullClassComponent;
import shiver.me.timbers.waiting.WaitingForTrueClassComponent;
import shiver.me.timbers.waiting.WaitingMethodsComponent;

/**
 * We must return all the instrumented beans with a type of {@link Object}. This is so that they are instantiated at the
 * latest possible moment and thus after the load time weaver has been started. If they were instantiated before the
 * load time weaver is started then it will not be instrumented.
 */
@Configuration
@EnableLoadTimeWeaving
public class WaiterConfiguration {

    @Bean
    @Primary
    public Object waitingDefaultsClassComponent() {
        return new WaitingDefaultsClassComponent();
    }

    @Bean
    @Primary
    public Object waitingDurationClassComponent() {
        return new WaitingDurationClassComponent();
    }

    @Bean
    @Primary
    public Object waitingForClassComponent() {
        return new WaitingForClassComponent();
    }

    @Bean
    @Primary
    public Object waitingForTrueClassComponent() {
        return new WaitingForTrueClassComponent();
    }

    @Bean
    @Primary
    public Object waitingForNotNullClassComponent() {
        return new WaitingForNotNullClassComponent();
    }

    @Bean
    public Object waitingMethodsComponent() {
        return new WaitingMethodsComponent();
    }
}
