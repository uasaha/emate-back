package me.emate.mateback.authority.repository;

import me.emate.mateback.authority.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findAuthorityByAuthorityName(String authorityName);
}
