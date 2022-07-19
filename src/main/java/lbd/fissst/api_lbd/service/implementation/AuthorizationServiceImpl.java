package lbd.fissst.api_lbd.service.implementation;

import lbd.fissst.api_lbd.service.definition.AuthorizationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    public static final String STUDENT_CONTROLLER_URL = "/api/student";
    public static final String TEACHER_CONTROLLER_URL = "/api/teacher";

    @Override
    public boolean isAuthorized(String URI, String roleHeaderValue) {
        if(URI != null && roleHeaderValue != null){
            if(URI.startsWith(STUDENT_CONTROLLER_URL)){
                return roleHeaderValue.equals("TEACHER_ROLE") || roleHeaderValue.equals("STUDENT_ROLE");

            }else if(URI.startsWith(TEACHER_CONTROLLER_URL)){
                return roleHeaderValue.equals("TEACHER_ROLE");
            }
        }
        return false;
    }
}
