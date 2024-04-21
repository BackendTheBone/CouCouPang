package p.khj745700.coucoupang.application.aop.annotation;

import p.khj745700.coucoupang.application.domain.member.MemberType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuth {
    MemberType value();
}