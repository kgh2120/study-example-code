package org.kk.cachesync.repository;

import org.kk.cachesync.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
