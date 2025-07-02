package com.project.hms.modules.reservation;

import com.project.hms.modules.reservation.dto.AddReservationRequest;
import com.project.hms.modules.reservation.dto.ReservationListResponse;
import com.project.hms.common.validation.ValidationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hms/v1/reservation")
public class ReservationController {

    private final ValidationService validationService;
    private final ReservationService reservationService;

    public ReservationController(ValidationService validationService, ReservationService reservationService) {
        this.validationService = validationService;
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/create")
    public void postReservation(@RequestBody AddReservationRequest request) {
        if (validateRequest(request)) {
            reservationService.createReservation(request.getName(), request.getSurname(),
                    request.getDateStart(), request.getDateEnd());
        }
    }

    @PutMapping(value = "/create")
    public void putReservation(@RequestBody AddReservationRequest request) {
        if (validateRequest(request)) {
            reservationService.createReservation(request.getName(), request.getSurname(),
                    request.getDateStart(), request.getDateEnd());
        }
    }

    @GetMapping("getAll")
    public ReservationListResponse getAllReservations(@RequestParam String clientId, @RequestParam String apiKey) {
        if (validationService.validateAuthData(clientId, apiKey)) {
            return reservationService.getAllReservations();
        }
        return new ReservationListResponse();
    }

    private boolean validateRequest(AddReservationRequest request) {
        if (!validationService.validateAuthData(request.getClientId(), request.getApiKey())) {
            return false;
        }
        System.out.println("----Stub for request validation----");
        return true;
    }

}
