import java.util.*;
/**
 * BoundedStack คือ ADTแทนชั้นหนังสือที่บรรณารักษ์ใช้บันทึกการยืมและคืนหนังสือ
 * 
 */
public class Boundedstack { 
    //public static final int MAX_BOOKS = 200; 
    private final List<String> books; // private final String book ;
    private final int capacity;
    private final int size;
    //Abstraction Function:
    //Af(books)- ชั้นหนังสือที่จัดเก็บหนังสือ "A100","A101"...
    
    // Representation Invariant:
    //-ต้องมีรายหนังสืออยู่จริง (ไม่เป็น 'null')
    //-ไม่มีหนังสือไหนเป็น 'null'
    //-ไม่มีชื่อหนังสือที่เป็นสตริงว่าง
    //-รหัสหนังสือไม่ซ้ำกัน
    //-มีหนังสือไม่เกินขนาดที่กำหนด
    //-
    private void checkRep() {
        assert books != null : "song is not null";
        //assert books.size()<=MAX_BOOKS: "books <= maxbooks";
    Set<String> seen = new HashSet<>();
       for(String b: books){
        assert b != null;
        assert b != "";
        assert seen.add(b);

       }
    }
    
    /*public Boundedstack() {
        this.books = new ArrayList<>();
        checkRep();
    }*/
    
    /**
     * 
     * @param capacity
     * @throws IllegalArgumentException ถ้า capacity
     */
    public Boundedstack(int capacity){
        this.books = new ArrayList<>();
        this.size = 150;
        this.capacity = capacity;
    }
    /**
     * 
     * @param book รหัสฟหนังสือ ต้องไม่เป็นnullและเป็นสตริงว่าง
     * @return
     * @throws IllegalArgumentException ถ้าbookเป็นnullหรือสตริงว่าง
     */
    public void add(String book){
     if(book == null) throw new IllegalArgumentException();
     if(book=="" )throw new IllegalArgumentException();


    }

}