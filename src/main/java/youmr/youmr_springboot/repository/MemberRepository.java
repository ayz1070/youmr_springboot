package youmr.youmr_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import youmr.youmr_springboot.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsBySocialId(String socialId);
}