package sydoruk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sydoruk.domain.User;
import sydoruk.domain.plainObjects.UserPojo;
import sydoruk.services.interfaces.IUserService;

import java.util.List;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<UserPojo> createUser(@RequestBody User user) {
        UserPojo result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> getUser(@PathVariable Long id) {
        UserPojo result = userService.getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserPojo>> getUsers() {
        List<UserPojo> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> updateUser(@PathVariable Long id,
                                               @RequestBody User source){

        UserPojo result = userService.updateUser(source, id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

}
