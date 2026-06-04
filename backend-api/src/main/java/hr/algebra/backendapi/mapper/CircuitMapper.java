package hr.algebra.backendapi.mapper;

import hr.algebra.backendapi.dto.CircuitRequestDto;
import hr.algebra.backendapi.dto.CircuitResponseDto;
import hr.algebra.backendapi.model.Circuit;
import org.springframework.stereotype.Component;

@Component
public class CircuitMapper {
    public Circuit toEntity(CircuitRequestDto dto) {
        Circuit circuit = new Circuit();
        circuit.setTrackName(dto.getTrackName());
        circuit.setCountry(dto.getCountry());
        circuit.setCity(dto.getCity());
        circuit.setCircuitLength(dto.getCircuitLength());
        circuit.setFirstGrandPrix(dto.getFirstGrandPrix());
        circuit.setNumberOfLaps(dto.getNumberOfLaps());
        circuit.setRaceDistance(dto.getRaceDistance());
        circuit.setFastestLapTime(dto.getFastestLapTime());
        circuit.setCircuitImageUrl(dto.getCircuitImageUrl());
        return circuit;
    }

    public CircuitResponseDto toResponseDto(Circuit circuit) {
        CircuitResponseDto dto = new CircuitResponseDto();
        dto.setId(circuit.getId());
        dto.setTrackName(circuit.getTrackName());
        dto.setCountry(circuit.getCountry());
        dto.setCity(circuit.getCity());
        dto.setCircuitLength(circuit.getCircuitLength());
        dto.setFirstGrandPrix(circuit.getFirstGrandPrix());
        dto.setNumberOfLaps(circuit.getNumberOfLaps());
        dto.setRaceDistance(circuit.getRaceDistance());
        dto.setFastestLapTime(circuit.getFastestLapTime());
        dto.setCircuitImageUrl(circuit.getCircuitImageUrl());
        return dto;
    }
}
