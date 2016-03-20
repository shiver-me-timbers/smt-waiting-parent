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
smt-waiting-parent
===========
[![Build Status](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent.svg)](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent) [![Coverage Status](https://coveralls.io/repos/shiver-me-timbers/smt-waiting-parent/badge.svg?branch=master&service=github)](https://coveralls.io/github/shiver-me-timbers/smt-waiting-parent?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting-parent/)

This project contains libraries that can be used to wait on code execution until it finishes successfully. This can be
helpful for instance when writing acceptance tests that need to wait for some JavaScript on a page to finish executing
before an element can be found or interacted with.

## [smt-waiting](smt-waiting)

This is the core library that contains the [`Waiter`](smt-waiting/src/main/java/shiver/me/timbers/waiting/Waiter.java)
that is used to repeatedly execute code until it succeeds or times out. The definition of success can be set with
[`Options`](smt-waiting/src/main/java/shiver/me/timbers/waiting/Options.java).

```java
import shiver.me.timbers.waiting.Waiter;
import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;

class Examples {
    public void all() {

        new Waiter(new Options().willWaitForTrue(true)).wait(new Until<Boolean>() {
            private Exception exception;
            private boolean hasRun = false;

            public Boolean success() throws Throwable {
                System.out.println("Run this code thrice.");
                if (exception == null) {
                    throw (exception = new Exception());
                }
                return hasRun || !(hasRun = true);
            }
        });
    }
}
```

## [smt-waiting-aspect](smt-waiting-aspect)

With this library it is possible to wait on methods and classes by simply applying the
['@Wait'](smt-waiting-aspect/src/main/java/shiver/me/timbers/waiting/Wait.java) annotation.

```java
import shiver.me.timbers.waiting.Wait;

class Examples {

    @Wait
    class WaitOnAllMethods {
        public void one() {
        }
        public void two() {
        }
    }

    class WaitOnOneMethod {

        @Wait
        public void one() {
        }
        public void two() {
        }
    }
}
```

## [smt-waiting-spring](smt-waiting-spring)

This library allows the global configuration of all `Waiter`s with Spring properties.