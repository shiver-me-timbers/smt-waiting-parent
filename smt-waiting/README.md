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
smt-waiting
===========
[![Build Status](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent.svg)](https://travis-ci.org/shiver-me-timbers/smt-waiting-parent) [![Coverage Status](https://coveralls.io/repos/shiver-me-timbers/smt-waiting-parent/badge.svg?branch=master&service=github)](https://coveralls.io/github/shiver-me-timbers/smt-waiting-parent?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-waiting/)

A library to help you wait until some code has executed successfully. Where you can also define what success means.

## Usage

#### Waiter

A [`Waiter`](src/main/java/shiver/me/timbers/waiting/Waiter.java) can be used to wait for some code to complete
successfully. This means it will rerun the code over and over again until it succeeds by not throwing an exception and
fulfilling any extra [`Options`](src/main/java/shiver/me/timbers/waiting/Options.java) set on the waiters creation.

```java
new Waiter().wait(new Until<Void>() {
    public Void success() throws Throwable {
        System.out.println("Run this code once.");
        return null;
    }
});

new Waiter().wait(new Until<Void>() {
    private Exception exception;

    public Void success() throws Throwable {
        System.out.println("Run this code twice.");
        if (exception == null) {
            throw (exception = new Exception());
        }
        return null;
    }
});

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
```
#### Options

The `Options` can be used to customise how the `Waiter` waits on the code.

```java
new Waiter(new Options().withTimeout(1000L, TimeUnit.DAYS)).wait(new Until<Void>() {
    public Void success() throws Throwable {
        System.out.println("Run this code for a very long time.");
        throw new Exception();
    }
});

new Waiter(new Options().withInterval(1000L, TimeUnit.DAYS)).wait(new Until<Void>() {
    public Void success() throws Throwable {
        System.out.println("Pause for a very long time before running this code again.");
        throw new Exception();
    }
});

new Waiter(new Options().willWaitForTrue(true)).wait(new Until<Boolean>() {
    public Boolean success() throws Throwable {
        System.out.println("Run this code forever.");
        return false;
    }
});

new Waiter(new Options().willWaitForNotNull(true)).wait(new Until<String>() {
    public String success() throws Throwable {
        return "Only run this once and return this string.";
    }
});

class BecauseResult implements ResultValidator<String> {
    public boolean isValid(String result) throws Throwable {
        return result.toLowerCase().contains("because");
    }
}

new Waiter(new Options().waitFor(new BecauseResult())).wait(new Until<String>() {
    private final List<String> sentences = new ArrayList<String>() {{
       add("This will run twice.");
       add("Because it need to return this sentence.");
    }};

    public String success() throws Throwable {
        final String sentence = sentences.remove(0);
        System.out.println(sentence);
        return sentence;
    }
});

new Waiter(new Options().include(IllegalArgumentException.class)).wait(new Until<Void>() {
    public Void success() throws Throwable {
        System.out.println("This will run once and explode.");
        throw new Exception();
    }
});

new Waiter(new Options().include(IllegalArgumentException.class)).wait(new Until<Void>() {
    private Exception exception;

    public Void success() throws Throwable {
        System.out.println("Where as this will run twice and succeed.");
        if (exception == null) {
            throw (exception = new IllegalArgumentException());
        }
        return null;
    }
});

new Waiter(new Options().exclude(IllegalArgumentException.class)).wait(new Until<Void>() {
    public Void success() throws Throwable {
        System.out.println("This will also run once and explode.");
        throw new IllegalArgumentException();
    }
});

new Waiter(new Options().exclude(IllegalArgumentException.class)).wait(new Until<Void>() {
    private Exception exception;

    public Void success() throws Throwable {
        System.out.println("And this will also run twice and succeed.");
        if (exception == null) {
            throw (exception = new Exception());
        }
        return null;
    }
});

final Options options = new Options()
    .withTimeout(10L, TimeUnit.MINUTES)
    .withInterval(1L, TimeUnit.MILLISECONDS)
    .willWaitForTrue(false)
    .willWaitForNotNull(true)
    .waitFor(new BecauseResult())
    .include(IllegalStateException.class)
    .exclude(IllegalArgumentException.class);
new Waiter(options).wait(new Until<String>() {
    private final List<String> sentences = new ArrayList<String>() {{
        add("Also all of these options can be combined.");
        add("Just because.");
    }};
    private Exception exception;

    public String success() throws Throwable {
        final String sentence = sentences.remove(0);
        System.out.println(sentence);
        if (exception == null) {
            throw (exception = new IllegalStateException());
        }
        return sentence;
    }
});

new Waiter(new Options().withDefaults(true)).wait(new Until<Void>() {
    public Void success() throws Throwable {
        System.out.println("This will ignore any currently set global properties.");
        return null;
    }
});
```
#### Properties

You can apply global configuration for all instantiated `Waiter`s with the following Java properties.
* `smt.waiting.timeout.duration`, `smt.waiting.timeout.unit`: Set the global timeout.
  * Example:
    <pre>
    smt.waiting.timeout.duration=1
    smt.waiting.timeout.unit=SECONDS # java.util.concurrent.TimeUnit name.
    </pre>
* `smt.waiting.interval.duration`, `smt.waiting.interval.unit`: Set the global interval.
  * Example:
    <pre>
    smt.waiting.interval.duration=100
    smt.waiting.interval.unit=MILLISECONDS # java.util.concurrent.TimeUnit name.
    </pre>
* `smt.waiting.waitForTrue`: Set if all `Waiter`s should wait for
[`Until#success()`](src/main/java/shiver/me/timbers/waiting/Until.java#L30) to return `true`.
  * Example: `smt.waiting.waitForTrue=true`
* `smt.waiting.waitForNotNull`: Set if all `Waiter`s should wait for `Until#success()` to return a nonnull value.
  * Example: `smt.waiting.waitForNotNull=true`
* `smt.waiting.waitFor`: Set any [`ResultValidator`s](src/main/java/shiver/me/timbers/waiting/ResultValidator.java) that
should be automatically applied to all `Waiter`s.
  * Example: `smt.waiting.waitFor=org.example.ExampleResultOne,org.example.ExampleResultTwo`
* `smt.waiting.include`: Set a global include exceptions list.
  * Example: `smt.waiting.include=org.example.ExampleOneException,org.example.ExampleTwoError`
* `smt.waiting.exclude`: Set a global exclude exceptions list.
  * Example: `smt.waiting.exclude=org.example.ExampleOneException,org.example.ExampleTwoError`

## License

smt-waiting is released under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
