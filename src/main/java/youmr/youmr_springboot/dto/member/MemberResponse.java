package youmr.youmr_springboot.dto.member;

import lombok.Builder;
import lombok.Getter;
import youmr.youmr_springboot.entity.Member;
import youmr.youmr_springboot.entity.type.MemberStatus;
import youmr.youmr_springboot.entity.type.Provider;
import youmr.youmr_springboot.entity.type.Role;
import youmr.youmr_springboot.entity.type.WeekType;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberResponse {
    private Long id;
    private String socialId;
    private Provider provider;
    private String name;
    private String nickname;
    private WeekType weekType;
    private String profileImageUrl;
    private Role role;
    private MemberStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .socialId(member.getSocialId())
                .provider(member.getProvider())
                .name(member.getName())
                .nickname(member.getNickname())
                .weekType(member.getWeekType())
                .profileImageUrl(member.getProfileImageUrl())
                .role(member.getRole())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}