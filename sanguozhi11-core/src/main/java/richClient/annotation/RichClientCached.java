package richClient.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sikou
 * @date 2021/08/05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RichClientCached {
    /**
     * 是否需要被缓存,默认为true
     */
    boolean clientCache() default true;

    /**
     * 服务端是否需要被缓存,默认为true
     */
    boolean severCache() default true;

    /**
     * tair的缓存namespace
     */
    int namespace();

    /**
     * 缓存前缀
     */
    String cachePrefix();

    /**
     * tair的失效时间
     */
    int expireSecond();

    /**
     * 从缓存中读取到NullObjec对象是否直接返回，不穿透到后端服务
     */
    boolean fastReturnNullObject() default false;
}
