package com.usuarios.cadastro.controller;

import com.usuarios.cadastro.entity.User;
import com.usuarios.cadastro.mapper.UserMapper;
import com.usuarios.cadastro.record.UserRecord;
import com.usuarios.cadastro.record.validgroup.CreateUserRecord;
import com.usuarios.cadastro.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            @RequestBody @Validated(CreateUserRecord.class) UserRecord userRecord) {
        var user = userService.save(UserMapper.toModel(userRecord));
        return ResponseEntity.ok().body(UserMapper.toRecord(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserRecord>> findAll(
            @RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        var users = userService.findAll(pageNumber, pageSize);
        var pagedUsersRecords = users.map(UserMapper::toRecord);
        return ResponseEntity.ok().body(pagedUsersRecords);
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
