import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * BoundedStack คือ ADTแทนชั้นหนังสือที่บรรณารักษ์ใช้บันทึกการยืมและคืนหนังสือ
 */
public class BoundedStack {  
    private final List<String> bookID; // private final String book ;
    private final int capacity;
    //Abstraction Function:
    // bookID แทนชั้นหนังสือที่เก็บรหัสหนังสือ "A100","A101"...
    // ลำดับใน List แทนลำดับการเพิ่มหนังสือ
    // หนังสือที่อยู่ตำแหน่งสุดท้ายคือหนังสือที่ถูกเพิ่มล่าสุด
    
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
        assert bookID != null ; 
        assert capacity >= 0;
        assert bookID.size() <= capacity;
    Set<String> seen = new HashSet<>();
       for(String b: bookID){
        assert b != null;
        assert !b.isEmpty();
        assert seen.add(b);
       }
    }
    //สร้างเพลย์ลิสต์ว่าง กำหนดค่าความจุ
    public BoundedStack() {
        this.bookID = new ArrayList<>();
        this.capacity = 200;
        checkRep();
    }
    /** สร้างชั้นหนังสือที่เอาไว้เก็บbookID
     * @param Library ชั้นหนังสือต้องไม่เกินความจุที่กำหนด
     * @throws IllegalArgumentException ถ้าLibraryผิดเงื่อนไข
     */
    public BoundedStack(List<String> Library,int capacity){
        if(Library == null)throw new IllegalArgumentException();
        if(capacity < 0)throw new IllegalArgumentException();
        if(Library.size()>capacity)throw new IllegalArgumentException();
        Set<String>seen = new HashSet<>();
        for(String b : Library){
         if(b==null)throw new IllegalArgumentException();
         if(b.isEmpty())throw new IllegalArgumentException();
         if(!seen.add(b))throw new IllegalArgumentException();   
        }
        this.capacity = capacity;
        this.bookID = new ArrayList<>(Library);
        checkRep();
    }

    /**
     * 
     * เพิ่มหนังสือใหม่และหนังสือที่ถูกยืมเข้ามา
     * @param book รหัสหนังสือ ต้องไม่เป็นnullและไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีหนังสือนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้าbookเป็นnullหรือสตริงว่าง
     */
    public boolean add(String book){
     if(book == null) throw new IllegalArgumentException();
     if(book.isEmpty())throw new IllegalArgumentException();
     if(bookID.contains(book)|| bookID.size()==capacity) return false;
    bookID.add(book);
    checkRep();
    return true;
    }
    /**
     * ยืมหนังสือและลบหนังสือออกไป
     * @param book หนังสือที่ต้องการยืม/ลบ
     * @return trueถ้าลบ/ยืมหนังสือสำเร็จ,falseถ้าไม่พบหนังสือ
     * @throws IllegalArgumentException ถ้าbookเป็นnull
     */
    public boolean remove(String book){
       if(book == null) throw new IllegalArgumentException();
       if(!bookID.contains(book)) return false;
       bookID.remove(book);
       checkRep();
       return true;
    }
    /**
     * ดูหนังสือที่ล่าสุดที่ถูกเพิ่มมา
     * @return ส่งค่าหนังสือล่าสุดที่ถูกเพิ่ม
     */
    public String latestBook(){
      return bookID.get(bookID.size()-1);
    }
    /**
     * ตรวจว่ามีหนังสือนี้อยู่หรือไม่
     * @return ส่งค่าว่ามีหนังสือนี้อยู่หรือไม่
     */
    public boolean contains(String book){
        return bookID.contains(book);
    }
    /**
     * คืนจำนวนหนังสือ
     * @return ส่งค่าจำนวนหนังสือกลับไป
     */
    public int size(){
        return bookID.size();
    }
    /**
    * ตรวจว่าชั้นหนังสือว่างหรือไม่
    * @return true ถ้าไม่มีหนังสืออยู่ในชั้นเลย, false ถ้ามีหนังสืออย่างน้อย 1 เล่ม
    */
    public boolean isEmpty(){
        return bookID.isEmpty();
    }
    /**
    * ตรวจว่าชั้นหนังสือเต็มความจุหรือยัง
    * @return true ถ้าจำนวนหนังสือเท่ากับความจุสูงสุด, false ถ้ายังมีที่ว่าง
    */
    public boolean isFull(){
        return bookID.size() == capacity;
    }
     /**
    * สร้างชั้นหนังสือใหม่อีกชั้นนึงที่มีหนังสือเหมือนกันทุกเล่ม
    * แต่ถ้าแก้ไขชั้นไหน อีกชั้นจะไม่เปลี่ยนตาม
    * @return ชั้นหนังสือใหม่
    */
    public BoundedStack shuffled (){
        List<String> copy =new ArrayList<>(bookID);
        Collections.shuffle(copy);
        return new BoundedStack(copy,capacity);
    }
    /**
     * คืนหนังสือทั้งหมด
     * @return ส่งค่าหนังสือทั้งหมด
     */
    public List<String> books(){
        return new ArrayList<>(bookID);
    }
   @Override
    public String toString(){
        return bookID.toString();
    }
}