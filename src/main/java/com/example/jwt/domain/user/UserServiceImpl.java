package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.rank.Rank;
import com.example.jwt.domain.rank.RankRepository;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  // Logger innerhalb der Klassendefinition
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RoleRepository roleRepository;
  private final AuthorityRepository authorityRepository;
  private final RankRepository rankRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
                         BCryptPasswordEncoder bCryptPasswordEncoder,
                         RoleRepository roleRepository,
                         AuthorityRepository authorityRepository,
                         RankRepository rankRepository) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleRepository = roleRepository;
    this.authorityRepository = authorityRepository;
    this.rankRepository = rankRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByUsername(username)
            .map(UserDetailsImpl::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  @Override
  public User register(User user) {
    if (((UserRepository) repository).existsByUsername(user.getUsername())) {
      throw new RuntimeException("Username already exists");
    }

    Role clientRole = roleRepository.findByName("CLIENT")
            .orElseThrow(() -> new RuntimeException("Role 'CLIENT' not found"));

    Authority placeOrder = authorityRepository.findByName("CAN_PLACE_ORDER")
            .orElseThrow(() -> new RuntimeException("Authority 'CAN_PLACE_ORDER' not found"));

    user.setRoles(Set.of(clientRole));
    user.setAuthorities(Set.of(placeOrder));

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    Rank silverRank = rankRepository.findByName("Silver")
            .orElseThrow(() -> new RuntimeException("Rank 'Silver' not found"));
    user.setRank(silverRank);

    return save(user);
  }
}
