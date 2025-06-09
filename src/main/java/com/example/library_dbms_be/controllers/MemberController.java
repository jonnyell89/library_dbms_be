package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.models.Member;
import com.example.library_dbms_be.services.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // CREATE
    @PostMapping // ("/api/members")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    // READ
    @GetMapping // ("/api/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}") // ("/api/members/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    // UPDATE
    @PutMapping("/{memberId}") // ("/api/members/{memberId}")
    public Member updateMemberById(@PathVariable Long memberId, @RequestBody Member member) {
        return memberService.updateMemberById(memberId, member);
    }

    // DELETE
    @DeleteMapping("/{memberId}") // ("/api/members/{memberId}"
    public String deleteMemberById(@PathVariable Long memberId) {
        memberService.deleteMemberById(memberId);
        return String.format("Member deleted with ID: " + memberId);
    }
}
