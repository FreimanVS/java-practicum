package com.freimanvs.javapracticum;

import com.freimanvs.javapracticum.dto.AuthorizationProfile;
import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.mappings.FinanceTrackerMapping;
import com.freimanvs.javapracticum.repositories.UserCrudRepository;
import com.freimanvs.javapracticum.repositories.impls.UserRepository;
import com.freimanvs.javapracticum.services.impl.ProfileAuthorizationService;
import com.freimanvs.javapracticum.services.impl.UserService;

public class Main {

    private final UserCrudRepository<User, Long> userRepository = new UserRepository();
    private final UserService userService = new UserService(userRepository);
    private final FinanceTrackerMapping financeTrackerMapping = new FinanceTrackerMapping();
    private final ProfileAuthorizationService authorizationService = new ProfileAuthorizationService(userService, financeTrackerMapping);
    public static void main(String[] args) {
        new Main().main();
    }

    private void main() {

        AuthorizationProfile profile = new AuthorizationProfile()
                .setName("Vasiliy")
                .setEmail("vasiliy@mail.ru")
                .setPassword("vasiliy1234");

        authorizationService.register(profile);

        User authorizedUser = authorizationService.authorize(profile);
        System.out.println(authorizedUser + " is authorized!");
    }
}
