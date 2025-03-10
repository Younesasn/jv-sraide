package fr.younesasn.jvsraide.controller;

import fr.younesasn.jvsraide.model.User;
import fr.younesasn.jvsraide.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setFollowing(0);
        user.setFollower(0);
        user.setSubscribeDate(new Date());
        User userCreated = this.userRepository.save(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        return (user.isPresent()) ?
                new ResponseEntity<>(user.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userModified) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setLastname(userModified.getLastname());
            existingUser.setUsername(userModified.getUsername());
            existingUser.setFirstname(userModified.getFirstname());
            existingUser.setDescription(userModified.getDescription());

            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>("User bien supprimé", HttpStatus.OK);
        }

        return new ResponseEntity<>("User non trouvé", HttpStatus.NOT_FOUND);
    }
}