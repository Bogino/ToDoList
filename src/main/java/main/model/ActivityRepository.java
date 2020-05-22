package main.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query("select a from Activity a where a.name like %?1%")
    List<Activity> findByNameContaining(String name);

    @Query(value = "SELECT * FROM Activity", nativeQuery = true)
    List<Activity> list();

}
