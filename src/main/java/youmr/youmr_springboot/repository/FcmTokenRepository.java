package youmr.youmr_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youmr.youmr_springboot.entity.FcmToken;

import java.util.List;
import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {

    Optional<FcmToken> findByMemberId(Long memberId);

    Optional<FcmToken> findByMemberIdAndIsActiveTrue(Long memberId);

    List<FcmToken> findAllByIsActiveTrue();

    boolean existsByMemberIdAndToken(Long memberId, String token);

    List<FcmToken> findAllByMemberIdInAndIsActiveTrue(List<String> memberIds);

}