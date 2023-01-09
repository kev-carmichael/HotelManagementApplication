package com.kev.HotelManagementApplication.booking;

import com.kev.HotelManagementApplication.customer.CustomerRepository;
import com.kev.HotelManagementApplication.entity.Booking;
import com.kev.HotelManagementApplication.entity.Customer;
import com.kev.HotelManagementApplication.entity.Room;
import com.kev.HotelManagementApplication.entity.RoomType;
import com.kev.HotelManagementApplication.error.DateInIsSameAsOrAfterDateOutException;
import com.kev.HotelManagementApplication.error.DobInFutureException;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import com.kev.HotelManagementApplication.room.RoomRepository;
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
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;


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

    public Booking createBooking(String dateIn, String dateOut, int customerid, int roomid) {

        Optional<Customer> customer = customerRepository.
                findById(customerid);

        Optional<Room> room = roomRepository.
                findById(roomid);

        //check dateOut is later than dateIn
        LocalDate parsedDateIn = LocalDate.parse(dateIn);
        LocalDate parsedDateOut = LocalDate.parse(dateOut);
        if(parsedDateIn.isAfter(parsedDateOut)||parsedDateIn==parsedDateOut){
            throw new DateInIsSameAsOrAfterDateOutException();
        }

        //check existing customer and save booking
        if (customer.isPresent()) {
            int size = bookingRepository.findAll().size();
            Booking booking = new Booking(
                    (size + 1),
                    parsedDateIn,
                    parsedDateOut,
                    customer.get(),
                    room.get());
            return bookingRepository.save(booking);
        }

        return null;
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
