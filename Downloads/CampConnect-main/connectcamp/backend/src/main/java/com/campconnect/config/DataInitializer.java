package com.campconnect.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.campconnect.enums.ERole;
import com.campconnect.enums.DifficultyLevel;
import com.campconnect.enums.EventType;
import com.campconnect.enums.BadgeRarity;
import com.campconnect.repository.RoleRepository;
import com.campconnect.repository.UserRepository;
import com.campconnect.repository.CategoryRepository;
import com.campconnect.entity.Category;
import com.campconnect.entity.Role;
import com.campconnect.entity.User;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    com.campconnect.repository.CourseRepository courseRepository;
    
    @Autowired
    com.campconnect.repository.EventRepository eventRepository;
    
    @Autowired
    com.campconnect.repository.BadgeRepository badgeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    com.campconnect.repository.CertificationRepository certificationRepository;

    @Autowired
    com.campconnect.repository.VideoRepository videoRepository;

    @Override
    public void run(String... args) {
        // Purge Academy data to resolve mapping conflicts and legacy data issues
        courseRepository.deleteAll();
        categoryRepository.deleteAll();
        badgeRepository.deleteAll();
        certificationRepository.deleteAll();
        videoRepository.deleteAll();

        if (userRepository.count() == 0) {
        // Initialize Roles
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(ERole.ROLE_USER));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }

        // Initialize Users
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User("admin", "admin@campconnect.com", encoder.encode("admin123"), "Administrator");
            Set<Role> roles = new HashSet<>();
            roleRepository.findByName(ERole.ROLE_ADMIN).ifPresent(roles::add);
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("camper")) {
            User camper = new User("camper", "camper@campconnect.com", encoder.encode("camper123"), "Happy Camper");
            Set<Role> roles = new HashSet<>();
            roleRepository.findByName(ERole.ROLE_USER).ifPresent(roles::add);
            camper.setRoles(roles);
            userRepository.save(camper);
        }

        }

        // Initialize Sample Aligned Data
        if (courseRepository.count() == 0) {
            Category survival = categoryRepository.findByName("survival")
                .orElseGet(() -> categoryRepository.save(new Category("survival", "Survival skills")));
            
            User admin = userRepository.findByUsername("admin").orElse(null);

            com.campconnect.entity.Course course = new com.campconnect.entity.Course();
            course.setTitle("Survival Basics");
            course.setDescription("Learn to survive in the wild.");
            course.setCategory(survival);
            course.setDifficulty(DifficultyLevel.BEGINNER.name());
            course.setPrice(49.99);
            course.setPassingScore(80);
            course.setCreator(admin);
            courseRepository.save(course);
        }

        if (eventRepository.count() == 0) {
            User admin = userRepository.findByUsername("admin").orElse(null);

            // Event 1: Full Moon Night Hike
            com.campconnect.entity.Event hike = new com.campconnect.entity.Event();
            hike.setTitle("Full Moon Night Hike & Stargazing");
            hike.setDescription("Experience the desert under a full moon with expert astronomy guides. Includes telescope viewing, guided constellation mapping, and a midnight snack at the summit. Perfect for photographers and nature lovers.");
            hike.setType(EventType.GUIDED_HIKE.name());
            hike.setPrice(45.0);
            hike.setStartDate(java.time.LocalDateTime.of(2026, 3, 20, 20, 30));
            hike.setEndDate(java.time.LocalDateTime.of(2026, 3, 21, 2, 0));
            hike.setLocation(new com.campconnect.entity.Location("Joshua Tree National Park, CA", "74485 National Park Dr, Twentynine Palms, CA 92277", null));
            hike.setCapacity(15);
            hike.setRegistered(15);
            hike.setDifficulty(DifficultyLevel.INTERMEDIATE.name());
            hike.setTags(java.util.Arrays.asList("Stargazing", "Night Hike", "Photography"));
            hike.setImageUrl("https://images.unsplash.com/photo-1532983330958-4b32bc9bb07d?w=800");
            hike.setStatus(com.campconnect.enums.EventStatus.UPCOMING.name());
            hike.setWhatToExpect(java.util.Arrays.asList("Breathtaking views", "Expert guide", "Dark sky experience"));
            hike.setWhatToBring(java.util.Arrays.asList("Headlamp (red light)", "Warm layers", "Sturdy boots"));
            hike.setCreator(admin);
            eventRepository.save(hike);

            // Event 2: Weekend Backpacking Expedition
            com.campconnect.entity.Event expedition = new com.campconnect.entity.Event();
            expedition.setTitle("Weekend Backpacking Expedition");
            expedition.setDescription("A 3-day adventure through the heart of the Grand Canyon. Traverse historic trails, camp beside the Colorado River, and witness spectacular geological formations. This is a rigorous trip for experienced hikers.");
            expedition.setType(EventType.EXPEDITION.name());
            expedition.setPrice(180.0);
            expedition.setStartDate(java.time.LocalDateTime.of(2026, 3, 28, 7, 0));
            expedition.setEndDate(java.time.LocalDateTime.of(2026, 3, 30, 17, 0));
            expedition.setLocation(new com.campconnect.entity.Location("Grand Canyon, AZ", "Grand Canyon Village, AZ 86023", null));
            expedition.setCapacity(12);
            expedition.setRegistered(8);
            expedition.setDifficulty(DifficultyLevel.ADVANCED.name());
            expedition.setTags(java.util.Arrays.asList("Backpacking", "Canyon", "Trekking"));
            expedition.setImageUrl("https://images.unsplash.com/photo-1502134249126-5f5a0910fe71?w=800");
            expedition.setStatus(com.campconnect.enums.EventStatus.UPCOMING.name());
            expedition.setWhatToExpect(java.util.Arrays.asList("Epic canyon views", "River camping", "Challenging terrain"));
            expedition.setWhatToBring(java.util.Arrays.asList("Full backpacking gear", "Water filtration", "Permit"));
            expedition.setCreator(admin);
            eventRepository.save(expedition);

            // Event 3: Beginner Wilderness Skills
            com.campconnect.entity.Event workshop = new com.campconnect.entity.Event();
            workshop.setTitle("Beginner Wilderness Skills");
            workshop.setDescription("Master the fundamentals of outdoor survival. Learn fire starting, shelter building, water purification, and basic navigation in a safe, mentored environment. Ideal for those starting their outdoor journey.");
            workshop.setType(EventType.WORKSHOP.name());
            workshop.setPrice(120.0);
            workshop.setStartDate(java.time.LocalDateTime.of(2026, 3, 15, 9, 0));
            workshop.setEndDate(java.time.LocalDateTime.of(2026, 3, 15, 17, 0));
            workshop.setLocation(new com.campconnect.entity.Location("Rocky Mountains, CO", "Estes Park, CO 80517", null));
            workshop.setCapacity(10);
            workshop.setRegistered(5);
            workshop.setDifficulty(DifficultyLevel.BEGINNER.name());
            workshop.setTags(java.util.Arrays.asList("Survival", "Workshop", "Fundamentals"));
            workshop.setImageUrl("https://images.unsplash.com/photo-1476041800959-2f6bb412c8ce?w=800");
            workshop.setStatus(com.campconnect.enums.EventStatus.UPCOMING.name());
            workshop.setWhatToExpect(java.util.Arrays.asList("Hands-on training", "Survival kit guide", "Practical navigation"));
            workshop.setWhatToBring(java.util.Arrays.asList("Comfortable clothing", "Notebook", "Curiosity"));
            workshop.setCreator(admin);
            eventRepository.save(workshop);
        }

        if (badgeRepository.count() == 0) {
            Category survival = categoryRepository.findByName("survival")
                .orElseGet(() -> categoryRepository.save(new Category("survival", "Survival skills")));
            
            User admin = userRepository.findByUsername("admin").orElse(null);

            com.campconnect.entity.Badge badge = new com.campconnect.entity.Badge();
            badge.setName("First Fire");
            badge.setDescription("Successfully started a fire without matches.");
            badge.setIcon("fire-icon");
            badge.setCategory(survival);
            badge.setRarity(com.campconnect.enums.BadgeRarity.COMMON.name());
            badge.setRequirements(java.util.Arrays.asList("Complete Survival Module 1", "Pass practical exam"));
            badge.setCreator(admin);
            badgeRepository.save(badge);
        }

        if (videoRepository.count() == 0) {
            User admin = userRepository.findByUsername("admin").orElse(null);

            com.campconnect.entity.Video video1 = new com.campconnect.entity.Video();
            video1.setTitle("How to Start a Fire in the Rain");
            video1.setDescription("Essential survival tips for wet conditions.");
            video1.setVideoUrl("https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?w=800");
            video1.setThumbnailUrl("https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?w=800");
            video1.setCategory("survival");
            video1.setType("REEL");
            video1.setViews(1250);
            video1.setHelpfulCount(45);
            video1.setCreatedAt(java.time.LocalDateTime.now());
            video1.setTakeaways(java.util.Arrays.asList("Dry tinder is key", "Use a reflector", "Build a small pyramid"));
            video1.setCreator(admin);
            videoRepository.save(video1);

            com.campconnect.entity.Video video2 = new com.campconnect.entity.Video();
            video2.setTitle("Navigation without a Compass");
            video2.setDescription("Learn to find your way using the sun and stars.");
            video2.setVideoUrl("https://images.unsplash.com/photo-1533420227224-37ceb40ddcc1?w=800");
            video2.setThumbnailUrl("https://images.unsplash.com/photo-1533420227224-37ceb40ddcc1?w=800");
            video2.setCategory("navigation");
            video2.setType("TUTORIAL");
            video2.setViews(850);
            video2.setHelpfulCount(32);
            video2.setCreatedAt(java.time.LocalDateTime.now().minusDays(2));
            video2.setTakeaways(java.util.Arrays.asList("Shadow tip method", "North star location", "Watch hands technique"));
            video2.setCreator(admin);
            videoRepository.save(video2);
        }
    }
}
