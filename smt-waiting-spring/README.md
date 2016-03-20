<!---
Copyright 2015 Karl Bennett

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
smt-waiting-spring
===========
[![Build Status](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent.svg)](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent) [![Coverage Status](https://coveralls.io/repos/shiver-me-timbers/smt-waiting-parent/badge.svg?branch=master&service=github)](https://coveralls.io/github/shiver-me-timbers/smt-waiting-parent?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting-spring/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting-spring/)

This library contains the [`SpringOptions`](src/main/java/shiver/me/timbers/waiting/SpringOptions.java) that can be
used to configure a [`Waiter`](../smt-waiting/src/main/java/shiver/me/timbers/waiting/Waiter.java) instance. If used,
any options that haven't been set will check within the
[Spring context](src/main/java/shiver/me/timbers/waiting/SpringPropertyGetter.java) for any related property values. The
Spring properties that can be set are exactly the same as the [JVM](../smt-waiting#properties) properties.

## Usage

```java
import shiver.me.timbers.waiting.Waiter;
import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.Until;

class Examples {
    public void all() {

        new Waiter( new SpringOptions()).wait(new Until<Void>() {
            public Void success() throws Throwable {
                System.out.println("This wait will use any waiter options set within the Spring context.");
                return null;
            }
        });
    }
}
```

#### WaiterAspect

The [`WaiterAspect`](../smt-waiting-aspect/src/main/java/shiver/me/timbers/waiting/WaiterAspect.java) will also start
using global Spring properties just by having this library in the class path. This is because it uses the
[Java service API](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html) to auto load any custom
implementation of the `Waiter` and [`Options`](../smt-waiting/src/main/java/shiver/me/timbers/waiting/Options.java).

```java
import shiver.me.timbers.waiting.Wait;

class Examples {

    @Wait
    public void UseSpringProperties() {
        System.out.println("Use any global Spring properties if smt-waiting-spring is in the class path.");
    }
}
```