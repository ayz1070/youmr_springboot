package youmr.youmr_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youmr.youmr_springboot.dto.fcm.FcmTokenResponse;
import youmr.youmr_springboot.dto.fcm.RefreshFcmTokenRequest;
import youmr.youmr_springboot.entity.FcmToken;
import youmr.youmr_springboot.repository.FcmTokenRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;

    /// create + update
    // 토큰 저장 또는 갱신
    @Transactional
    public FcmTokenResponse saveOrUpdateToken(RefreshFcmTokenRequest request) {
        return fcmTokenRepository.findByMemberId(request.getMemberId())
                .map(existing -> {
                    existing.refreshToken(request.getToken(), request.getDeviceType());
                    return FcmTokenResponse.fromEntity(existing);
                })
                .orElseGet(() -> {
                    FcmToken newToken = FcmToken.builder()
                            .memberId(request.getMemberId())
                            .token(request.getToken())
                            .deviceType(request.getDeviceType())
                            .isActive(true)
                            .build();
                    return FcmTokenResponse.fromEntity(fcmTokenRepository.save(newToken));
                });
    }

    /// read
    // 특정 사용자의 모든 토큰 조회
    @Transactional(readOnly = true)
    public Optional<FcmTokenResponse> findTokenByMemberId(Long memberId) {
        return fcmTokenRepository.findByMemberId(memberId)
                .map(FcmTokenResponse::fromEntity);
    }

    // 전체 활성 토큰 목록 조회 (예: 전체 푸시 대상자)
    @Transactional(readOnly = true)
    public List<FcmTokenResponse> findAllActiveTokens() {
        return fcmTokenRepository.findAllByIsActiveTrue().stream()
                .map(FcmTokenResponse::fromEntity)
                .toList();
    }

    /// etc
    // 로그아웃/탈퇴 시 비활성화
    @Transactional
    public void deactivateTokenByMemberId(Long memberId) {
        fcmTokenRepository.findByMemberIdAndIsActiveTrue(memberId)
                .ifPresent(FcmToken::deactivate);
    }


}