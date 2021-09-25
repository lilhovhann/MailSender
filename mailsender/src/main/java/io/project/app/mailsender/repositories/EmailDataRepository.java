package io.project.app.mailsender.repositories;

import io.project.app.mailsender.domain.EmailData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lilith
 */
@Transactional
@Repository
public interface EmailDataRepository extends MongoRepository<EmailData, String> {

}
