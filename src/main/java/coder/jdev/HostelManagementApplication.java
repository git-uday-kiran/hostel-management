package coder.jdev;

import coder.jdev.repositories.hostel.HostelRepository;
import coder.jdev.repositories.identity.CityRepository;
import coder.jdev.services.hostel.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
public class HostelManagementApplication {

    public final DataSource dataSource;
    public final HostelRepository hostelRepository;
    public final CityRepository cityRepository;
    public final RoomService roomService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HostelManagementApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void loadSqlFiles() {
        System.out.println("Loading SQL files.....");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        final String path = "/db/";
        Resource college = new ClassPathResource(path + "college.sql");
        Resource world = new ClassPathResource(path + "world.sql");

        populator.addScript(college);
        populator.addScript(world);
        populator.execute(dataSource);
        System.out.println("SQL files loaded");
    }

}




