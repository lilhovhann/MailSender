package io.project.app.mailsender.repositories;

import io.project.app.mailsender.domain.MailSenderModel;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armena
 */
@Transactional
@Repository
public interface MailSenderRepository extends MongoRepository<MailSenderModel, String> {

    Optional<MailSenderModel> findByEmailAndPassword(String email, String password);

    Optional<MailSenderModel> findByEmail(String email);

}
