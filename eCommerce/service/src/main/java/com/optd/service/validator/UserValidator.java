package com.optd.service.validator;

import com.optd.common.dto.security.LoginDto;
import com.optd.common.exception.ErrorMessageUtil;
import com.optd.common.exception.MetaMessageUtil;
import com.optd.entity.Cart;
import com.optd.entity.User;
import com.optd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    private MetaMessageUtil metaMessageUtil = new MetaMessageUtil();

    public void checkUserName(LoginDto loginDto){
        metaMessageUtil = new MetaMessageUtil();
        List<User> userList = userRepository.retrieveUserList();
        String username = userRepository.retrieveUsernameByUsername(loginDto.getUsername());
        for(User user : userList){
            if(user.getUsername().equals(username))
                metaMessageUtil.addMetaMessageWarning("Kullanıcı adı sistemde kayıtlı.Başka bir kullanıcı adı deneyiniz");
        }
        ErrorMessageUtil.checkBusinessException(metaMessageUtil);
    }
}
