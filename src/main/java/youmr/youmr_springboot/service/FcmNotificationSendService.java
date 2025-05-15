package youmr.youmr_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youmr.youmr_springboot.common.fcm.FcmSender;
import youmr.youmr_springboot.entity.FcmToken;
import youmr.youmr_springboot.entity.Member;
import youmr.youmr_springboot.entity.type.MemberStatus;
import youmr.youmr_springboot.entity.type.WeekType;
import youmr.youmr_springboot.repository.FcmTokenRepository;
import youmr.youmr_springboot.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmNotificationSendService {

    private final MemberRepository memberRepository;
    private final FcmTokenRepository fcmTokenRepository;
    private final FcmSender fcmSender;

    /**
     * 특정 요일 대상자에게 FCM 전송
     */
    @Transactional(readOnly = true)
    public void sendToWeekTypeMembers(WeekType weekType, String title, String body) {
        List<Member> members = memberRepository.findAllByWeekTypeAndStatus(weekType, MemberStatus.ACTIVE);
        List<String> memberIds = members.stream().map(m -> m.getId().toString()).toList();

        List<FcmToken> tokens = fcmTokenRepository.findAllByMemberIdInAndIsActiveTrue(memberIds);
        List<String> tokenList = tokens.stream().map(FcmToken::getToken).toList();

        fcmSender.sendToMultipleTokens(tokenList, title, body);
    }

    /**
     * 전체 활성 사용자에게 FCM 전송
     */
    @Transactional(readOnly = true)
    public void sendToAllActiveMembers(String title, String body) {
        List<FcmToken> tokens = fcmTokenRepository.findAllByIsActiveTrue();
        List<String> tokenList = tokens.stream().map(FcmToken::getToken).toList();

        fcmSender.sendToMultipleTokens(tokenList, title, body);
    }
}