package by.ShoppingList.SpringBootHTML.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Репозиторием называется класс который ответственен за сохранение в базу данных сущностей и за извлечение
//@repository указывает Spring Data Jpa что данный класс должен быть репозиторием
@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
}
