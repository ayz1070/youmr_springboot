package youmr.youmr_springboot.controller.api.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import youmr.youmr_springboot.dto.member.MemberResponse;
import youmr.youmr_springboot.dto.member.SignUpRequest;
import youmr.youmr_springboot.service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberV1Controller {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PostMapping("/signup")
    public MemberResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return memberService.create(request);
    }
}