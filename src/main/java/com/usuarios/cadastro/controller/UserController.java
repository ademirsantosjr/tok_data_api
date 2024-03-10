package com.usuarios.cadastro.controller;

import com.usuarios.cadastro.entity.User;
import com.usuarios.cadastro.mapper.UserMapper;
import com.usuarios.cadastro.record.UserRecord;
import com.usuarios.cadastro.record.validgroup.CreateUserRecord;
import com.usuarios.cadastro.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserRecord> save(
            @RequestBody
            @Validated(CreateUserRecord.class)
            UserRecord userRecord) {

        var user = userService.save(UserMapper.toModel(userRecord));
        return ResponseEntity.ok().body(UserMapper.toRecord(user));
    }

    @GetMapping
    public ResponseEntity<Collection<UserRecord>> findAll() {
        var users = userService.findAll();
        var usersRecords = users.stream().map(UserMapper::toRecord).toList();
        return ResponseEntity.ok().body(usersRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> findById(@PathVariable Integer id) {
        var user = userService.findById(id);
        return ResponseEntity.ok().body(UserMapper.toRecord(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedById(@RequestBody UserRecord userRecord,
                                            @PathVariable Integer id) {
        var user = UserMapper.toModel(userRecord);
        userService.updateById(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
