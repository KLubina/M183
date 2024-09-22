package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.example.jwt.domain.user.dto.UserDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends ExtendedMapper<User, UserDTO> {

  @Mapping(target = "rank", ignore = true)  // Optional: Falls du Rank später manuell zuweist
  User fromUserRegisterDTO(UserRegisterDTO dto);
}
