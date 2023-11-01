package me.emate.mateback.authority.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Authority entity.
 *
 * @author 여운석
 */
@Entity
@Table(name = "authority")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_no", nullable = false)
    private Integer authorityNo;

    @Column(name = "authority_name", nullable = false)
    private String authorityName;
}
