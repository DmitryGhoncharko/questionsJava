package by.ghoncharko.interview_training_platform.controller;

import by.ghoncharko.interview_training_platform.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestPageController {
    private final QuestionService questionService;

    @GetMapping("/{page}")
    public String testPage(@PathVariable("page") String page, Model model) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 1);
        model.addAttribute("questions", questionService.findAll(pageable));
        return "test";
    }
}
