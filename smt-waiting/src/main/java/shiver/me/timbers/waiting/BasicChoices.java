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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
class BasicChoices implements Choices {

    private final Long timeoutDuration;
    private final TimeUnit timeoutUnit;
    private final Long intervalDuration;
    private final TimeUnit intervalUnit;
    private final Boolean waitForTrue;
    private final Boolean waitForNotNull;
    private final List<ResultValidator> resultValidators;
    private final Set<Class<? extends Throwable>> includes;
    private final Set<Class<? extends Throwable>> excludes;

    BasicChoices(
        Long timeoutDuration,
        TimeUnit timeoutUnit,
        Long intervalDuration,
        TimeUnit intervalUnit,
        Boolean waitForTrue,
        Boolean waitForNotNull,
        List<ResultValidator> resultValidators,
        Set<Class<? extends Throwable>> includes,
        Set<Class<? extends Throwable>> excludes) {
        this.timeoutDuration = timeoutDuration;
        this.timeoutUnit = timeoutUnit;
        this.intervalDuration = intervalDuration;
        this.intervalUnit = intervalUnit;
        this.waitForTrue = waitForTrue;
        this.waitForNotNull = waitForNotNull;
        this.resultValidators = resultValidators;
        this.includes = includes;
        this.excludes = excludes;
    }

    @Override
    public Long getTimeoutDuration() {
        return timeoutDuration;
    }

    @Override
    public TimeUnit getTimeoutUnit() {
        return timeoutUnit;
    }

    @Override
    public Long getIntervalDuration() {
        return intervalDuration;
    }

    @Override
    public TimeUnit getIntervalUnit() {
        return intervalUnit;
    }

    @Override
    public Boolean isWaitForTrue() {
        return waitForTrue;
    }

    @Override
    public Boolean isWaitForNotNull() {
        return waitForNotNull;
    }

    @Override
    public List<ResultValidator> getResultValidators() {
        return new ArrayList<>(resultValidators);
    }

    @Override
    public Set<Class<? extends Throwable>> getIncludes() {
        return new HashSet<>(includes);
    }

    @Override
    public Set<Class<? extends Throwable>> getExcludes() {
        return new HashSet<>(excludes);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final BasicChoices that = (BasicChoices) object;

        if (timeoutDuration != null ? !timeoutDuration.equals(that.timeoutDuration) : that.timeoutDuration != null) {
            return false;
        }
        if (timeoutUnit != that.timeoutUnit) {
            return false;
        }
        if (intervalDuration != null ? !intervalDuration.equals(that.intervalDuration) : that.intervalDuration != null) {
            return false;
        }
        if (intervalUnit != that.intervalUnit) {
            return false;
        }
        if (waitForTrue != null ? !waitForTrue.equals(that.waitForTrue) : that.waitForTrue != null) {
            return false;
        }
        if (waitForNotNull != null ? !waitForNotNull.equals(that.waitForNotNull) : that.waitForNotNull != null) {
            return false;
        }
        if (resultValidators != null ? !resultValidators.equals(that.resultValidators) : that.resultValidators != null) {
            return false;
        }
        if (includes != null ? !includes.equals(that.includes) : that.includes != null) {
            return false;
        }
        return !(excludes != null ? !excludes.equals(that.excludes) : that.excludes != null);

    }

    @Override
    public int hashCode() {
        int result = timeoutDuration != null ? timeoutDuration.hashCode() : 0;
        result = 31 * result + (timeoutUnit != null ? timeoutUnit.hashCode() : 0);
        result = 31 * result + (intervalDuration != null ? intervalDuration.hashCode() : 0);
        result = 31 * result + (intervalUnit != null ? intervalUnit.hashCode() : 0);
        result = 31 * result + (waitForTrue != null ? waitForTrue.hashCode() : 0);
        result = 31 * result + (waitForNotNull != null ? waitForNotNull.hashCode() : 0);
        result = 31 * result + (resultValidators != null ? resultValidators.hashCode() : 0);
        result = 31 * result + (includes != null ? includes.hashCode() : 0);
        result = 31 * result + (excludes != null ? excludes.hashCode() : 0);
        return result;
    }
}
