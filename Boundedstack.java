import java.util.*;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * BoundedStack คือ ADTแทนชั้นหนังสือที่บรรณารักษ์ใช้บันทึกการยืมและคืนหนังสือ
 * 
 */
public class Boundedstack { 
    //public static final int MAX_BOOKS = 200; 
    private final List<String> books; // private final String book ;
    private final int capacity;
    //Abstraction Function:
    //Af(books)- ชั้นหนังสือที่จัดเก็บหนังสือ "A100","A101"...
    
    // Representation Invariant:
    //-ต้องมีรายหนังสืออยู่จริง (ไม่เป็น 'null')
    //-ไม่มีหนังสือไหนเป็น 'null'
    //-ไม่มีชื่อหนังสือที่เป็นสตริงว่าง
    //-รหัสหนังสือไม่ซ้ำกัน
    //-มีหนังสือไม่เกินขนาดที่กำหนด
    
    /**
     * เขียนcheckRep()เพื่อให้ตรงตามเงื่อนไขRI
     * โดยแปลงRiเป็นassert
     */
    private void checkRep() {
        assert books != null ; 
    Set<String> seen = new HashSet<>();
       for(String b: books){
        assert b != null;
        assert b != "";
        assert seen.add(b);

       }
    }

    //สร้างเพลย์ลิสต์ว่าง
    public Boundedstack() {
        this.books = new ArrayList<>();
        this.capacity = 200;
        checkRep();
    }
    
    /**
     * 
     * @param capacity
     * @throws IllegalArgumentException ถ้า capacity
     */
    public Boundedstack(int capacity){
        this.books = new ArrayList<>();
        this.capacity = capacity;
        checkRep();

    }

    /**
     * เพิ่มหนังสือที่ถูกคืนเข้ามา
     * @param book รหัสหนังสือ ต้องไม่เป็นnullและเป็นสตริงว่าง
     * @return
     * @throws IllegalArgumentException ถ้าbookเป็นnullหรือสตริงว่าง
     */
    public boolean add(String book){
     if(book == null) throw new IllegalArgumentException();
     if(book=="" )throw new IllegalArgumentException();
     return false;

    }

    /**
     * ลบหนังสือที่ถูกยืมออกไป
     * @param 
     * @return
     */
    public boolean remove(String book){
       return false;

    }

    /**
     * ดูหนังสือที่ถูกคืนล่าสุด
     * @param
     * @return
     */
    public String peek() {
     return null;
    }
    /**
     * ตรวจว่ามีฟนังสือนี้อยู่ฟรือไม่
     * @return
     */
    public boolean contains(String book){
        return books.contains(book);
    }

    /**
     * คืนจำนวนหนังสือ
     * @return
     */
    public int size(){
        return books.size();
    }

    /**
     * คืนหนังสือทั้งหมด
     * @return
     */
    public List<String> books(){
        return new ArrayList<>(books);
    }
    
    /**
     * @return
     */
   /*  public Boundedstack shuffled(){
        List<String> copy = new ArrayList<>(books);
        Collections.shuffle(copy);
        return new Boundedstack(copy);

    }*/ 
}