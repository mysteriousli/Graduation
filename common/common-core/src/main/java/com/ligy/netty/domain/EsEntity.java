package com.ligy.netty.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
/**
 * @Author lgy
 */
@Data
public class EsEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String msg;
}
