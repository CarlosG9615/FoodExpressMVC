package es.daw.foodexpressmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ApiAuthService {


    private final WebClient webClientAuth;

    @Value("${api.auth-username}")
    private String apiUsername;

    @Value("${api.auth-password}")
    private String apiPassword;

    public String getToken() {
//        ApiLoginRequest request = new ApiLoginRequest();
//        request.setUsername(apiUsername);
//        request.setPassword(apiPassword);
//
//        try {
//            ApiLoginResponse response = webClientAuth
//                    .post()
//                    .uri("") // authURL ya es /auth/login
//                    .bodyValue(request)
//                    .retrieve()
//                    .bodyToMono(ApiLoginResponse.class)
//                    .block();
//
//            if (response == null || response.getToken() == null) {
//                throw new ConnectApiRestException("API login failed: no token received");
//            }
//
//            return response.getToken();
//        } catch (Exception ex) {
//            throw new ConnectApiRestException("Could not authenticate against FoodExpress API");
//        }
        return "";
    }
}
