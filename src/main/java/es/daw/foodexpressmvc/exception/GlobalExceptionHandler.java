package es.daw.foodexpressmvc.exception;

import es.daw.foodexpressmvc.dto.UserRegisterDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConnectionApiRestException.class)
    public String handleConnectApiRestException(ConnectionApiRestException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "api-error";
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String handleUsernameExists(UsernameAlreadyExistsException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("userRegisterDTO", new UserRegisterDTO()); // borra los campos
        // return "register?error" .....
        return "register";
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public String handlePasswordsError(PasswordsDoNotMatchException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }
}
