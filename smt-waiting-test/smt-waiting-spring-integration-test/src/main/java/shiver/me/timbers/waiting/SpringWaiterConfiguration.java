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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.env.ConfigurableEnvironment;
import shiver.me.timbers.waiting.property.DynamicPropertySource;

@Configuration
@EnableAutoConfiguration
@EnableSpringConfigured
public class SpringWaiterConfiguration {

    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    @ConditionalOnMissingBean(DynamicPropertySource.class)
    public DynamicPropertySource dynamicPropertySource() {
        final DynamicPropertySource propertySource = new DynamicPropertySource();
        environment.getPropertySources().addLast(propertySource);
        return propertySource;
    }
}
