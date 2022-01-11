package by.asalalaiko.service;

import by.asalalaiko.domain.User;
import by.asalalaiko.domain.UsersRole;
import by.asalalaiko.exception.UserNotFoundForLoginException;
import by.asalalaiko.repo.UserRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Random;

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

    public User activateUser(String code){
        User user =  UserRepo.getInstance().findByCode(code);
         if (user != null){
             user.setActive(Boolean.TRUE);
             UserRepo.getInstance().save(user);
         }
        return user;
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
        user.setActionCode(generateActionCode());

        User save = UserRepo.getInstance().save(user);

        try {
            EmailService.getInstance().notifyUserRegistered(email, save.getId(), save.getActionCode());
        } catch (MessagingException e) {
            LOGGER.error("Message sending failed", e);
        }
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

    private String generateActionCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
