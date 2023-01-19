package com.nhnacademy.sessionproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Data {

    @Id
    @Column(name = "`key`")
    private String key;

    @Column(name = "`field`")
    private String field;
    @Column(name = "`value`")
    private String value;

    @Builder
    public Data(String key,String field, String value) {
        this.key = key;
        this.field = field;
        this.value = value;
    }
}
