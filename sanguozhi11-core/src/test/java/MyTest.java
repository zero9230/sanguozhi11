import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyTest {
    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        mockList.add("one1");
        assertEquals(0, mockList.size());
        Mockito.verify(mockList).add("one");


        Mockito.when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

    @Test
    public void test2(){
        HashSet<String> set=new HashSet<>();
        set.add("ss");
        System.out.println(set);
        set.remove("ss");
        System.out.println(set);

    }
}
