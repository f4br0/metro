// tag::sample[]
package co.metro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String origen;
    private String destino;


    public Historial(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return origen + "/" + destino;
    }


}

