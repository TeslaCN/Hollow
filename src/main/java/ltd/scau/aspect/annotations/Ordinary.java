package ltd.scau.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此 Annotation 作为 Aspect 的一个切入点，用于标记需要登录并且要求 UserLevel 至少为 1(UserLevel.ORDINARY) 的 Action。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ordinary {
}
