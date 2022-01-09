package by.asalalaiko.service;

import by.asalalaiko.domain.Plane;
import by.asalalaiko.domain.User;
import by.asalalaiko.domain.UsersRole;
import by.asalalaiko.exception.UserNotFoundForLoginException;
import by.asalalaiko.repo.PlaneRepo;
import by.asalalaiko.repo.UserRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

public class UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static UserService instance;


    private UserService() {

        instance = this;
    }

    public static UserService getInstance() {
        if (instance == null) {
            UserService.instance = new UserService();
        }
        return instance;
    }

    public static Collection<User> findAll() {return UserRepo.getInstance().findAll();
    }

    public static void lockUnlockById(Long id) {
        User user = new User();
        user = UserRepo.getInstance().getById(id);
        user.setLocked(!user.getLocked());
        UserRepo.getInstance().save(user);
    }

    public User findByLogin(String login){
        return UserRepo.getInstance().findByLogin(login);
    }

    public User registerUser(String login, String password, String firstName, String lastName,
                             String email) {

        String hashPassword = hashPassword(password);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        User user = new User();

        user.setLogin(login);
        user.setPassword(hashPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCreated(timestamp.toLocalDateTime());
        user.setLocked(Boolean.FALSE); // FALSE - USER NOT LOCKED
        user.setRole(UsersRole.USER);
        user.setActive(Boolean.FALSE); // FALSE - USER NOT ACTIVATED

        User save = UserRepo.getInstance().save(user);

//        try {
//           // EmailService.getInstance().notifyUserRegistered(email, save.getId());
//        } catch (MessagingException e) {
//            LOGGER.error("MEssage sending failed", e);
//        }
        return save;
    }


    public User login(String login, String password) {
        User findByLogin = UserRepo.getInstance().findByLogin(login);
        if (findByLogin != null) {
            String hashInDB = findByLogin.getPassword();
            String hashPassword = hashPassword(password);
            if (hashInDB.equals(hashPassword)) {
                return findByLogin;
            }
        }
        throw new UserNotFoundForLoginException(login);
    }

    private String hashPassword(String password) {
        return DigestUtils.md5Hex(password).toUpperCase();
    }

}
