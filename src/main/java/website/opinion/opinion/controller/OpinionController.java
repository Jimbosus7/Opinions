package website.opinion.opinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import website.opinion.opinion.dao.OpinionDao;
import website.opinion.opinion.model.Opinion;
import website.opinion.opinion.model.User;
import website.opinion.opinion.security.SecurityUtil;

@Controller
public class OpinionController {
    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    OpinionDao opinionDao;

    @GetMapping("/")
    public String showHome(Model model)
    {
        model.addAttribute("search", null);
        model.addAttribute("opinions", opinionDao.findAll());
        return "index";
    }

    @GetMapping("/my-opinions")
    public String showMyOpinions(Model model)
    {
        //get logged in user
        User user = securityUtil.getLoggedInUser();

        model.addAttribute("opinions", opinionDao.findAllByUser(user));
        return "/my-opinions";
    }

    @GetMapping("/add-opinion")
    public String showAddOpinion(Model model)
    {
        model.addAttribute("opinion", new Opinion());
        return "add-edit-opinion";
    }

    @GetMapping("/editOpinion/{opinionId}")
    public String showEditOpinion(Model model, @PathVariable("opinionId") Long opinionId)
    {
        model.addAttribute("opinion", opinionDao.findById(opinionId).get());
        return "add-edit-opinion";
    }

    @GetMapping("/deleteOpinion/{opinionId}")
    public String deleteOpinion(Model model, @PathVariable("opinionId") Long opinionId)
    {
        opinionDao.deleteById(opinionId);
        return "redirect:/my-opinions?deleted";
    }

    @PostMapping("add-edit-opinion")
    public String addUpdateOpinion(@ModelAttribute("opinion")Opinion opinion)
    {
        if (opinion.getId() != null && opinion.getId() != 0)
        {
            Opinion opinionToUpdate = opinionDao.findById(opinion.getId()).get();

            System.out.println(opinionToUpdate.toString());

            opinionToUpdate.setTitle(opinion.getTitle());
            opinionToUpdate.setTopic(opinion.getTopic());
            opinionToUpdate.setDetails(opinion.getDetails());

            opinionDao.save(opinionToUpdate);

            return "redirect:/my-opinions?updated";
        }

        //get logged in user
        User user = securityUtil.getLoggedInUser();

        opinion.setUser(user);

        opinionDao.save(opinion);
        return "redirect:/my-opinions?added";
    }

    @PostMapping("/search-opinion")
    public String searchOpinions(@RequestParam("search") String search, Model model)
    {
        model.addAttribute("opinions", opinionDao.findAllByTitleContainsOrTopicContainsOrDetailsContains(search));

        model.addAttribute("search", "Results for: " + search);
        return "index";
    }
}
