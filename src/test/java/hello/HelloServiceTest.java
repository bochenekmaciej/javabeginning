package hello;

import lang.Lang;
import lang.LangRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Morgen";
    @Test
    public void test_nullName_PrepareGreeting_returnsGreetingWithFallbackName() throws Exception{
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null,-1);

        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_name_PrepareGreeting_returnsGreetingWithName() throws Exception{
        // given
        var name = "test";
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(name, -1);

        //then
        assertEquals( WELCOME + " " +    name + "!", result);
    }
    @Test
    public void test_nullLang_PrepareGreeting_returnsGreetingWithFallbackIdLang() throws Exception{
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }
    /*@Test
    public void test_textLang_PrepareGreeting_returnsGreetingWithFallbackIdLang() throws Exception{
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }*/

    @Test
    public void test_nonExistingLang_prepareGreeting_returnsGreetingWithFallbackLang() throws Exception{
        // given
        var mockRepository = new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, -1);

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!",result);
    }

    private LangRepository fallbackLangIdRepository(){
       return new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null, "chuj"));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository(){
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null, "chuj"));
            }
        };
    }
}
