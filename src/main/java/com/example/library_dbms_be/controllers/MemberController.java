package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.dtos.MemberRequestDTO;
import com.example.library_dbms_be.dtos.MemberResponseDTO;
import com.example.library_dbms_be.mappers.MemberMapper;
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
    public MemberResponseDTO createMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        return memberService.createMember(memberRequestDTO);
    }

    // READ
    @GetMapping // ("/api/members")
    public List<MemberResponseDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}") // ("/api/members/{memberId}")
    public MemberResponseDTO getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    // GET http://localhost:8080/api/members/search?name=Jonny&email=jonny@email.com
    @GetMapping("/search") // ("/api/members/search")
    public MemberResponseDTO getMemberByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        return memberService.getMemberByNameAndEmail(name, email);
    }

    // UPDATE
    @PutMapping("/{memberId}") // ("/api/members/{memberId}")
    public MemberResponseDTO updateMemberById(@PathVariable Long memberId, @RequestBody MemberRequestDTO memberRequestDTO) {
        return memberService.updateMemberById(memberId, memberRequestDTO);
    }

    // DELETE
    @DeleteMapping("/{memberId}") // ("/api/members/{memberId}"
    public String deleteMemberById(@PathVariable Long memberId) {
        memberService.deleteMemberById(memberId);
        return String.format("Member deleted with ID: " + memberId);
    }
}
