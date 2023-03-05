package br.com.cwi.cwitter.factories;

import br.com.cwi.cwitter.security.domain.User;

public class UserFactory {

    public static User get() {
        User user = new User();
        user.setId(SimpleFactory.getRandomLong());
        user.setFullName("Sr Teste");
        user.setEmail("teste@cwi.com.br");
        return user;
    }
}
