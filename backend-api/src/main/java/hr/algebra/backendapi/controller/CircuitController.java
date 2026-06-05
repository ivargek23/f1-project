package hr.algebra.backendapi.controller;

import hr.algebra.backendapi.dto.CircuitRequestDto;
import hr.algebra.backendapi.dto.CircuitResponseDto;
import hr.algebra.backendapi.service.CircuitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/circuits")
@RequiredArgsConstructor
public class CircuitController {
    private final CircuitService circuitService;

    @GetMapping
    public ResponseEntity<List<CircuitResponseDto>> getAllCircuits() {
        return ResponseEntity.ok(circuitService.getAllCircuits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CircuitResponseDto> getCircuitById(@PathVariable Long id) {
        return ResponseEntity.ok(circuitService.getCircuitById(id));
    }

    @PostMapping
    public ResponseEntity<CircuitResponseDto> createCircuit(@RequestBody @Valid CircuitRequestDto circuitRequestDto) {
        return ResponseEntity.ok(circuitService.createCircuit(circuitRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CircuitResponseDto> updateCircuit(@PathVariable Long id, @RequestBody @Valid CircuitRequestDto circuitRequestDto) {
        return ResponseEntity.ok(circuitService.updateCircuit(id, circuitRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCircuit(@PathVariable Long id) {
        circuitService.deleteCircuit(id);
        return ResponseEntity.noContent().build();
    }
}
