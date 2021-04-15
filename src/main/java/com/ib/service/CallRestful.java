package com.ib.service;

import com.ib.entity.EwalletLinked;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CallRestful {
    static final String URL_CREATE_EWALLET = "https://6076a3141ed0ae0017d69723.mockapi.io/momo/linked";

    public static void callAPI(EwalletLinked objE){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<EwalletLinked> requestBody = new HttpEntity<>(objE, headers);
        System.out.println(objE.getId());
        // Gửi yêu cầu với phương thức POST.
        EwalletLinked e = restTemplate.postForObject(URL_CREATE_EWALLET, requestBody, EwalletLinked.class);
        ResponseEntity<String> response = restTemplate.postForEntity(URL_CREATE_EWALLET, null, String.class);
        HttpStatus stt = response.getStatusCode();

        System.out.println(stt);
        String restCall = response.getBody();
        System.out.println(restCall);
    }
}
