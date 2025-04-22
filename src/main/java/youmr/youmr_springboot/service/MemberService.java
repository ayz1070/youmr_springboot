package youmr.youmr_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youmr.youmr_springboot.dto.member.MemberResponse;
import youmr.youmr_springboot.dto.member.SignUpRequest;
import youmr.youmr_springboot.entity.Member;
import youmr.youmr_springboot.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * ID로 회원 조회
     */
    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id=" + id));
        return MemberResponse.fromEntity(member);
    }

    /**
     * 회원 생성 (소셜 회원가입)
     */
    @Transactional
    public MemberResponse create(SignUpRequest request) {
        if (memberRepository.existsBySocialId(request.getSocialId())) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        Member savedMember = memberRepository.save(request.toEntity());
        return MemberResponse.fromEntity(savedMember);
    }
}