package org.example.pgnosqldemo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Ocean
 * @date 2023/9/8 15:50
 * @description YoutubeController
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/callback")
@Validated
public class CallbackController {


    private final String address = "461197429@qq.com";

    @PostMapping(value = "/test")
    public String test(@RequestBody String json) throws IOException {
        log.info(json);
        return json;
    }



}
