package com.org.example.springboot.domain;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dataNo;

    private String name;
    private String mail;
    private Integer age;
    private String memo;

    @Builder
    public MyData(String name, String mail, Integer age, String memo){
        this.name=name;
        this.mail=mail;
        this.age=age;
        this.memo=memo;
    }

}
