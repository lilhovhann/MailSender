package io.project.app.mailsender.resources;

import io.project.app.io.project.app.mailsender.services.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import io.project.app.mailsender.domain.MailSenderModel;
import org.springframework.http.MediaType;

/**
 *
 * @author root
 */
@RestController
@RequestMapping("/api/v2/accounts")
@Slf4j
public class MailSenderController {

    @Autowired
    private MailSenderService accountService;


    @PutMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    @Transactional
    public ResponseEntity<?> register(
            @RequestBody(required = true) MailSenderModel registerRequest
    ) throws Exception {

        Optional<MailSenderModel> doRegister = accountService.registerAccount(registerRequest);
        if (doRegister.isPresent()) {
          
            return ResponseEntity.status(HttpStatus.OK).body(doRegister.get());
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(("Could not register user"));
    }

}
