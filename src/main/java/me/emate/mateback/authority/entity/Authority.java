package me.emate.mateback.authority.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authority")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {
    @Id
    @Column(name = "authority_no", nullable = false)
    private Integer authorityNo;

    @Column(name = "authority_name", nullable = false)
    private String authorityName;
}
