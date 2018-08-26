package co.metro;

import co.metro.model.Role;
import co.metro.model.RoleName;
import co.metro.repository.RoleRepository;
import co.metro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        MetroApplication.class,
        Jsr310JpaConverters.class
})
public class MetroApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MetroApplication.class, args);
    }

    @Bean
    public CommandLineRunner initial(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            roleRepository.save(new Role(RoleName.ROLE_USER));
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        };
    }

}
