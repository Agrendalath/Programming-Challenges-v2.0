package agrendalath.main_menu;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Menu {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("agrendalath", new SubTypesScanner(false));
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);

        List<Class<?>> menu = allClasses.stream().filter(
                c -> c.getPackage().getName().replace("agrendalath.", "").replace("_", "")
                        .equals(c.getSimpleName().toLowerCase())).collect(Collectors.toList());

        System.out.println("Hello. Choose the program you want to run.");
        for (int i = 0; i < menu.size(); ++i)
            System.out.println(i + ". " + menu.get(i).getSimpleName());

        Scanner scanner = new Scanner(System.in);

        try {
            Class<?> c = menu.get(scanner.nextInt());
            Method main = c.getMethod("main", String[].class);
            main.invoke(null, (Object) null);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such option in this program. Maybe it will show up later :)");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
