package lv.ctco.cukesrest.internal.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.math.BigDecimal;

public class EqualToIgnoringTypeMatcher {

    public static Matcher<String> equalToIgnoringType(final String value) {
        return new BaseMatcher<String>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("equal to ignoring type " + value);
            }

            @Override
            public boolean matches(Object item) {
                String toString = item.toString();
                if (toString.equals(value)) return true;

                if (item instanceof Number) {
                    BigDecimal observedValue = item instanceof BigDecimal ?
                            (BigDecimal) item : new BigDecimal(toString);
                    return new BigDecimal(value).compareTo(observedValue) == 0;
                }

                return false;
            }
        };
    }
}
