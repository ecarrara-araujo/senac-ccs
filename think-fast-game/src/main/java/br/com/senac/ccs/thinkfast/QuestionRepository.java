package br.com.senac.ccs.thinkfast;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ecarrara
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
