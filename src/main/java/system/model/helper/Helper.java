package system.model.helper;

import java.lang.reflect.Field;

/**
 * Created by vladimir on 08.01.2018.
 *
 * Метод то toLine создан как эксперимент создания универсального метода,
 * который позволи принять любой класс и вернуть строку значений всех его
 * параметров в нижнем регистре.
 */
public class Helper {

    public static <T> String toLine(T t) {
        StringBuilder builder = new StringBuilder();
        Class clazz = t.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String line = String.valueOf(field.get(t));
                if (line!=null) {
                    if (field.getName().equals("readAlready")){
                        line = line.equals("1") ? "Прочитано" : "Не прочитано";
                    }
                    builder.append(line.toLowerCase());
                    builder.append(" ");
                }
            } catch (IllegalAccessException e) {
                //ignore
            }
        }
        return builder.toString();
    }
}
