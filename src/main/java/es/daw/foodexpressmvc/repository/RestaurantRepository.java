package es.daw.foodexpressmvc.repository;

import es.daw.foodexpressmvc.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
