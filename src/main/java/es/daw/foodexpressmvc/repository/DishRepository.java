package es.daw.foodexpressmvc.repository;


import es.daw.foodexpressmvc.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
