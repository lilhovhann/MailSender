package io.project.app.mailsender.resources;

import io.micrometer.core.annotation.Timed;
import io.project.app.mailsender.services.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import io.project.app.mailsender.domain.EmailData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import io.project.app.mailsender.dto.EmailDataDTO;

/**
 *
 * @author lilith
 */
@RestController
@RequestMapping("/api/v2/accounts")
@Slf4j
public class MailSenderController {

    @Autowired
    public MailSenderService mailSenderService;
    



    @PostMapping(path = "/contact")
    @CrossOrigin
    @Timed
    @ApiOperation(value = "Get messages", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Messages Fetch successfully"),
        @ApiResponse(code = 400, message = "Could not find any message")
    }
    )

    public ResponseEntity<?> contact(@RequestBody EmailDataDTO emailDataDTO) {

        Optional<EmailData> emailData = mailSenderService.contactHicare(emailDataDTO);

        if (emailData.isPresent()) {
            log.info("Data sent");
            return ResponseEntity.ok().body(emailData.get());
        }

        return ResponseEntity.badRequest().body(emailData.get());
    }

}
