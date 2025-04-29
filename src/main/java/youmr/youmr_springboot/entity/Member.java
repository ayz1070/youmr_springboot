package youmr.youmr_springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import youmr.youmr_springboot.entity.type.MemberStatus;
import youmr.youmr_springboot.entity.type.Provider;
import youmr.youmr_springboot.entity.type.Role;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AutoIncrement 적용
    private Long id;  // UUID → Long 타입으로 변경

    @Column(unique = true, nullable = true)
    private String socialId;  // 소셜 로그인 식별자 (소셜 사용자만)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;  // 소셜 로그인 제공자 (KAKAO, GOOGLE, APPLE, EMAIL)

    @Column(nullable = false, unique = true, length = 20)
    private String name;  // 이름

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;  // 닉네임 (유니크 설정)

    @Column(nullable = false)
    private String profileImageUrl;  // 기본 프로필 이미지 제공

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status = MemberStatus.ACTIVE;  // 계정 상태

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;  // 탈퇴 시 설정됨
}
