import java.util.*;
/**
 * BoundedStack คือการตรวจสอบหนังสือในห้องสมุด
 * 
 */
public class Boundedstack {
    private final List<String> books; // private final String book ;
    private final int size;
    private final int capacity;
    //Af(book)- 
    //


    
    //RI
    // Representation Invariant:
    //-ต้องมีรายหนังสืออยู่จริง (ไม่เป็น null)
    //-ไม่มีหนังสือไหนเป็น null
    //-ไม่มีชื่อหนังสือที่เป็นสตริงว่าง
    //-
    //-
    //-

    /**
     * 
     * @param capacity
     */
    public Boundedstack(int capacity){
        this.books = new ArrayList<>();
        this.size = 500;
        this.capacity = capacity;
    }
    /**
     * 
     * @param s
     */
    public void push(String s){

    }

}