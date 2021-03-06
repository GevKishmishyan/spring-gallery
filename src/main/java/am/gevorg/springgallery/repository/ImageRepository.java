package am.gevorg.springgallery.repository;

import am.gevorg.springgallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findAllByCategory_Id(int id);

}
