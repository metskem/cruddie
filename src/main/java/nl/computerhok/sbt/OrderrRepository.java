package nl.computerhok.sbt;

import nl.computerhok.sbt.model.Orderr;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderrRepository extends CrudRepository<Orderr, Long> {

    List<Orderr> findByArticle(String article);
}

