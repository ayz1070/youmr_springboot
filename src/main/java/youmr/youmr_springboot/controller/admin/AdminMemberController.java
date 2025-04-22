//package youmr.youmr_springboot.controller.admin;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import study.are_you_t_springboot.dto.member.MemberResponse;
//import study.are_you_t_springboot.dto.member.SocialSignUpRequest;
//import study.are_you_t_springboot.service.MemberService;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin/members")
//@RequiredArgsConstructor
//public class AdminMemberController {
//
//    private final MemberService memberService;
//
//    /** ✅ 회원 목록 페이지 */
//    @GetMapping
//    public String getAllMembers(Model model) {
//        List<MemberResponse> members = memberService.getAllMembers();
//        model.addAttribute("members", members);
//        return "admin/member-list"; // 📌 templates/admin/member-list.html 렌더링
//    }
//
//    /** ✅ 회원 추가 페이지 (폼) */
//    @GetMapping("/add")
//    public String showAddMemberForm(Model model) {
//        model.addAttribute("memberRequest", new SocialSignUpRequest());
//        return "admin/member-form"; // 📌 templates/admin/member-form.html 렌더링
//    }
//
//    /** ✅ 회원 등록 처리 */
//    @PostMapping("/add")
//    public String addMember(@ModelAttribute SocialSignUpRequest memberRequest) {
//        memberService.signUpWithSocial(memberRequest);
//        return "redirect:/admin/members"; // 📌 회원 추가 후 목록으로 이동
//    }
//
//    /** ✅ 회원 삭제 */
//    @PostMapping("/delete/{id}")
//    public String deleteMember(@PathVariable Long id) {
//        memberService.deleteMember(id);
//        return "redirect:/admin/members"; // 📌 삭제 후 목록 페이지로 이동
//    }
//}
//
