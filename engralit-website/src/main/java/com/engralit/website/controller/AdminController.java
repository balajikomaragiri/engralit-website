package com.engralit.website.controller;

import com.engralit.website.entity.ChatMessage;
import com.engralit.website.entity.CourseEnquiry;
import com.engralit.website.repository.ChatMessageRepository;
import com.engralit.website.repository.CourseEnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CourseEnquiryRepository enquiryRepository;
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/enquiries")
    public String enquiries(@RequestParam(required = false) String search, Model model) {
        List<CourseEnquiry> enquiries;
        if (search != null && !search.isBlank()) {
            enquiries = enquiryRepository
                    .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(search, search, search);
        } else {
            enquiries = enquiryRepository.findAllByOrderByCreatedAtDesc();
        }
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("search", search);
        return "admin-enquiries";
    }

    @PostMapping("/enquiries/{id}/delete")
    public String deleteEnquiry(@PathVariable Long id) {
        enquiryRepository.deleteById(id);
        return "redirect:/admin/enquiries";
    }

    @PostMapping("/enquiries/{id}/contacted")
    public String markContacted(@PathVariable Long id) {
        CourseEnquiry enquiry = enquiryRepository.findById(id).orElseThrow();
        enquiry.setStatus("CONTACTED");
        enquiryRepository.save(enquiry);
        return "redirect:/admin/enquiries";
    }

    @GetMapping("/chat")
    public String chatInbox(Model model) {
        List<String> students = chatMessageRepository.findDistinctUserEmails();
        model.addAttribute("students", students);
        return "admin-chat";
    }

    @GetMapping("/chat/{email}")
    @ResponseBody
    public List<ChatMessage> getConversation(@PathVariable String email) {
        return chatMessageRepository.findByUserEmailOrderBySentAtAsc(email);
    }

    @PostMapping("/chat/reply")
    @ResponseBody
    public ChatMessage reply(@RequestBody Map<String, String> payload) {
        ChatMessage msg = new ChatMessage();
        msg.setUserEmail(payload.get("email"));
        msg.setMessage(payload.get("message"));
        msg.setSender("ADMIN");
        return chatMessageRepository.save(msg);
    }
}