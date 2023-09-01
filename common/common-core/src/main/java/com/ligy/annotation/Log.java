package com.ligy.annotation;

import com.ligy.core.enums.BusinessType;
import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.*;

import static com.ligy.core.Constants.OTHER;
import static com.ligy.core.enums.ExecutionStatusEnum.STOP;
import static java.awt.Event.DELETE;
import static java.awt.event.PaintEvent.UPDATE;
import static javax.swing.DropMode.INSERT;
import static jdk.nashorn.internal.parser.TokenType.EXPORT;

/**
 * @Author lgy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
public @interface Log {
    String value() default "";

    BusinessType businessType();
}
