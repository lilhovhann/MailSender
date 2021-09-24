/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.app.io.project.app.mailsender.services;

import io.project.app.mailsender.domain.MailSenderModel;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.project.app.mailsender.dto.PersonDTO;
import io.project.app.mailsender.repositories.MailSenderRepository;

/**
 *
 * @author root
 */
@Service
@Slf4j
public class MailSenderService {

    @Autowired
    private MailSenderRepository mailSenderRepository;


    public Optional<MailSenderModel> registerAccount(MailSenderModel registerRequest) throws Exception {
        Optional<MailSenderModel> existingAccount = mailSenderRepository.findByEmail(registerRequest.getEmail());
        if (!existingAccount.isPresent()) {
          
            MailSenderModel save = mailSenderRepository.save(registerRequest);
           
            return Optional.ofNullable(save);
        }
        return Optional.empty();
    }


}
