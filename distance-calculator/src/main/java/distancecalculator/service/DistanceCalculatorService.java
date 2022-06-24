package distancecalculator.service;

import distancecalculator.model.CoordinatesDistance;

public interface DistanceCalculatorService {

    CoordinatesDistance getDistancesByDateTime(int year, int month, int day, int hour);
}
