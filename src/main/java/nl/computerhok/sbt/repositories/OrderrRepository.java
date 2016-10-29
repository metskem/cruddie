package nl.computerhok.sbt.repositories;

import nl.computerhok.sbt.entity.Orderr;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderrRepository extends PagingAndSortingRepository<Orderr, Long> {

    List<Orderr> findByArticle(String article);
}

