import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

class Hotel {
    int id;
    Queue<Room> roomList;

    Hotel(int id) {
        this.id = id;
        this.roomList = new ConcurrentLinkedQueue<>();
    }

    public int getId() {
        return id;
    }

    public Queue<Room> getRoomList() {
         return new ConcurrentLinkedQueue<>(roomList);
    }

    public void addRoom(Room room) {
        this.roomList.add(room);
    }

}

class Room {
    int id;
    boolean is_booked;

    Room(int id) {
        this.id = id;
    }

    synchronized boolean bookRoom(Room room) {
        if(room.is_booked) {
            return false;
        }
        room.is_booked = true;
        return true;
    }

}

class HotelService {

    public void addRoom(Hotel hotel, Room room) {
        hotel.addRoom(room);
    }

    public boolean bookRoom(Hotel hotel) {
        Queue<Room> roomList = hotel.roomList;
        Room room = roomList.poll();
        if (room == null) {
            return false;
        }
        return room.bookRoom(room);
    }


}


public class TripJack {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        Hotel hotel = new Hotel(1);
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        Room room4 = new Room(4);

        HotelService service = new HotelService();

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);
        hotel.addRoom(room4);

        List<Room> roomList = Arrays.asList(room1, room2, room3, room4);

        List<Future<Boolean>> futureList = new ArrayList<>();
        for (int i = 0;i<5;i++) {
            final int temp = i;
            Future<Boolean> f = ex.submit(() -> service.bookRoom(hotel));
            futureList.add(f);
        }

        for(int j=0;j<futureList.size();j++) {
            System.out.println(futureList.get(j).get());
        }

        Thread.sleep(2000);
        Thread.currentThread().join();
    }
}
