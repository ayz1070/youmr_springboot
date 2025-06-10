package youmr.youmr_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import youmr.youmr_springboot.entity.Member;
import youmr.youmr_springboot.entity.type.MemberStatus;
import youmr.youmr_springboot.entity.type.WeekType;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsBySocialId(String socialId);

    // 특정 요일에 속한 활성 사용자
    List<Member> findAllByWeekTypeAndStatus(WeekType weekType, MemberStatus status);

    // 모든 활성 사용자
    List<Member> findAllByStatus(MemberStatus status);

    Optional<Member> findBySocialId(String socialId);
}