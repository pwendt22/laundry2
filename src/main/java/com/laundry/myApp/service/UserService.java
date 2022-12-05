import com.laundry.myApp.controllers.form.RegistrationFormDto;
import com.laundry.myApp.models.Role;
import com.laundry.myApp.models.User;
import com.laundry.myApp.repository.UserRepository;
import com.laundry.myApp.services.exception.UserNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserService {

    private UserRepository userRepository;
    
    User findUserByEmail(String email);

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
