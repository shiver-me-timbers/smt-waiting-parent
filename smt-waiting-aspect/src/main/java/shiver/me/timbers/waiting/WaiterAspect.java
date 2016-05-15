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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Use this aspect to add support for the {@link Wait} annotation at the class and method level
 *
 * @author Karl Bennett
 */
@Aspect
public class WaiterAspect {

    private final ServiceLoader<WaiterService, OptionsService> waiterLoader;
    private final ServiceLoader<OptionsService, Wait> optionsLoader;

    public WaiterAspect() {
        this(new WaiterServiceLoader(), new OptionsServiceLoader());
    }

    public WaiterAspect(
        ServiceLoader<WaiterService, OptionsService> waiterLoader,
        ServiceLoader<OptionsService, Wait> optionsLoader
    ) {
        this.waiterLoader = waiterLoader;
        this.optionsLoader = optionsLoader;
    }

    @Around("@within(wait) && isMethod()")
    public Object waitOnClass(ProceedingJoinPoint joinPoint, Wait wait) throws Throwable {
        if (methodHasWait(joinPoint)) {
            return joinPoint.proceed();
        }

        return wait(joinPoint, wait);
    }

    @Around("@annotation(wait) && isMethod()")
    public Object waitOnMethod(ProceedingJoinPoint joinPoint, Wait wait) throws Exception {
        return wait(joinPoint, wait);
    }

    @Pointcut("execution(* *(..))")
    private void isMethod() {
    }

    @SuppressWarnings("unchecked")
    private Object wait(final ProceedingJoinPoint joinPoint, Wait wait) throws Exception {
        return waiterLoader.load(optionsLoader.load(wait)).wait(new Until() {
            @Override
            public Object success() throws Throwable {
                return joinPoint.proceed();
            }

            @Override
            public String toString() {
                return joinPoint.toString();
            }
        });
    }

    private static boolean methodHasWait(ProceedingJoinPoint joinPoint) {
        final Signature signature = joinPoint.getSignature();
        final String methodName = signature.getName();

        for (Method method : signature.getDeclaringType().getDeclaredMethods()) {
            if (methodName.equals(method.getName())) {
                return method.isAnnotationPresent(Wait.class);
            }
        }

        return false;
    }
}
