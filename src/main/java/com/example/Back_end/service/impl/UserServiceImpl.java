package com.example.Back_end.service.impl;

import com.example.Back_end.dto.UserRequestDT0;
import com.example.Back_end.dto.UserResponseDTO;
import com.example.Back_end.entity.User;
import com.example.Back_end.repository.UserRepository;
import com.example.Back_end.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserResponseDTO create(UserRequestDT0 request) {
        User user = modelMapper.map(request, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDT0 request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        modelMapper.map(request, user);
        user.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO getById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
