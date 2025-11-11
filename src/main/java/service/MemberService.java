package org.example.member_book.service;

import org.example.member_book.model.Member;
import org.example.member_book.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repo;

    public MemberService(MemberRepository repo) { this.repo = repo; }

    public List<Member> findAll() { return repo.findAll(); }
    public Member findById(Long id) { return repo.findById(id).orElse(null); }
    public Member save(Member m) { return repo.save(m); }
    public void delete(Long id) { repo.deleteById(id); }
}
