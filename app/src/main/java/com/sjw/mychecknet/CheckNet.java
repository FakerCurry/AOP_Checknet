package com.sjw.mychecknet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by pc on 2018/6/30.
 */

//Target代表放在哪个位置
// ①ElementType.FIELD放在属性上面
// ②ElementType.METHOD放在方法上面
// ③ElementType.TYPE放在类上面
@Target(ElementType.METHOD)
//①RetentionPolicy.RUNTIME 运行时
//②RetentionPolicy.CLASS 编译时
//③RetentionPolicy.SOURCE 资源
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNet { //@interface 代表注解

}
