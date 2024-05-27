package puk.groupware;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@Slf4j
public class TestController {

    @GetMapping("/")
    public String getMethodName() {
        return "index";
    }

    @GetMapping("/signUpform")
    public String singUp(){
        log.info("signUp");
        return "signupform";
    }

    @GetMapping("/projectreg")
    public String projectreg() {
        return "projectregform";
    }
    
    
}
