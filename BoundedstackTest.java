import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Test runner
 */
public class BoundedstackTest {
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
                    +"- re-run with: java -ea LibraryTest\n"
            );
        }
    System.out.println("=== Library Test Suite ===");
        testCreators();
       
        




        


    if(failed > 0){
        System.exit(1);
    }
    }
    // --- Partition ว่าง / มีหนังสือ / input ที่ผิดเงื่อนไข ---
    private static void  testCreators(){
        System.out.println("-- Creators --");

        Boundedstack empty = new Boundedstack();
        check("new() -> empty", empty.size ()== 0);
        check("new() -> contains nothing",!empty.contains("anyting"));

        Boundedstack b = new Boundedstack();
        check( "new(list) -> size 1", b.size() == 1);
        check( "new(list) -> contains A", b.contains("A"));

        //ต้องเติม Boudary
        Boundedstack formEmpty = new Boundedstack();
        check("new(empty list) -> empty",formEmpty.size() == 0);
        // ถ้าinput ผิดเงือนไข่จะต้องโยน exception
        boolean threwDup = false;
        try{
           new Boundedstack();
        }catch (IllegalArgumentException e){
            threwDup = true;
        }check("new(duplicates) -> throws IllegalArgumentException", threwDup);
        
        boolean threwNull = false;
        try {
            new Boundedstack();
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);

        
    } 
      
        
}
