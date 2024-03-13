package com.usuarios.cadastro.controller;

import com.usuarios.cadastro.mapper.UserMapper;
import com.usuarios.cadastro.record.UserRecord;
import com.usuarios.cadastro.record.validgroup.CreateUserRecord;
import com.usuarios.cadastro.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/find-by-username-or-email/{usernameOrEmail}")
    public ResponseEntity<Page<UserRecord>> findByName(
            @PathVariable("usernameOrEmail") String usernameOrEmail,
            @RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        var users = userService.findByNameOrEmail(usernameOrEmail, pageNumber, pageSize);
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

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteById(@PathVariable("username") String name) {
        userService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

}
