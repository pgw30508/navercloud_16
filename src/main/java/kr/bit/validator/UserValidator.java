package kr.bit.validator;

import kr.bit.beans.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//유효성 검사 customize
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user=(User) target;
        String beanName=errors.getObjectName();

        //join.jsp에서 폼에다 데이터 입력
        // -> modelAttribute로 User클래스의 필드에 데이터 값이 저장되어 있는 상태(값 주입한 상태)
        if(beanName.equals("joinBean") || beanName.equals("modifyBean")){
            if(!user.getUser_pw().equals(user.getUser_pw2())) {
                errors.rejectValue("user_pw", "NotEqual");
                errors.rejectValue("user_pw2", "NotEqual");
            }
            //비밀번호와 비밀번호 확인이 같아야됨.. 유효성 검사하는 어노테이션이 따로 없음
            //-> 개발자가 정의해야함(커스터마이징)
        }

        if(beanName.equals("joinBean")){
            if(!user.isExistId()) {
                errors.rejectValue("user_id", "NotCheck");
            }
        }

    }
}
