package shiver.me.timbers.waiting;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.Field;

import static org.hamcrest.Matchers.equalTo;

class HasFieldMatcher<T> extends TypeSafeMatcher<T> {

    private Object actual;

    static <T> Matcher<T> hasField(String name, Object expected) {
        return new HasFieldMatcher<>(name, equalTo(expected));
    }

    static <T> Matcher<T> hasField(String name, Matcher matcher) {
        return new HasFieldMatcher<>(name, matcher);
    }

    private final String name;
    private final Matcher matcher;
    private SelfDescribing describer;

    public HasFieldMatcher(String name, Matcher matcher) {
        this.name = name;
        this.matcher = matcher;
        this.describer = new WrongValue();
    }

    @Override
    protected boolean matchesSafely(T object) {
        try {
            final Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            actual = field.get(object);
            return matcher.matches(actual);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            describer = new NoField();
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        describer.describeTo(description);
    }

    private class WrongValue implements SelfDescribing {
        @Override
        public void describeTo(Description description) {
            description.appendText("a field with name: ").appendValue(name)
                .appendText(" and value that ").appendDescriptionOf(matcher)
                .appendText(" but value was ").appendValue(actual);
        }
    }

    private class NoField implements SelfDescribing {
        @Override
        public void describeTo(Description description) {
            description.appendText("a field to exist with name: ").appendValue(name);
        }
    }
}
