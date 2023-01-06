package com.kev.HotelManagementApplication.booking;

import com.kev.HotelManagementApplication.entity.Booking;
import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import com.kev.HotelManagementApplication.roomType.RoomTypeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private DTOFactory dtoFactory;

    //Need to add some logic to methods for dates:
    // e.g. dateIn is at least current date
    // dateOut is after dateIn
    // error if date not in correct format etc.
    public List<BookingDTO> getBookingList(){
        List<BookingDTO> list = new ArrayList<>();
        for (Booking booking : bookingRepository.findAll()){
            BookingDTO bookingDTO = dtoFactory.createDTO(booking);
            list.add(bookingDTO);
        }
        return list;
    }

    public boolean deleteBooking(int id) {
        if (bookingRepository.existsById(id)) {
            try {
                bookingRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return false;
            }
        }
        return false;
    }

    public BookingDTO updateBooking(int bookingId, String dateIn, String dateOut) {

        Optional<Booking> originalBookingDTO = bookingRepository.findById(bookingId);

        if (!originalBookingDTO.isPresent()) {
            return null;
        }

        Booking bookingEntity = originalBookingDTO.get();
        LocalDate parsedDateIn = LocalDate.parse(dateIn);
        LocalDate parsedDateOut = LocalDate.parse(dateOut);
        bookingEntity.setDateIn(parsedDateIn);
        bookingEntity.setDateOut(parsedDateOut);
        bookingRepository.save(bookingEntity);

        return dtoFactory.createDTO(bookingEntity);
    }


}
