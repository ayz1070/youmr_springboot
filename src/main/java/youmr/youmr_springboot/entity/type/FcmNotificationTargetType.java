package youmr.youmr_springboot.entity.type;

public enum FcmNotificationTargetType {
    ALL,           // 전체 대상
    WEEK_TYPE,     // 요일별 사용자 (ex. MONDAY)
    MEMBER         // 특정 사용자 ID
}