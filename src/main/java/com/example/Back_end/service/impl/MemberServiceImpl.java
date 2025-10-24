package com.example.Back_end.service.impl;

import com.example.Back_end.dto.MemberRequestDTO;
import com.example.Back_end.dto.MemberResponseDTO;
import com.example.Back_end.entity.Member;
import com.example.Back_end.repository.LabRepository;
import com.example.Back_end.repository.MemberRepository;
import com.example.Back_end.repository.UserRepository;
import com.example.Back_end.service.interf.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final LabRepository labRepository;

    @Override
    public MemberResponseDTO create(MemberRequestDTO dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var lab = labRepository.findById(dto.getLabId())
                .orElseThrow(() -> new RuntimeException("Lab not found"));

        Member member = new Member();
        member.setUser(user);
        member.setLab(lab);
        member.setMemberCode(dto.getMemberCode());
        member.setRole(dto.getRole());
        memberRepository.save(member);

        return toDto(member);
    }

    @Override
    public MemberResponseDTO getById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return toDto(member);
    }

    @Override
    public List<MemberResponseDTO> getAll() {
        return memberRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public MemberResponseDTO update(Long id, MemberRequestDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (dto.getLabId() != null) {
            var lab = labRepository.findById(dto.getLabId())
                    .orElseThrow(() -> new RuntimeException("Lab not found"));
            member.setLab(lab);
        }
        member.setMemberCode(dto.getMemberCode());
        member.setRole(dto.getRole());

        memberRepository.save(member);
        return toDto(member);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    private MemberResponseDTO toDto(Member member) {
        MemberResponseDTO dto = new MemberResponseDTO();
        dto.setMemberId(member.getMemberId());
        dto.setUserId(member.getUser().getUserId());
        dto.setLabId(member.getLab().getLabId());
        dto.setMemberCode(member.getMemberCode());
        dto.setRole(member.getRole());
        dto.setJoinedAt(member.getJoinedAt());
        return dto;
    }
}

