import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Test runner
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

        BoundedStack empty = new BoundedStack();//ต้องแก้
        check("new() -> empty", empty.size ()== 0);
        check("new() -> contains nothing",!empty.contains("anyting"));

        BoundedStack b = new BoundedStack();//แก้
        check( "new(list) -> size 1", b.size() == 1);
        check( "new(list) -> contains A", b.contains("A"));

        //ต้องเติม Boudary
        BoundedStack formEmpty = new BoundedStack(); //แก่
        check("new(empty list) -> empty",formEmpty.size() == 0);
        // ถ้าinput ผิดเงื่อนไข่จะต้องโยน exception
        boolean threwDup = false;
        try{
           new BoundedStack();
        }catch (IllegalArgumentException e){
            threwDup = true;
        }check("new(duplicates) -> throws IllegalArgumentException", threwDup);
        
        boolean threwNull = false;
        try {
            new BoundedStack();
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);
        boolean threwNullList = false;
        try {
            new BoundedStack();
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> IllegalArgumentException", threwNullList);
        
    } 

    //--- Mutator:add ป้องกันรหัสหนังสือซ้ำ ---
    private static void testAdd(){
        System.out.println("\n-- Add --");
        //ใส่หนังสือเข้าชั้นวางสำเร็จ
        BoundedStack s = new BoundedStack();
        check("Add(A) -> adding A succeeds", s.add("A"));
        check("Add(A) -> size 1", s.size() == 1);
        check("Add(A) -> found by contains", s.contains("A"));
        

        
        //input ผิดเงื่อนไขจึง throws exception
        boolean threwEmpty = false;
        try {
            s.add("");
        } catch (IllegalArgumentException e) {
             threwEmpty = true;
        } check("Add(empty string) -> throws IllegalArgumentException", threwEmpty);

        
    }
    private static void testRemove(){
        System.out.println("\n-- Remove --");
        //ถ้าโดนยืมหนังสือ หนังสือในชั้นจะต้องลดลง
        //ถ้าหนังสือไม่มีในชั้นจะTure
        //หนังสือหายออกไปจากชั้น
        //หนังสือชำรุด
    }
    private static void testLatestBook(){
        System.out.println("\n--- LatestBook ---");

        BoundedStack s = new BoundedStack();
        check(null, false);


        //
        
    }
    private static void testObservers(){
        System.out.println("\n--- Observers ---");

        BoundedStack s = new BoundedStack(new ArrayList<String>(), 2);
        s.add("A");
        check("before filling to capacity -> isFull false", !s.isFull());

        s.add("B");
        check("after filling to capacity -> isFull true", s.isFull());

        check("Add when bookshelf full -> returns false", !s.add("one more"));
        check("bookshelf is still full after failed add", s.isFull());
        
        //ถ้าชั้นหนังสือว่างเป็นtrue ไม่ว่างfalse
        //BoundedStack s = new BoundedStack(new ArrayList<String>(),2);
        //s.add("A");
        //s.add("B");
        //check("after filling to capacity -> isFull true", !s.isFull());
        //check("after filling to capacity -> isFull true", s.isFull());
        //check("Add when bookshelf full -> return false", s.add("one more"));
        //check("bookshelf is still full after failed add",s.isFull()); 


    }
    private static void testProducer(){
        System.out.println("\n--- Producer ---");

        



    }
    private static void testExposure(){
        System.out.println("\n--- Exposure ---");
    }       
}
