package com.ll.basic1.boundedContext.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY) //Auto_Increment
    private long id;
    @CreatedDate //1
    private LocalDateTime createDate;
    @LastModifiedDate //1
    private LocalDateTime modifyDate;
    @Column(unique = true)
    private String username;
    private String password;
}
