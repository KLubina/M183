package com.example.jwt.core.startup;

import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.AuthorityRepository;
import com.example.jwt.domain.rank.Rank; // Hinzugefügt
import com.example.jwt.domain.rank.RankRepository;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final RankRepository rankRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, AuthorityRepository authorityRepository, RankRepository rankRepository) {
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.rankRepository = rankRepository;
    }

    @PostConstruct
    public void initializeData() {
        // Rolle 'CLIENT' erstellen
        if (roleRepository.findByName("CLIENT").isEmpty()) {
            Role clientRole = new Role();
            clientRole.setName("CLIENT");
            roleRepository.save(clientRole);
        }

        // Autoritäten erstellen
        if (authorityRepository.findByName("CAN_PLACE_ORDER").isEmpty()) {
            authorityRepository.save(new Authority("CAN_PLACE_ORDER"));
        }

        if (authorityRepository.findByName("CAN_RETRIEVE_PURCHASE_HISTORY").isEmpty()) {
            authorityRepository.save(new Authority("CAN_RETRIEVE_PURCHASE_HISTORY"));
        }

        if (authorityRepository.findByName("CAN_RETRIEVE_PRODUCTS").isEmpty()) {
            authorityRepository.save(new Authority("CAN_RETRIEVE_PRODUCTS"));
        }

        // Ränge erstellen
        if (rankRepository.findByName("Silver").isEmpty()) {
            rankRepository.save(new Rank("Silver"));
        }

        if (rankRepository.findByName("Gold").isEmpty()) {
            rankRepository.save(new Rank("Gold"));
        }

        if (rankRepository.findByName("Platinum").isEmpty()) {
            rankRepository.save(new Rank("Platinum"));
        }
    }
}
