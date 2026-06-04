package hr.algebra.backendapi.service;

import hr.algebra.backendapi.dto.CircuitRequestDto;
import hr.algebra.backendapi.dto.CircuitResponseDto;

import java.util.List;

public interface CircuitService {
    List<CircuitResponseDto> getAllCircuits();
    CircuitResponseDto getCircuitById(Long id);
    CircuitResponseDto createCircuit(CircuitRequestDto request);
    CircuitResponseDto updateCircuit(Long id, CircuitRequestDto request);
    void deleteCircuit(Long id);
}
