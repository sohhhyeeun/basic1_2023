package com.ll.basic1.boundedContext.member.entity;

import jakarta.persistence.*;
import lombok.*;
//import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
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
