package theater;
import theater.seating.*;
public class TheaterSeating{
    private Seat [][]  seats;
    private int giftsTotal;
    public TheaterSeating(int rowCount,int colCount){
        if(rowCount<=0||colCount<=0){
            throw new IllegalArgumentException() ;  
        }
        else
        {
            seats= new Seat[rowCount][colCount];
            initSeating(rowCount,colCount);
        }
    }
    public Seat[][] getSeats(){
        return seats;
    } 

    private void initSeating(int rowCount,int colCount){
        boolean l;
        for(int i =0;i<rowCount;i++){
            for(int j =0;j<colCount;j++){
                String seatId =  String.format("%d,%d",i,j);
                l =(i+j)%2==1;
                if(i==0||j==0||i==rowCount-1||j==colCount-1){
                    seats[i][j]=new Seat(seatId,l,SeatType.OT);
                }
                else if ((int)Math.floor(colCount/2)==j){
                    seats[i][j]=new Seat(seatId,l,SeatType.MT);             

                }
                else{
                    seats[i][j]=new Seat(seatId,l,SeatType.IT);
                }
                if(l){
                    giftsTotal++;
                }
            }
        }
    }

    public Seat bookSeat(){
        for(int i =0;i<seats.length;i++){
            for(int j =0;j<seats[i].length;j++){
                if(seats[i][j].getIsOccupied()==false){
                    seats[i][j].setIsOccupied(true);
                    return seats[i][j];
                }
            }
        }
        return null;
    }

    public Seat bookSeat(int row,int col){
        if(seats[row][col].getIsOccupied()==false){
            seats[row][col].setIsOccupied(true);
            return seats[row][col];
        }
        else{
            throw new IllegalArgumentException("Seat is already occupied");
        }       
    }

    public Seat bookTailoredEmptySeat(SeatType seatType,boolean l ){
        for(int i =0;i<seats.length;i++){
            if(l){
                for(int j =0;j<=(int)Math.floor(seats[i].length/2);j++){
                    if(seats[i][j].getIsOccupied()==false ){
                        if(seats[i][j].getSeatType()==seatType){
                           seats[i][j].setIsOccupied(true);
                           return seats[i][j];
                        }
                   }
               }
            }        
            else{
                for(int j =seats[i].length-1;j>=(int)Math.floor(seats[i].length/2);j--){
                    if(seats[i][j].getIsOccupied()==false ){
                       if(seats[i][j].getSeatType()==seatType){
                            seats[i][j].setIsOccupied(true);
                            return seats[i][j];
                        }
                    }
                }
            }
        }
        return null;     
    }

    public int totalTakenGifts(){
        decreaseGifts();
        return giftsTotal;
    }

    public void decreaseGifts(){
        for(int i=0;i<seats.length;i++){
            for(int j=0;j<seats[i].length;j++){
                if((i+j)%2==1&&!seats[i][j].getHasGift()){
                    giftsTotal-=1;
                }
            }
        }
    }

    @Override
    public String toString() {;
        StringBuilder s=new StringBuilder();
        for(int i =0;i<seats.length;i++){
            for(int j =0;j<seats[i].length;j++){
                if (seats[i][j].getIsOccupied()) {
                    s.append("[B]");
                } else {
                    s.append("[A]");
                }
                if (j < seats[i].length - 1) {
                    s.append(" ");
                }            
            }
            s.append("\n");
        }
        return (s.toString());    
    }

    public int getAmountOfGifts(){
        return giftsTotal;
        
    }
}
