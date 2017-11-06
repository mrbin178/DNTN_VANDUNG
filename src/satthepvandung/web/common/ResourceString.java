package satthepvandung.web.common;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceString
{
   private static MessageSource messageSource = new ClassPathXmlApplicationContext("resources/locale.xml");
    public static String getMessage(String key)
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, new Object[0], locale);
    }


}