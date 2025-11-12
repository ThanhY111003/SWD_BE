package com.example.Back_end.config;

import com.example.Back_end.entity.Lab;
import com.example.Back_end.enums.LabStatus;
import com.example.Back_end.repository.LabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final LabRepository labRepository;

    @Override
    public void run(String... args) {

        // ✅ Seed Labs only if empty
        if (labRepository.count() == 0) {
            List<Lab> labs = List.of(
                    createLab("AI Research Lab", "AI-001", "Building A, Floor 3", "Lab for AI and Robotics research"),
                    createLab("BioTech Innovation Center", "BIO-002", "Building B, Floor 1", "Focused on biotechnology and genetic engineering experiments"),
                    createLab("Cybersecurity Operations Lab", "SEC-003", "Building C, Floor 2", "Practical security analysis, penetration testing and defense simulation"),
                    createLab("Green Energy Research Unit", "ENG-004", "Building D, Floor 4", "Developing sustainable and renewable energy systems"),
                    createLab("Data Science and Analytics Hub", "DS-005", "Building E, Floor 3", "Working on machine learning, AI analytics, and big data visualization"),
                    createLab("Human-Computer Interaction Studio", "HCI-006", "Building F, Floor 1", "Experimenting with UX/UI, AR/VR interfaces, and user experience testing"),
                    createLab("Quantum Computing Lab", "QC-007", "Building G, Floor 2", "Exploring quantum algorithms and quantum circuit simulations"),
                    createLab("Robotics and Automation Lab", "ROB-008", "Building H, Floor 5", "Developing autonomous robots and industrial automation systems"),
                    createLab("Environmental Monitoring Lab", "ENV-009", "Building I, Floor 2", "Analyzing air, water, and soil quality using sensor networks"),
                    createLab("Software Engineering Lab", "SWE-010", "Building J, Floor 4", "Focusing on agile development, DevOps, and software quality assurance")
            );

            labRepository.saveAll(labs);
            System.out.println("✅ 10 Labs seeded successfully!");
        } else {
            System.out.println("ℹ️ Labs already exist, skipping seed.");
        }
    }

    // helper function
    private Lab createLab(String name, String code, String location, String description) {
        Lab lab = new Lab();
        lab.setLabName(name);
        lab.setLabCode(code);
        lab.setLocation(location);
        lab.setDescription(description);
        lab.setStatus(LabStatus.ACTIVE);
        lab.setRooms(null);
        lab.setRoomSlots(null);
        lab.setMembers(null);
        lab.setStaffs(null);
        return lab;
    }
}
