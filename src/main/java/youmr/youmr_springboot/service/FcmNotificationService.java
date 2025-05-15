package youmr.youmr_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youmr.youmr_springboot.entity.FcmNotification;
import youmr.youmr_springboot.repository.FcmNotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmNotificationService {

    private final FcmNotificationRepository fcmNotificationRepository;

    /**
     * 알림 저장
     */
    @Transactional
    public FcmNotification saveNotification(FcmNotification notification) {
        return fcmNotificationRepository.save(notification);
    }

    /**
     * 전체 알림 조회 (관리자용)
     */
    @Transactional(readOnly = true)
    public List<FcmNotification> findAll() {
        return fcmNotificationRepository.findAll();
    }

    /**
     * 미발송 알림 조회 (예: 예약 발송용)
     */
    @Transactional(readOnly = true)
    public List<FcmNotification> findUnsentNotifications() {
        return fcmNotificationRepository.findAllByIsSentFalse();
    }

    /**
     * 단일 알림 조회
     */
    @Transactional(readOnly = true)
    public FcmNotification findById(Long id) {
        return fcmNotificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다. id=" + id));
    }

    /**
     * 알림 삭제
     */
    @Transactional
    public void deleteById(Long id) {
        fcmNotificationRepository.deleteById(id);
    }

    /**
     * 발송 완료 처리
     */
    @Transactional
    public void markAsSent(FcmNotification notification) {
        notification.setSent(true);
        notification.setSentAt(java.time.LocalDateTime.now());
        fcmNotificationRepository.save(notification); // dirty checking or 직접 저장
    }
}