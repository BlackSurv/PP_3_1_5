package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/usersList")
    public ResponseEntity<List<User>> getUserList() {
        List<User> userList = userService.getAllUsers();
        return userList == null && userList.isEmpty() ?
                new ResponseEntity<>(userList, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(userList, HttpStatus.OK) ;
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Set<Role>> getRolesList() {
        Set<Role> roleSet = roleService.getAllRoles();
        return roleSet == null && roleSet.isEmpty() ?
                new ResponseEntity<>(roleSet, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(roleSet, HttpStatus.OK) ;
    }


    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserFromID(@PathVariable long id) {
        final User user = userService.getUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid User user) {
        //BindingResult bindingResult
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMsg = new StringBuilder();
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for (FieldError error : errors) {
//                errorMsg.append(error.getField() + " - " + error.getDefaultMessage() + ';');
//            }
//
//        }
// TODO
        userService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping(value = "/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid User user) {
        userService.update(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @DeleteMapping(value = "/delete")
//    public ResponseEntity<HttpStatus> delete(@RequestBody Long id) {
//        userService.deleteById(id);
//        return ResponseEntity.ok(HttpStatus.OK);
//    } //Здесь отправлять просто цифру id в http запросе
//    //TODO


//     @DeleteMapping(value = "/delete")
//    public ResponseEntity<HttpStatus> delete(@RequestParam(value="id") Long id) {
//        userService.deleteById(id);
//        return ResponseEntity.ok(HttpStatus.OK);
//    } //Здесь отправлять просто цифру id в http запросе
//    //TODO


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
