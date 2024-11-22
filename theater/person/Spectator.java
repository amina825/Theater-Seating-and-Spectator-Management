package theater.person;
import theater.seating.*;
import theater.TheaterSeating;
public class Spectator{
    private Seat seat;
    private final String name;
    public Spectator(String name){
        if(name.length()==0){
            throw new IllegalArgumentException();
        }
        else{
            this.name=name;
        }        
    }

    public Seat getSeat(){
        return seat;
    }

    public String getName(){
        return name;
    }

    public boolean takeGift(){  
        if(seat.getHasGift()==true){       
            seat.setHasGift(false);  
            return true;
        }
        else {
            return false;  
        }       
    }

    public void bookAnySeat(TheaterSeating ts){
        for(int i =0;i<ts.getSeats().length;i++){
            for(int j =0;j<ts.getSeats()[i].length;j++){
                if(!ts.getSeats()[i][j].getIsOccupied()){
                    ts.getSeats()[i][j].setIsOccupied(true);   
                    seat=ts.getSeats()[i][j]; 
                    return;
                }      
            }
        }
    }

    public void bookSpecificSeat(TheaterSeating ts,int row,int col){
        if(!ts.getSeats()[row][col].getIsOccupied()){
            ts.getSeats()[row][col].setIsOccupied(true);        
            seat=ts.getSeats()[row][col]; 
    
        }    
    }
    
    public void bookTailoredSeat(TheaterSeating ts){
        for(int i =0;i<ts.getSeats().length;i++){
            for(int j =0;j<ts.getSeats()[i].length;j++){
               
                if(name.length()%2==0){
                    if(j<(int)Math.floor(ts.getSeats()[i].length/2)){
                        if(ts.getSeats()[i][j].getIsOccupied()==false ){
                           ts.getSeats()[i][j].setIsOccupied(true);
                           seat=ts.getSeats()[i][j];
                           return;
                        }  
                    }                                                         
                }             
                else if(name.length()<3){
                    if(ts.getSeats()[i][j].getSeatType()==SeatType.OT){                         
                        if(ts.getSeats()[i][j].getIsOccupied()==false)
                        ts.getSeats()[i][j].setIsOccupied(true);
                        seat=ts.getSeats()[i][j];
                        return;
                    }
                }
                else {
                    if(ts.getSeats()[i][j].getSeatType()==SeatType.MT){                         
                        if(ts.getSeats()[i][j].getIsOccupied()==false)
                        ts.getSeats()[i][j].setIsOccupied(true);
                        seat=ts.getSeats()[i][j];
                        return;
                    }
                }                                                                    
            }  
        }
    }
}