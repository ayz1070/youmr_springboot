package youmr.youmr_springboot.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import youmr.youmr_springboot.entity.Member;
import youmr.youmr_springboot.entity.type.MemberStatus;
import youmr.youmr_springboot.entity.type.Provider;
import youmr.youmr_springboot.entity.type.Role;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    private String socialId;

    @NotNull
    private Provider provider;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String nickname;

    private String profileImageUrl;

    public Member toEntity() {
        return Member.builder()
                .socialId(this.socialId)
                .provider(this.provider)
                .name(this.name)
                .nickname(this.nickname)
                .profileImageUrl(
                        (this.profileImageUrl != null && !this.profileImageUrl.isBlank())
                                ? this.profileImageUrl
                                : "/images/default_profile.png"
                )
                .role(Role.MEMBER) // 기본값 지정
                .status(MemberStatus.ACTIVE)
                .build();
    }
}