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

    @GetMapping("/{id}") // ("/api/members/{id}")
    public Member getMemberById(Long memberId) {
        return memberService.getMemberById(memberId);
    }

    // UPDATE


    // DELETE
    @DeleteMapping("/{id}") // ("/api/members/{id}"
    public String deleteCustomerById(@PathVariable Long memberId) {
        return String.format("Member deleted with ID: " + memberId);
    }
}
