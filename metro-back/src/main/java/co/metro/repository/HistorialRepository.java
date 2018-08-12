

package co.metro.repository;

import co.metro.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HistorialRepository extends JpaRepository<Historial, Long> {

}
