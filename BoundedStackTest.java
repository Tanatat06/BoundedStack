import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Test runner
 * นายธนทัต สนนาค 6821651299 sec801
 * น.ส.ธมลวรรณ กลักเพชร 6821651353 sec 801
 */
public class BoundedStackTest {
    private static int passed = 0;
    private static int failed = 0;
    

    private static void check(String name , boolean condition){
        if(condition){
            passed++;
            System.out.println("[PASS] " + name);
            
        }else {
            failed++;
            System.out.println("[FAIL]" + name);
        }

    }
    public static void main(String[] args){
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn){
            System.out.println("WARNING: assertions disabled"
                         +"- re-run with: java -ea LibraryTest\n");
        }
    System.out.println("=== Library Test Suite ===");
        testAdd();
        testRemove();
        testLatestBook();
        testCreators();
        testObservers();
        testProducer();
        testExposure();    
    System.out.println("\n=== Summary ===");
    System.out.println("Passed: " + passed);
    System.out.println("Failed: " + failed);
    System.out.println("Total : " + (passed + failed));
    System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");
    if(failed > 0){
        System.exit(1);
    }
    }
    // --- Partition ว่าง / มีหนังสือ / input ที่ผิดเงื่อนไข ---
    private static void  testCreators(){
        System.out.println("-- Creators --");

        BoundedStack empty = new BoundedStack();
        check("new() -> empty", empty.size ()== 0);
        check("new() -> contains nothing",!empty.contains("anyting"));

        BoundedStack b = new BoundedStack(Arrays.asList("A"));
        check( "new(list) -> size 1", b.size() == 1);
        check( "new(list) -> contains A", b.contains("A"));

        BoundedStack formEmpty = new BoundedStack(new ArrayList<String>() );
        check("new(empty list) -> empty", formEmpty.size() == 0);
        // ถ้าinput ผิดเงื่อนไข่จะต้องโยน exception
        boolean threwDup = false;
        try{
            new BoundedStack(Arrays.asList("A","A"));
    }catch (IllegalArgumentException e){
        threwDup = true;
    }
    check("new(duplicates) -> throws IllegalArgumentException", threwDup);
    boolean threwNull = false;
    try {
        new BoundedStack(Arrays.asList("A", null)); 
    }catch (IllegalArgumentException e) {
        threwNull = true;
    }
    check("new(list with null) -> throws IllegalArgumentException", threwNull);

    boolean threwNullList = false;
    try {
        new BoundedStack(null); 
    }catch (IllegalArgumentException e) {
        threwNullList = true;
    }
        check("new(null) -> IllegalArgumentException", threwNullList);
    }
    //--- Mutator:add ป้องกันรหัสหนังสือซ้ำ ---
    private static void testAdd(){
        System.out.println("\n-- Add --");
        //ใส่หนังสือเข้าชั้นวางสำเร็จ
        BoundedStack b = new BoundedStack();
        check("Add(A) -> adding A succeeds", b.add("A"));
        check("Add(A) -> size 1", b.size() == 1);
        check("Add(A) -> found by contains", b.contains("A"));
        
       
        //input ผิดเงื่อนไขจึง throws exception
        boolean threwEmpty = false;
        try {
            b.add("");
        } catch (IllegalArgumentException e) {
             threwEmpty = true;
        } check("Add(empty string) -> throws IllegalArgumentException", threwEmpty);

        
    }
    //--- Mutator:remove ป้องกันรหัสหนังสือซ้ำ ---
    private static void testRemove(){
        System.out.println("\n-- Remove --");
        BoundedStack b = new BoundedStack(Arrays.asList("A", "B", "C"));
        check("remove(B) -> returns true", b.remove("B"));
        check("remove -> size decreases", b.size() == 2 );
        check("remove keeps the others in order",
            b.bookID().equals(Arrays.asList("A", "C")));
        //ถ้าโดนยืมหนังสือ หนังสือในชั้นจะต้องลดลง
        //ถ้าหนังสือไม่มีในชั้นจะTure
        //หนังสือหายออกไปจากชั้น
    }
    //---LatestBook บอกเล่มล่าสุดว่าถูกไหม ---
    private static void testLatestBook(){
        System.out.println("\n-- LatestBook --");
    // ทดสอบผ่าน add() ทีละตัว
    BoundedStack b1 = new BoundedStack();
    b1.add("A");
    b1.add("B");
    b1.add("C");
    check("after add(A), add(B), add(C) -> latestBook is C",
            b1.latestBook().equals("C"));

    // ทดสอบผ่าน constructor ที่รับ list
    BoundedStack b2 = new BoundedStack(Arrays.asList("A", "B", "C"));
    check("constructor preserves order -> latestBook is C",
            b2.latestBook().equals("C"));
        
        
    }
    // Observers ดูสถานะแล้ว ข้อมูลไม่เปลี่ยนใช่ไหม
    private static void testObservers(){
        System.out.println("\n-- Observers --");

        BoundedStack b = new BoundedStack(new ArrayList<String>(), 2);
        b.add("A");
        check("before filling to capacity -> isFull false", !b.isFull());
        b.add("B");
        check("after filling to capacity -> isFull true", b.isFull());
        check("Add when bookshelf full -> returns false", !b.add("one more"));
        check("bookshelf is still full after failed add", b.isFull()); 
        BoundedStack b1 = new BoundedStack();
        //ชั้นหนังสือต้องว่าง ไม่ว่าง false
        check("new bookshelf is empty", b1.isEmpty());
        //เพิ่มหนังสือแล้วต้องไม่ว่าง
        b1.add("A");
        check("after add(A) -> isEmpty false", !b1.isEmpty());

    }
    //Producer หนังสือมี representation แยกจากของเดิมจริงไหม
    private static void testProducer(){
        System.out.println("\n-- Producer --");
    BoundedStack original = new BoundedStack(Arrays.asList("A", "B", "C"));
    
        BoundedStack shuffled = original.shuffled();

    check("shuffled() -> returns new stack",
            shuffled != original);
        //จำนวนหนังสือใน shuffled เท่ากับ original ไหม
    check("shuffled() -> same size as original",
            shuffled.size() == original.size());
    //หนังสือทั้ง 3 เล่มยังอยู่ครบใน shuffled ไหม
    check("shuffled() -> contains A",
            shuffled.contains("A"));

    check("shuffled() -> contains B",
            shuffled.contains("B"));

    check("shuffled() -> contains C",
            shuffled.contains("C"));

            shuffled.add("D");
    check("mutating shuffled does not affect book", original.size() == 3);

        
    }
    //Exposure representation รั่วให้คนนอกแก้ได้ไหม
    private static void testExposure(){
        System.out.println("\n-- Exposure --");
        //การแก้ค่าใดๆจะไม่มีผลต่อStack
        BoundedStack b = new BoundedStack(Arrays.asList("A","B"));
        List<String> copy =b.bookID();
        copy.add("E");
        check("adding to returned list does not affect stack",!b.contains("E"));

        copy.remove("A");
        check("removing from returned list does not affect stack",b.contains("A"));

        copy.clear();
        check("clearing returned list does not affect stack size",b.size() == 2);
        // สองครั้งต้องเป็นคนละ object
        check("bookID() returns a fresh list each call",
                b.bookID() != b.bookID());
        
        List<String> input = new ArrayList<String>(Arrays.asList("A", "B"));
    BoundedStack s = new BoundedStack(input);

    input.clear();
    check("clearing constructor argument does not affect stack",
        s.size() == 2);

    input.add("injected");
    check("adding to constructor argument does not affect stack",
        !s.contains("injected"));

    }       
}
