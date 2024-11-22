package theater.person;
import theater.TheaterSeating;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.MethodOrderer.*;
import check.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import theater.seating.Seat;
import theater.seating.SeatType;

import java.util.stream.Stream;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SpectatorTest {
    
    private final static int ROW_COUNT = 4;
    private final static int COL_COUNT = 5;

    @Test
    public void testTakeGiftWithoutGift(){
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John Doe");
        spectator.bookSpecificSeat(theaterSeating,0,0);
        assertEquals(spectator.takeGift(),spectator.getSeat().getHasGift());
    }

    @Test
    public void testTakeGiftWithGift(){
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John Doe");
        spectator.bookSpecificSeat(theaterSeating,0,1);
        boolean result=spectator.getSeat().getHasGift();
        assertEquals(spectator.takeGift(),result);
    }

    @Test
    public void testBookSpecificSeat(){
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John Doe");
        spectator.bookSpecificSeat(theaterSeating,0,1);
        assertEquals(spectator.getSeat(),theaterSeating.getSeats()[0][1]);
    }

    @Test
    public void testBookATailoredSeatEvenNameLength(){
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("john");
        spectator.bookTailoredSeat(theaterSeating);
        assertEquals(spectator.getSeat(),theaterSeating.getSeats()[0][0]);
    }

    @Test
    public void testBookATailoredSeatOddNameLength(){
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("johny");
        spectator.bookTailoredSeat(theaterSeating);
        assertEquals(spectator.getSeat(),theaterSeating.getSeats()[1][2]);
    }

    @Test
    public void testBookTailoredSeatShortName(){
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("A");
        spectator.bookTailoredSeat(theaterSeating);
        assertEquals(spectator.getSeat(),theaterSeating.getSeats()[0][0]);
    }


    static Stream<Arguments> provideBooleanCases() {
        return Stream.of(
            Arguments.of("John", true),
            Arguments.of("Jane", false)
        );
    }

    static Stream<Arguments> provideCornerCases() {
        return Stream.of(
            Arguments.of(0, 0), 
            Arguments.of(0, COL_COUNT - 1), 
            Arguments.of(ROW_COUNT - 1, 0), 
            Arguments.of(ROW_COUNT - 1, COL_COUNT - 1), 
            Arguments.of(2, 2) 
        );
    }

    // Method source for the third test
    static Stream<Arguments> provideNameCases() {
        return Stream.of(
            Arguments.of("Roxy", SeatType.OT),
            Arguments.of("Bob", SeatType.MT),
            Arguments.of("Al", SeatType.OT)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBooleanCases")
    public void testTakeGift(String name, boolean hasGift) {
        Spectator spectator=new Spectator(name);
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        theaterSeating.getSeats()[0][1].setHasGift(hasGift); 
        spectator.bookSpecificSeat(theaterSeating,0,1);
        assertEquals(hasGift,spectator.takeGift());
        assertEquals(false,spectator.takeGift());
    }

    @ParameterizedTest
    @MethodSource("provideCornerCases")
    public void testBookSpecificSeat(int bookedRow, int bookedCol) {
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John Doe");
        spectator.bookSpecificSeat(theaterSeating, bookedRow, bookedCol);
        Seat bookedSeat = spectator.getSeat();
        assertNotNull(bookedSeat);
        assertTrue(bookedSeat.getIsOccupied());
    }

    @ParameterizedTest
    @MethodSource("provideNameCases")
    public void testBookTailoredSeat(String name, SeatType expected) {
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator(name);
        spectator.bookTailoredSeat(theaterSeating);
        Seat bookedSeat = spectator.getSeat();
        assertNotNull(bookedSeat);
        assertEquals(expected, bookedSeat.getSeatType());
    }
    @Test
    public void testGetName() {
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John");
        assertEquals(spectator.getName(),"John");      
    }

    @Test
    public void testGetSeatInitialState() {
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John");
        assertEquals(null,spectator.getSeat());        
    }

    @Test
    public void testConstructorWithNullName() {
        IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Spectator("")
        );
    }

    @Test
    public void testBookAnySeat() {
        TheaterSeating theaterSeating = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("John Doe");
        spectator.bookAnySeat(theaterSeating);
        assertEquals(true,theaterSeating.getSeats()[0][0].getIsOccupied());
    }
}
