package theater;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.MethodOrderer.*;
import check.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import theater.TheaterSeating;
import theater.seating.Seat;
import theater.seating.SeatType;
public class TheaterSeatingTest{
    @Test
    public void testInitialization(){
        TheaterSeating seating = new TheaterSeating(10, 10);
        assertEquals(10, seating.getSeats().length);
        assertEquals(10,seating.getSeats()[0].length);
        for(int i =0;i<seating.getSeats().length;i++){
            for(int j =0;j<seating.getSeats()[i].length;j++){           
                if(i==0||j==0||i==seating.getSeats().length-1||j==seating.getSeats()[i].length-1){
                    assertEquals(SeatType.OT,seating.getSeats()[i][j].getSeatType());
                }
                else if ((int)Math.floor(seating.getSeats()[i].length/2)==j){
                    assertEquals(SeatType.MT,seating.getSeats()[i][j].getSeatType());
                }
                else{
                    assertEquals(SeatType.IT,seating.getSeats()[i][j].getSeatType());
                }               
            }
        }
    }

    @ParameterizedTest
    @CsvSource({
        "10,10,50",
        "5,5,12",
        "1,1,0",
   })
    public void testGiftsInitialization(int rows,int cols,int expectedGifts){
        int count=0;
        TheaterSeating seating = new TheaterSeating(rows, cols);
        for(int i =0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(seating.getSeats()[i][j].getHasGift()==true){
                    count++;
                }
            }
        }
        assertEquals(expectedGifts,count);
    }

    @Test
    public void testBookSeat(){
        TheaterSeating theater=new TheaterSeating(10,10);
        theater.bookSeat(1, 1);
        assertEquals(true, theater.getSeats()[1][1].getIsOccupied());
        IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> theater.bookSeat(1, 1) 
    );
   }

    @Test
    public void testBookTailoredEmptySeat(){
        TheaterSeating theater=new TheaterSeating(10,10);
        theater.bookTailoredEmptySeat(SeatType.OT,true);
        assertEquals(true, theater.getSeats()[0][0].getIsOccupied());
        
    }
    @Test
    public void testText(){
            TheaterSeating theater=new TheaterSeating(5,5);
        String check="""
        [A] [A] [A] [A] [A]
        [A] [A] [A] [A] [A]
        [A] [A] [A] [A] [A]
        [A] [A] [A] [A] [A]
        [A] [A] [A] [A] [A]
        """;
        assertEquals(check,theater.toString());
    }
}