package lbd.fissst.api_lbd.service.definition;

public interface AuthorizationService {

    boolean isAuthorized(String URI, String roleHeaderValue);
}
