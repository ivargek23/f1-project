package hr.algebra.backendapi.service.impl;

import hr.algebra.backendapi.dto.CircuitRequestDto;
import hr.algebra.backendapi.dto.CircuitResponseDto;
import hr.algebra.backendapi.exception.ResourceNotFoundException;
import hr.algebra.backendapi.mapper.CircuitMapper;
import hr.algebra.backendapi.model.Circuit;
import hr.algebra.backendapi.repository.CircuitRepository;
import hr.algebra.backendapi.service.CircuitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircuitServiceImpl implements CircuitService {
    private final CircuitRepository circuitRepository;
    private final CircuitMapper circuitMapper;

    @Override
    public List<CircuitResponseDto> getAllCircuits() {
        return circuitRepository.findAll().stream().map(circuitMapper::toResponseDto).toList();
    }

    @Override
    public CircuitResponseDto getCircuitById(Long id) {
        return circuitRepository
                .findById(id)
                .map(circuitMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Circuit with id " + id + " not found"));
    }

    @Override
    public CircuitResponseDto createCircuit(CircuitRequestDto request) {
        Circuit circuit = circuitMapper.toEntity(request);
        return circuitMapper.toResponseDto(circuitRepository.save(circuit));
    }

    @Override
    public CircuitResponseDto updateCircuit(Long id, CircuitRequestDto request) {
        Circuit circuitToUpdate = circuitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Circuit with id " + id + " not found"));
        updateCircuitData(circuitToUpdate, request);
        return circuitMapper.toResponseDto(circuitRepository.save(circuitToUpdate));
    }

    private void updateCircuitData(Circuit circuitToUpdate, CircuitRequestDto request) {
        circuitToUpdate.setTrackName(request.getTrackName());
        circuitToUpdate.setCountry(request.getCountry());
        circuitToUpdate.setCity(request.getCity());
        circuitToUpdate.setCircuitLength(request.getCircuitLength());
        circuitToUpdate.setFirstGrandPrix(request.getFirstGrandPrix());
        circuitToUpdate.setNumberOfLaps(request.getNumberOfLaps());
        circuitToUpdate.setRaceDistance(request.getRaceDistance());
        circuitToUpdate.setFastestLapTime(request.getFastestLapTime());
        circuitToUpdate.setCircuitImageUrl(request.getCircuitImageUrl());
    }

    @Override
    public void deleteCircuit(Long id) {
        if (!circuitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Circuit with id " + id + " not found");
        }
        circuitRepository.deleteById(id);
    }
}
