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
smt-waiting-aspect
===========
[![Build Status](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent.svg)](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent) [![Coverage Status](https://coveralls.io/repos/shiver-me-timbers/smt-waiting-parent/badge.svg?branch=master&service=github)](https://coveralls.io/github/shiver-me-timbers/smt-waiting-parent?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting-aspect/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting-aspect/)

This library can be used to apply the [`Waiter`](../smt-waiting/src/main/java/shiver/me/timbers/waiting/Waiter.java) to
any method or class with a simple [`@Wait`](src/main/java/shiver/me/timbers/waiting/Wait.java) annotation.

## Usage

The [`WaiterAspect`](src/main/java/shiver/me/timbers/waiting/WaiterAspect.java) can be applied with
[compile time weaving](../smt-waiting-test/smt-waiting-aspect-compile-time),
[load time weaving](../smt-waiting-test/smt-waiting-aspect-load-time), or even with the use of
[auto proxies](../smt-waiting-test/smt-waiting-aspect-integration). Then the `@Wait` annotation can be added to any
class or method. When annotated at the class level all methods within that class will have the wait applied.

**Note:** The `@Wait` is not inherited, so applying the annotation to a super class or method will have no impact on the
child.

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

    class WaitOnNoMethods extends WaitOnAllMethods {
        public void one() {
        }
        public void two() {
        }
    }
}
```
#### Options

All the [`Options`](../smt-waiting/src/main/java/shiver/me/timbers/waiting/Options.java) can be set with the annotation.
Also annotated `Waiter`s pick up all globally configured values, so any options that haven't been manually overridden
with the annotation can be controlled with
[JVM](../smt-waiting#properties) or [Spring](../smt-waiting-spring) properties.

```java
import java.util.concurrent.TimeUnit;
import shiver.me.timbers.waiting.Wait;
import shiver.me.timbers.waiting.Timeout;
import shiver.me.timbers.waiting.Interval;
import shiver.me.timbers.waiting.Decision;

class Examples {

    @Wait(@Timeout(duration = 1000L, unit = TimeUnit.DAYS))
    public void one() {
        System.out.println("Run this code for a very long time.");
    }

    @Wait(interval = @Interval(duration = 1000L, unit = TimeUnit.DAYS))
    public void two() {
        System.out.println("Pause for a very long time before running this code again.");
    }

    @Wait(waitForTrue = Decision.YES)
    public boolean three() {
        System.out.println("Run this code forever.");
        return false;
    }

    @Wait(waitForNotNull = Decision.YES)
    public String four() {
        return "Only run this once and return this string.";
    }

    public static class BecauseResult implements ResultValidator<String> {
        public boolean isValid(String result) throws Throwable {
            return result.toLowerCase().contains("because");
        }
    }

    private final List<String> sentences = new ArrayList<String>() {{
       add("This will run twice.");
       add("Because it needs to return this sentence.");
    }};

    @Wait(waitFor = BecauseResult.class)
    public String five() {
        final String sentence = sentences.remove(0);
        System.out.println(sentence);
        return sentence;
    }

    @Wait(include = IllegalArgumentException.class)
    public void six() {
        System.out.println("This will run once and explode.");
        throw new Exception();
    }

    private Exception exception;

    @Wait(include = IllegalArgumentException.class)
    public void seven() {
        System.out.println("Where as this will run twice and succeed.");
        if (exception == null) {
            throw (exception = new IllegalArgumentException());
        }
    }

    @Wait(exclude = IllegalArgumentException.class)
    public void eight() {
        System.out.println("This will also run once and explode.");
        throw new IllegalArgumentException();
    }

    @Wait(exclude = IllegalArgumentException.class)
    public void nine() {
        System.out.println("And this will also run twice and succeed.");
        if (exception == null) {
            throw (exception = new Exception());
        }
    }

    private final List<String> otherSentences = new ArrayList<String>() {{
        add("Also all of these options can be combined.");
        add("Just because.");
    }};

    @Wait(
        value = @Timeout(duration = 10L, unit = TimeUnit.MINUTES),
        interval = @Interval(duration = 1L, unit = TimeUnit.MILLISECONDS),
        waitForTrue = Decision.NO,
        waitForNotNull = Decision.YES,
        waitFor = BecauseResult.class,
        include = IllegalStateException.class,
        exclude = IllegalArgumentException.class
    )
    public void ten() {
        final String sentence = otherSentences.remove(0);
        System.out.println(sentence);
        if (exception == null) {
            throw (exception = new IllegalStateException());
        }
        return sentence;
    }
}
```

## License

smt-waiting is released under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)