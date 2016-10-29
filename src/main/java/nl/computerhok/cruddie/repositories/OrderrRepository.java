package nl.computerhok.cruddie.repositories;

import nl.computerhok.cruddie.entity.Orderr;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderrRepository extends PagingAndSortingRepository<Orderr, Long> {

    List<Orderr> findByArticle(String article);
}

