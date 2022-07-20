package pl.km.exercise271;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/nowy")
    public String newEmployee(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/zapisz")
    public String saveEmployee(
            @ModelAttribute("user") User user,
            Model model) {
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("user", users);
        return "redirect:/";
    }
}
