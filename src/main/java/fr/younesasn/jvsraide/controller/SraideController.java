package fr.younesasn.jvsraide.controller;

import fr.younesasn.jvsraide.model.Sraide;
import fr.younesasn.jvsraide.repository.SraideRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sraides")
public class SraideController {

    final SraideRepository sraideRepository;

    public SraideController(SraideRepository sraideRepository) {
        this.sraideRepository = sraideRepository;
    }

    @GetMapping
    public ResponseEntity<List<Sraide>> getAll() {
        return new ResponseEntity<>(sraideRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sraide> create(@RequestBody Sraide sraide) {
        Sraide sraideCreated = sraideRepository.save(sraide);
        return new ResponseEntity<>(sraideCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sraide> getSraideById(@PathVariable Long id) {
        Optional<Sraide> sraide = sraideRepository.findById(id);

        return (sraide.isPresent()) ?
                new ResponseEntity<>(sraide.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sraide> udpate(@PathVariable Long id, @RequestBody Sraide sraideModified) {
        Optional<Sraide> sraide = sraideRepository.findById(id);

        if (sraide.isPresent()) {
            Sraide existingSraide = sraide.get();
            existingSraide.setComment(sraideModified.getComment());

            Sraide updatedSraide = sraideRepository.save(existingSraide);
            return new ResponseEntity<>(updatedSraide, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Sraide> sraide = sraideRepository.findById(id);
        if (sraide.isPresent()) {
            sraideRepository.delete(sraide.get());
            return new ResponseEntity<>("Sraide bien supprimé", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sraide non trouvé", HttpStatus.NOT_FOUND);
    }
}